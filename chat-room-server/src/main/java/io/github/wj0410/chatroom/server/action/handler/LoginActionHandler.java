package io.github.wj0410.chatroom.server.action.handler;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.comparator.VersionComparator;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import io.github.wj0410.chatroom.common.constant.IpConstants;
import io.github.wj0410.chatroom.common.entity.*;
import io.github.wj0410.chatroom.common.enums.Action;
import io.github.wj0410.chatroom.common.enums.Platform;
import io.github.wj0410.chatroom.common.enums.UserStatus;
import io.github.wj0410.chatroom.server.action.ChannelAction;
import io.github.wj0410.chatroom.server.action.handler.base.ActionHandler;
import io.github.wj0410.chatroom.server.annotation.DoAction;
import io.github.wj0410.chatroom.server.builder.ResponseBuilder;
import io.github.wj0410.chatroom.server.cache.UserCache;
import io.github.wj0410.chatroom.server.factory.ObjectFactory;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.github.wj0410.chatroom.server.service.AbstractResponseHistoryService;
import io.github.wj0410.chatroom.server.util.IpUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * @author anlingyi
 * @date 2020/8/14
 */
@Slf4j
@DoAction(Action.LOGIN)
public class LoginActionHandler implements ActionHandler<LoginDTO> {

    @Override
    public void handle(ChannelHandlerContext ctx, LoginDTO body) {
        if (ChannelAction.getUser(ctx) != null) {
            ctx.writeAndFlush(ResponseBuilder.system("请勿重复登录！"));
            return;
        }

        if (body.getPlatform() == null) {
            body.setPlatform(Platform.WEBSOCKET);
        }

        if (body.getPlatform() == Platform.JAVA_SWING) {
            // JAVA SWING客户端登录的需要比对插件版本
            String currentClientVersion = ServerHolder.serverProperties.getClient().getVersion();
            String userClientVersion = body.getClientVersion();
            if (StrUtil.isNotBlank(userClientVersion)) {
                int len = currentClientVersion.length();
                int len2 = userClientVersion.length();
                int k = len - len2;
                String padding = "";
                for (int i = Math.abs(k); i > 0; i--) {
                    padding += "0";
                }
                if (k > 0) {
                    userClientVersion += padding;
                } else if (k < 0) {
                    currentClientVersion += padding;
                }
            }

            int versionState = VersionComparator.INSTANCE.compare(currentClientVersion, userClientVersion);
            if (versionState > 0) {
                ctx.writeAndFlush(ResponseBuilder.system("温馨提醒~ 请尽快更新客户端版本至v" + ServerHolder.serverProperties.getClient().getVersion() + "！"));
            }
            if (versionState < 0) {
                ctx.writeAndFlush(ResponseBuilder.system("当前服务端版本过低！你的版本：v" + body.getClientVersion()
                        + "，服务端版本：v" + ServerHolder.serverProperties.getClient().getVersion()));
            }
        }

        boolean isReconnect = body.isReconnected();
        String username = body.getUsername();

        if (StrUtil.isBlank(username)) {
            ctx.writeAndFlush(ResponseBuilder.system("昵称不能为空！"));
            ctx.close();
            return;
        }

        String channelId = ChannelAction.getChannelId(ctx);
        final String ip = IpUtil.getIpByCtx(ctx);
        final IpRegion ipRegion = IpUtil.getRegionByIp(ip);
        User user = new User(body, channelId, ip, ipRegion, ctx.channel());
        // 用户缓存
        UserCache.add(channelId, user);
        ChannelAction.add(ctx.channel());
        // 当前登录用户同步在线用户列表
        ChannelAction.sendOnlineUsers(user);
        // 给所有在线用户同步当前登录用户的状态为在线
        ChannelAction.sendUserState(user, UserStatus.ON_LINE);
        if (isReconnect) {
            user.send(ResponseBuilder.system("重新连接服务器成功！"));
        }
        user.send(ResponseBuilder.system("修身洁行，言必由绳墨。"));
        // 给所有在线用户提示当前登录用户进入聊天室
        final String loginMsg = StrUtil.format("[{}·{}]进入了聊天室！",
                MapUtil.getStr(IpConstants.SHORT_PROVINCE, ipRegion.getProvince(), ipRegion.getCountry()), user.getUsername());
        ChannelAction.send(ResponseBuilder.system(loginMsg));
        // 给当前登录用户获取最近30条历史消息记录
        List<Response> historyMsgList = ObjectFactory.getObject(AbstractResponseHistoryService.class).getHistory(30);
        if (CollectionUtil.isNotEmpty(historyMsgList)) {
            user.send(ResponseBuilder.build(null, new HistoryMsgDTO(historyMsgList), MessageType.HISTORY_MSG));
        }
    }

}
