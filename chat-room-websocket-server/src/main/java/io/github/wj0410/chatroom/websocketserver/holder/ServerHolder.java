package io.github.wj0410.chatroom.websocketserver.holder;

import io.github.wj0410.chatroom.common.conf.ServerProperties;
import io.github.wj0410.chatroom.common.constant.CommonConstants;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.util.AttributeKey;

/**
 * @author wangjie
 * @date 2023/11/8
 */
public class ServerHolder {
    public static SocketChannel serverSocketChannel;
    public static ServerProperties serverProperties;
    public static WebSocketServerHandshaker handshaker;

    public static void setClientIdAttr(String clientId) {
        if (serverSocketChannel != null) {
            serverSocketChannel.attr(AttributeKey.valueOf(CommonConstants.CLIENT_ID)).set(clientId);
        }
    }

}
