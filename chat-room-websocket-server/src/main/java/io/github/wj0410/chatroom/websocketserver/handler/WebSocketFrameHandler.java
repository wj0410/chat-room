package io.github.wj0410.chatroom.websocketserver.handler;

import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.common.util.ServerUtil;
import io.github.wj0410.chatroom.websocketserver.holder.ServerHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.*;
import lombok.extern.slf4j.Slf4j;


/**
 * websocket消息都通过此处接收
 *
 * @author wangjie
 * @date 2023/11/8
 */
@Slf4j
public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否是关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            ServerHolder.handshaker.close(ctx.channel(),
                    (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否是Ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(
                    new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        if (frame instanceof TextWebSocketFrame) {
            // 将msg交给下一个handler处理
            ctx.fireChannelRead(frame);
        } else {
            throw new UnsupportedOperationException(String.format(
                    "%s frame types not supported", frame.getClass().getName()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常处理逻辑
        cause.printStackTrace();
        System.err.println("Exception caught: " + cause);
    }

    /**
     * 客户端下线
     *
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ClientModel clientModel = ServerUtil.getClientModel(ctx);
        ServerUtil.removeClient(ctx);
        // 给所有客户端发送同步在线列表消息
        ServerUtil.sendSyncOnlineMessage();
        // 给所有客户端发送离开消息
        if (clientModel != null) {
            ServerUtil.sendLeaveMessage(clientModel.getClientId(), clientModel.getUserName());
        }
    }
}

