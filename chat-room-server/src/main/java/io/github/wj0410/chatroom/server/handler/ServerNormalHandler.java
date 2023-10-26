package io.github.wj0410.chatroom.server.handler;

import io.github.wj0410.chatroom.common.message.NormalMessage;
import io.github.wj0410.chatroom.common.util.UIUtil;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.github.wj0410.chatroom.server.util.ServerUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * @author wangjie
 * @date 2023/10/23
 */
public class ServerNormalHandler extends SimpleChannelInboundHandler<NormalMessage> {

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

    /**
     * 接收消息
     *
     * @param ctx
     * @param normalMessage
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NormalMessage normalMessage) {
        System.out.println(String.format("服务端收到客户端 %s 消息：%s", ServerUtil.formatClientAccount(ctx), normalMessage.toString()));
        if (ServerHolder.serverUI != null) {
            UIUtil.drawConsole(ServerHolder.serverUI.getConsolePane(), String.format("服务端收到客户端 %s 消息：%s", ServerUtil.formatClientAccount(ctx), normalMessage.toString()));
        }
    }

}
