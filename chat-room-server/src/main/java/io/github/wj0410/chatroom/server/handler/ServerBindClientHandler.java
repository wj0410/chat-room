package io.github.wj0410.chatroom.server.handler;

import io.github.wj0410.chatroom.common.message.BindMessage;
import io.github.wj0410.chatroom.common.message.SyncOnlineMessage;
import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.github.wj0410.chatroom.common.util.UIUtil;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.github.wj0410.chatroom.server.util.ServerUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;


/**
 * @author wangjie
 * @date 2023/10/25
 */
@Slf4j
public class ServerBindClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Object message = MessageUtil.getMessage(msg.toString());
        if (message instanceof BindMessage) {
            BindMessage bindMessage = (BindMessage) message;
            // 将clientId保存到Channel中,后面可以找到
            ServerHolder.setClientIdAttr(bindMessage.getClientId());
            log.info("服务端接收到客户端的绑定信息：{}", bindMessage.toString());
            ServerUtil.addClient(ctx, bindMessage);
            if (ServerHolder.serverUI != null) {
                UIUtil.drawConsole(ServerHolder.serverUI.getConsolePane(), String.format("客户端 %s 连接了...", ServerUtil.formatClientAccount(ctx)));
                ServerHolder.serverUI.flushClientOnlineList();
            }
            // 服务端向客户端发送同步在线列表消息
            SyncOnlineMessage syncOnlineMessage = new SyncOnlineMessage();
            LinkedList<ClientModel> clientOnlineList = ServerUtil.getClientOnlineList();
            clientOnlineList.forEach(clientModel -> {
                clientModel.setCtx(null);
            });
            syncOnlineMessage.setClientOnlineList(clientOnlineList);
            String syncOnlineMessageJsonStr = MessageUtil.createSyncOnlineMessageJsonStr(syncOnlineMessage);
            ctx.writeAndFlush(syncOnlineMessageJsonStr);
            log.info("服务端向客户端发送同步在线列表消息：{}", syncOnlineMessageJsonStr);
        } else {
            log.info("服务端接收到客户端的普通信息：{}，即将将msg交给下一个handler处理", message.toString());
            // 将msg交给下一个handler处理
            ctx.fireChannelRead(message);
        }
    }
}
