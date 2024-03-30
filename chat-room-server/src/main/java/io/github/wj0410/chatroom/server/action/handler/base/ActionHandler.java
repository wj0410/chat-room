package io.github.wj0410.chatroom.server.action.handler.base;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author anlingyi
 * @date 2020/8/14
 */
public interface ActionHandler<T> {

    default void handle(final ChannelHandlerContext ctx, final T body) {
        // ignore
    }

}
