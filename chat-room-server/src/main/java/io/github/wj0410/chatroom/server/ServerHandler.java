package io.github.wj0410.chatroom.server;

import io.github.wj0410.chatroom.server.ui.ServerUI;
import io.github.wj0410.chatroom.server.util.UIUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangjie
 * @date 2023/10/23
 */
@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private ServerUI serverUI;

    public ServerHandler(ServerUI serverUI) {
        this.serverUI = serverUI;
    }

    /**
     * 客户端上线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        super.channelActive(ctx);
    }
    /**
     * 客户端下线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        super.channelInactive(ctx);
    }

    /**
     * 接收消息
     * @param ctx
     * @param str
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String str) throws Exception {
        if (serverUI != null) {
            UIUtil.drawConsole(serverUI.getConsolePane(), String.format("服务端收到客户端[%s]消息：%s", ctx.channel().remoteAddress(), str));
        }
    }
}
