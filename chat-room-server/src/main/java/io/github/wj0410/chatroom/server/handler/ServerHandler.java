package io.github.wj0410.chatroom.server.handler;

import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.github.wj0410.chatroom.common.util.ServerUtil;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;


/**
 * @author wangjie
 * @date 2023/10/25
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf) msg;
            Object message = MessageUtil.getMessage(MessageUtil.convertByteBuf2String(byteBuf));
            // 将msg交给下一个handler处理
            ctx.fireChannelRead(message);
        } else {
            throw new UnsupportedOperationException(String.format(
                    "%s types not supported", msg.getClass().getName()));
        }
    }

    /**
     * 客户端下线
     *
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ClientModel clientModel = ServerUtil.getClientModel(ctx);
        String formatClient = ServerUtil.formatClientAccount(ctx);
        ServerUtil.removeClient(ctx);
        // 给所有客户端发送同步在线列表消息
        ServerUtil.sendSyncOnlineMessage();
        // 给所有客户端发送离开消息
        ServerUtil.sendLeaveMessage(clientModel.getClientId(), clientModel.getUserName());
        if (ServerHolder.serverUI != null) {
            ServerHolder.serverUI.printConsole(String.format("客户端 %s 下线了...", formatClient));
            ServerHolder.serverUI.flushClientOnlineList();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常处理逻辑
        System.err.println("Exception caught: " + cause);
    }
}
