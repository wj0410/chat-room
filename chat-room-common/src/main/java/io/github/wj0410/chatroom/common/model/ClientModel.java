package io.github.wj0410.chatroom.common.model;

import io.github.wj0410.chatroom.common.enums.ClientOrigin;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
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
                return ctx.writeAndFlush(MessageUtil.convert2ByteBuf((String) o));
            case WEBSOCKET:
                // TODO websocket消息以 TextWebSocketFrame 传输
                return ctx.writeAndFlush(new TextWebSocketFrame((String) o));
            default:
                throw new IllegalStateException("Unexpected value: " + clientOrigin);
        }
    }
}
