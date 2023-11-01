package io.github.wj0410.chatroom.client.handler;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.common.message.SyncOnlineMessage;
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
public class ClientSyncOnlineHandler extends SimpleChannelInboundHandler<SyncOnlineMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SyncOnlineMessage message) {
        log.info("client: 读取到服务端同步在线列表消息：{}", message);
        LinkedList<ClientModel> clientOnlineList = message.getClientOnlineList();
        ClientHolder.chatRoomUI.flushOnlineModelList(clientOnlineList);
    }

}
