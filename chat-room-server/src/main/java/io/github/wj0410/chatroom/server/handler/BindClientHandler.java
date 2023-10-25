package io.github.wj0410.chatroom.server.handler;

import io.github.wj0410.chatroom.common.constant.CommonConstants;
import io.github.wj0410.chatroom.common.message.BindRequest;
import io.github.wj0410.chatroom.common.util.UIUtil;
import io.github.wj0410.chatroom.server.Server;
import io.github.wj0410.chatroom.server.util.ServerUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.AttributeKey;

/**
 * @author wangjie
 * @date 2023/10/25
 */
public class BindClientHandler extends ChannelInboundHandlerAdapter {
    private SocketChannel ch;
    private Server server;

    public BindClientHandler(SocketChannel ch, Server server) {
        this.ch = ch;
        this.server = server;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof BindRequest) {
            BindRequest bindRequest = (BindRequest) msg;
            System.out.println("服务端接收到客户端的绑定信息：" + bindRequest.toString());
            // 将clientId保存到Channel中,后面可以找到
            ch.attr(AttributeKey.valueOf(CommonConstants.CLIENT_ID)).set(bindRequest.getClientId());
            ServerUtil.addClient(ctx, bindRequest);
            if (server.getServerUI() != null) {
                UIUtil.drawConsole(server.getServerUI().getConsolePane(), String.format("客户端 %s 连接了...", bindRequest.getClientId()));
                server.getServerUI().flushClientOnlineList();
            }
        } else {
            System.out.println("服务端接收到客户端的普通信息：" + msg.toString() + "，即将将msg交给下一个handler处理");
            // 将msg交给下一个handler处理
            ctx.fireChannelRead(msg);
        }
    }
}