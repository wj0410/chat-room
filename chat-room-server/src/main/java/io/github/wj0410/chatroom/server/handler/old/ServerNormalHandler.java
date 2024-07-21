package io.github.wj0410.chatroom.server.handler.old;

import io.github.wj0410.chatroom.common.message.NormalMessage;
import io.github.wj0410.chatroom.common.util.ServerUtil;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


/**
 * @author wangjie
 * @date 2023/10/23
 */
@Slf4j
public class ServerNormalHandler extends SimpleChannelInboundHandler<NormalMessage> {

    /**
     * 接收普通消息
     *
     * @param ctx
     * @param normalMessage
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NormalMessage normalMessage) {
        if (ServerHolder.serverUI != null) {
            ServerHolder.serverUI.printConsole(String.format("服务端收到客户端 %s 消息：%s",
                    ServerUtil.formatClientAccount(ctx), normalMessage.toString()));
        }
        // 转发客户端消息
        ServerUtil.relayNormalMessage(normalMessage);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常处理逻辑
        System.err.println("Exception caught: " + cause);
    }
}
