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
    protected void channelRead0(ChannelHandlerContext ctx, SyncOnlineMessage message) throws InterruptedException {
        log.info("client: 读取到服务端同步在线列表消息：{}", message.toString());
        LinkedList<ClientModel> clientOnlineList = message.getClientOnlineList();
        /**
         * 这里睡眠100毫秒是因为登录时无法实时拿到服务器回传消息，导致消息有延迟
         * 可能消息已经过来了，但是登录成功还没来得及生成chatRoomUI对象
         */
        while (ClientHolder.chatRoomUI == null){
            Thread.sleep(100);
        }
        ClientHolder.chatRoomUI.flushOnlineModelList(clientOnlineList);
    }

}
