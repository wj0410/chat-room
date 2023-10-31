package io.github.wj0410.chatroom.client.handler;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.ui.PrivateChatUI;
import io.github.wj0410.chatroom.client.util.ClientUtil;
import io.github.wj0410.chatroom.common.message.NormalMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.util.CollectionUtils;

import javax.swing.*;
import java.util.List;


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
        List<String> targetClientIds = normalMessage.getTargetClientIds();
        JTextPane recvPane = null;
        String fromClientId = normalMessage.getFromClientId();
        if (CollectionUtils.isEmpty(targetClientIds)) {
            // 聊天室
            recvPane = ClientHolder.chatRoomUI.getRecvPane();
            ClientUtil.drawRecvArea(normalMessage, recvPane, fromClientId.equals(ClientHolder.clientInfo.getClientId()) ? 1 : 0);
        } else {
            // TODO 这里区分聊天室和私聊消息需要优化一下
            // 其他人发给自己的私聊消息
            PrivateChatUI privateChatUI = ClientHolder.privateChatUIMap.get(fromClientId);
            if (privateChatUI != null && privateChatUI.isShow()) {
                // 打开过聊天框并且窗口未关闭
                recvPane = privateChatUI.getRecvPane();
                ClientUtil.drawRecvArea(normalMessage, recvPane, 0);
            } else {
                // TODO 显示未读消息
            }
        }

    }

}
