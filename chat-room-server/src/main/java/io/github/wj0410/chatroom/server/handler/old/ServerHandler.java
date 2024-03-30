package io.github.wj0410.chatroom.server.handler.old;

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
            Object message = MessageUtil.getRealityMessageByMessageJsonStr(MessageUtil.convertByteBuf2String(byteBuf));
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
        if (clientModel == null) {
            return;
        }
        ServerUtil.removeClient(clientModel);
        if (ServerHolder.serverUI != null) {
            ServerHolder.serverUI.printConsole(String.format("客户端 %s 下线了...", ServerUtil.formatClientAccount(ctx)));
            ServerHolder.serverUI.flushClientOnlineList();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常处理逻辑
        System.err.println("Exception caught: " + cause);
    }
}
