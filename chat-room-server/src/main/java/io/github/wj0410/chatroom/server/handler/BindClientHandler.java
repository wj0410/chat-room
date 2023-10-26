package io.github.wj0410.chatroom.server.handler;

import io.github.wj0410.chatroom.common.message.BindRequest;
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
public class BindClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof BindRequest) {
            BindRequest bindRequest = (BindRequest) msg;
            // 将clientId保存到Channel中,后面可以找到
            ServerHolder.setClientIdAttr(bindRequest.getClientId());
            log.info("服务端接收到客户端的绑定信息：{}", bindRequest.toString());
            ServerUtil.addClient(ctx, bindRequest);
            if (ServerHolder.serverUI != null) {
                UIUtil.drawConsole(ServerHolder.serverUI.getConsolePane(), String.format("客户端 %s 连接了...", ServerUtil.formatClientAccount(ctx)));
                ServerHolder.serverUI.flushClientOnlineList();
            }
        } else {
            log.info("服务端接收到客户端的普通信息：{}，即将将msg交给下一个handler处理", msg.toString());
            // 将msg交给下一个handler处理
            ctx.fireChannelRead(msg);
        }
    }
}
