package io.github.wj0410.chatroom.client.handler;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.util.ClientUtil;
import io.github.wj0410.chatroom.common.message.NormalMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * @author wangjie
 * @date 2023/10/23
 */
public class ClientNormalHandler extends SimpleChannelInboundHandler<NormalMessage> {

    /**
     * 接收服务端转发消息
     *
     * @param ctx
     * @param normalMessage
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NormalMessage normalMessage) {
        System.out.println(String.format("客户端收到服务端转发消息：%s", normalMessage.toString()));
        // 在UI消息区域显示消息
        ClientUtil.drawRecvArea(normalMessage, ClientHolder.chatRoomUI.getRecvPane(), normalMessage.getFromClientId().equals(ClientHolder.clientInfo.getClientId()) ? 1 : 0);
    }

}
