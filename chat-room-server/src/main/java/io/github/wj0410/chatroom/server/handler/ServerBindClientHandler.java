package io.github.wj0410.chatroom.server.handler;

import io.github.wj0410.chatroom.common.constant.CommonConstants;
import io.github.wj0410.chatroom.common.message.BindMessage;
import io.github.wj0410.chatroom.common.message.RefuseMessage;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.github.wj0410.chatroom.server.util.ServerUtil;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;


/**
 * @author wangjie
 * @date 2023/10/25
 */
@Slf4j
public class ServerBindClientHandler extends SimpleChannelInboundHandler<BindMessage> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, BindMessage bindMessage) {
        // 将clientId保存到Channel中,后面可以找到
        ServerHolder.setClientIdAttr(bindMessage.getClientId());
        log.info("服务端接收到客户端的绑定信息：{}", bindMessage.toString());
        // 新增客户端到内存
        ServerUtil.addClient(ctx, bindMessage);
        // 校验客户端版本
        if (!checkClientVersion(bindMessage.getClientVersion())) {
            ServerUtil.sendRefuseMessage(bindMessage.getClientId(), CommonConstants.CLIENT_VERSION_LAG_TIP);
            return;
        }
        if (ServerHolder.serverUI != null) {
            ServerHolder.serverUI.printConsole(String.format("客户端 %s 连接了...", ServerUtil.formatClientAccount(ctx)));
            // 刷新UI在线列表
            ServerHolder.serverUI.flushClientOnlineList();
            // 给所有客户端发送同步在线列表消息
            ServerUtil.sendSyncOnlineMessage();
            // 给所有客户端发送欢迎消息
            ServerUtil.sendWelcomeMessage(bindMessage.getClientId());
        }
    }

    /**
     * 校验客户端版本
     * TODO 校验逻辑后面重构
     *
     * @return
     */
    private boolean checkClientVersion(String clientVersion) {
        if (StringUtils.isBlank(clientVersion)) {
            return false;
        }
        return true;
    }
}
