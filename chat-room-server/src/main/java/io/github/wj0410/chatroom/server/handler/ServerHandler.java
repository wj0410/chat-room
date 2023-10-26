package io.github.wj0410.chatroom.server.handler;

import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.github.wj0410.chatroom.common.util.UIUtil;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.github.wj0410.chatroom.server.util.ServerUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;


/**
 * @author wangjie
 * @date 2023/10/25
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端下线
     *
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        String formatClient = ServerUtil.formatClientAccount(ctx);
        ServerUtil.removeClient(ctx);
        if (ServerHolder.serverUI != null) {
            UIUtil.drawConsole(ServerHolder.serverUI.getConsolePane(), String.format("客户端 %s 下线了...", formatClient));
            ServerHolder.serverUI.flushClientOnlineList();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Object message = MessageUtil.getMessage(msg.toString());
        // 将msg交给下一个handler处理
        ctx.fireChannelRead(message);
    }
}
