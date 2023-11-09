package io.github.wj0410.chatroom.common.model;

import io.github.wj0410.chatroom.common.enums.ClientOrigin;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangjie
 * @date 2023/10/25
 */
@Data
public class ClientModel implements Serializable {
    private String clientId;
    private String account;
    private String userName;
    private ChannelHandlerContext ctx;
    private ClientOrigin clientOrigin;

    public ChannelFuture writeAndFlush(Object o) {
        switch (clientOrigin) {
            case SWING:
                // Swing客户端消息以ByteBuf形式传输
                return ctx.channel().writeAndFlush(MessageUtil.convertString2ByteBuf((String) o));
            case WEBSOCKET:
                // websocket消息以Frame传输
                if (o instanceof String) {
                    // 创建 TextWebSocketFrame 对象，并增加引用计数
                    return ctx.channel().writeAndFlush(new TextWebSocketFrame((String) o));
                }
                throw new UnsupportedOperationException(String.format(
                        "%s write types not supported", o.getClass().getName()));
            default:
                throw new IllegalStateException("Unexpected clientOrigin: " + clientOrigin);
        }
    }
}
