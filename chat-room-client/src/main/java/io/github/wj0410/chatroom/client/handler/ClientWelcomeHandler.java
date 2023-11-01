package io.github.wj0410.chatroom.client.handler;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.util.ClientUtil;
import io.github.wj0410.chatroom.common.message.SyncOnlineMessage;
import io.github.wj0410.chatroom.common.message.WelcomeMessage;
import io.github.wj0410.chatroom.common.model.ClientModel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @author wangjie
 * @date 2023/10/23
 */
@Slf4j
public class ClientWelcomeHandler extends SimpleChannelInboundHandler<WelcomeMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WelcomeMessage message) {
        log.info("client: 读取到服务端欢迎消息：{}", message);
        ClientUtil.drawWelcomeRecvPane(message,ClientHolder.chatRoomUI.getRecvPane(),message.getClientId().equals(ClientHolder.clientInfo.getClientId()) ? 1 : 0);
    }

}
