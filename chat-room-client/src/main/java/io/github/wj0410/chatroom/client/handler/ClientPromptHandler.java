package io.github.wj0410.chatroom.client.handler;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.ui.swing.PrivateChatUI;
import io.github.wj0410.chatroom.common.message.PromptMessage;
import io.github.wj0410.chatroom.common.util.SwingUIUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author wangjie
 * @date 2023/10/23
 */
@Slf4j
public class ClientPromptHandler extends SimpleChannelInboundHandler<PromptMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PromptMessage message) {
        log.info("client: 读取到服务端提示消息：{}", message.toString());
        // 聊天室
        SwingUIUtil.drawPromptMsgRecvPane(message, ClientHolder.chatRoomUI.getRecvPane(),
                message.getClientId().equals(ClientHolder.clientInfo.getClientId()) ? 1 : 0);
        // 私聊
        for (Map.Entry<String, PrivateChatUI> entry : ClientHolder.privateChatUIMap.entrySet()) {
            String clientId = entry.getKey();
            if (message.getClientId().equals(clientId)) {
                SwingUIUtil.drawPromptMsgRecvPane(message, entry.getValue().getRecvPane(), 0);
            }
        }
    }

}
