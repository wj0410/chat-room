package io.github.wj0410.chatroom.server.holder;

import io.github.wj0410.chatroom.common.constant.CommonConstants;
import io.github.wj0410.chatroom.server.conf.ServerProperties;
import io.github.wj0410.chatroom.server.ui.AbstractServerUI;
import io.github.wj0410.chatroom.server.netty.NettyServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.AttributeKey;

/**
 * @author wangjie
 * @date 2023/10/26
 */
public class ServerHolder {
    public static ServerProperties serverProperties;
    public static NettyServer nettyServer;
    public static AbstractServerUI serverUI;
    public static SocketChannel serverSocketChannel;

    private ServerHolder() {
    }

    public static void setClientIdAttr(String clientId) {
        if (serverSocketChannel != null) {
            serverSocketChannel.attr(AttributeKey.valueOf(CommonConstants.CLIENT_ID)).set(clientId);
        }
    }
}
