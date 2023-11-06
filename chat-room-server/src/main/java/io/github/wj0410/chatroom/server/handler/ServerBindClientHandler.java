package io.github.wj0410.chatroom.server.handler;

import io.github.wj0410.chatroom.common.constant.CommonConstants;
import io.github.wj0410.chatroom.common.message.BindMessage;
import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.github.wj0410.chatroom.server.util.ServerUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedList;


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
        // TODO 校验客户端版本 暂时在配置文件里设置
        if (!checkClientVersion(bindMessage.getClientVersion())) {
            ServerUtil.sendRefuseMessage(bindMessage.getClientId(), CommonConstants.CLIENT_VERSION_LAG_TIP);
            return;
        }
        // TODO 校验是否已登录 暂时放在服务端校验
        if (!checkLogin(bindMessage.getAccount())) {
            ServerUtil.sendRefuseMessage(bindMessage.getClientId(), CommonConstants.CLIENT_HAS_LOGIN);
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
        if (StringUtils.isBlank(clientVersion)
                || !clientVersion.equals(ServerHolder.serverProperties.getClient().getVersion())) {
            return false;
        }
        return true;
    }

    private boolean checkLogin(String account) {
        int count = 0;
        LinkedList<ClientModel> clientOnlineList = ServerUtil.getClientOnlineList();
        for (ClientModel clientModel : clientOnlineList) {
            if (clientModel.getAccount().equals(account)) {
                count++;
            }
        }
        if (count > 1) {
            return false;
        }
        return true;
    }
}
