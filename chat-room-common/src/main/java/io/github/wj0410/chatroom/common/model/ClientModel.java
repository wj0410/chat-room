package io.github.wj0410.chatroom.common.model;

import io.github.wj0410.chatroom.common.enums.old.ClientOrigin;
import io.github.wj0410.chatroom.common.enums.old.ClientType;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author wangjie
 * @date 2023/10/25
 */
@Data
public class ClientModel implements Serializable, Cloneable {
    private String clientId;
    private String account;
    private String nickName;
    private String avatar;
    private ChannelHandlerContext ctx;
    private ClientOrigin clientOrigin;
    private ClientType clientType;

    public ChannelFuture writeAndFlush(Object o) {
        switch (clientOrigin) {
            case SWING:
                // Swing客户端消息以ByteBuf形式传输
                return ctx.channel().writeAndFlush(MessageUtil.convertString2ByteBuf((String) o));
            case WEBSOCKET:
                // websocket消息以Frame传输
                if (o instanceof String) {
                    // 创建 TextWebSocketFrame 对象
                    return ctx.channel().writeAndFlush(new TextWebSocketFrame((String) o));
                }
                throw new UnsupportedOperationException(String.format(
                        "%s write types not supported", o.getClass().getName()));
            default:
                throw new IllegalStateException("Unexpected clientOrigin: " + clientOrigin);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientModel that = (ClientModel) o;
        return Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId);
    }

    @Override
    public ClientModel clone() {
        try {
            ClientModel cloned = (ClientModel) super.clone();
            // 对于其他引用类型的属性，需要进行深度复制
            cloned.setCtx(null);
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
