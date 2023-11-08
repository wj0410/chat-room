package io.github.wj0410.chatroom.websocketserver.holder;

import io.github.wj0410.chatroom.websocketserver.conf.ServerProperties;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;

/**
 * @author wangjie
 * @date 2023/11/8
 */
public class ServerHolder {
    public static ServerProperties serverProperties;
    public static WebSocketServerHandshaker handshaker;
}
