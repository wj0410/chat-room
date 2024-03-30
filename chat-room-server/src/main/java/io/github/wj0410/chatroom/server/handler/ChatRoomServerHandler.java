package io.github.wj0410.chatroom.server.handler;

import io.github.wj0410.chatroom.common.entity.Request;
import io.github.wj0410.chatroom.common.enums.Protocol;
import io.github.wj0410.chatroom.server.handler.base.AbstractDefaultChannelHandler;
import io.github.wj0410.chatroom.server.handler.base.RequestHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author anlingyi
 * @date 2020/5/29
 */
@Slf4j
public class ChatRoomServerHandler extends AbstractDefaultChannelHandler<Request> {

    protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
        msg.setProtocol(Protocol.DEFAULT);
        new RequestHandler(ctx, msg).exec();
    }

}