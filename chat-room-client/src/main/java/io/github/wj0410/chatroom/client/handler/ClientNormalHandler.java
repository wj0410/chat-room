package io.github.wj0410.chatroom.client.handler;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.ui.swing.PrivateChatUI;
import io.github.wj0410.chatroom.client.ui.swing.model.OnlineModel;
import io.github.wj0410.chatroom.client.util.TrayUtil;
import io.github.wj0410.chatroom.common.enums.ChatType;
import io.github.wj0410.chatroom.common.message.NormalMessage;
import io.github.wj0410.chatroom.common.util.SwingUIUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


/**
 * @author wangjie
 * @date 2023/10/23
 */
@Slf4j
public class ClientNormalHandler extends SimpleChannelInboundHandler<NormalMessage> {

    /**
     * 接收服务端转发消息
     *
     * @param ctx
     * @param normalMessage
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NormalMessage normalMessage) {
        log.info("客户端收到服务端转发消息：{}", normalMessage.toString());
        // 在UI消息区域显示消息
        String fromClientId = normalMessage.getFromClientId();
        ChatType chatType = normalMessage.getChatType();
        if (chatType.equals(ChatType.PUBLIC)) {
            // 聊天室
            int self = fromClientId.equals(ClientHolder.clientInfo.getClientId()) ? 1 : 0;
            SwingUIUtil.drawRecvPane(normalMessage, ClientHolder.chatRoomUI.getRecvPane(), self);
            if (!ClientHolder.chatRoomUI.getSendPane().isFocusOwner()) {
                // 如果没有聚焦，绘制系统托盘
                TrayUtil.noticeTray(true, ClientHolder.clientInfo.getAccount());
            }
        } else if (chatType.equals(ChatType.PRIVATE)) {
            // 私聊消息
            PrivateChatUI privateChatUI = ClientHolder.privateChatUIMap.get(fromClientId);
            if (privateChatUI == null) {
                OnlineModel onlineModel = ClientHolder.chatRoomUI.getOnlineModel(fromClientId);
                if (onlineModel == null) {
                    log.error("ERROR: clientId:{} 不在chatRoomUI.onlineList中！", fromClientId);
                    return;
                }
                privateChatUI = new PrivateChatUI(onlineModel);
            }
            SwingUIUtil.drawRecvPane(normalMessage, privateChatUI.getRecvPane(), 0);
            if (!privateChatUI.isShow()) {
                // 私聊窗口未开启
                // 未读消息+1
                ClientHolder.chatRoomUI.flushUnread(fromClientId);
                // 如果聊天室也没有鼠标焦点
                if (!ClientHolder.chatRoomUI.getSendPane().isFocusOwner()) {
                    // 如果没有聚焦，绘制系统托盘
                    TrayUtil.noticeTray(true, ClientHolder.clientInfo.getAccount());
                }
            }
        }
    }

}
