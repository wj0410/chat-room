package io.github.wj0410.chatroom.client.handler;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.ui.swing.PrivateChatUI;
import io.github.wj0410.chatroom.client.ui.swing.model.OnlineModel;
import io.github.wj0410.chatroom.client.util.ClientUtil;
import io.github.wj0410.chatroom.client.util.TrayUtil;
import io.github.wj0410.chatroom.common.message.NormalMessage;
import io.github.wj0410.chatroom.common.util.SwingUIUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.swing.*;
import java.util.List;


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
        System.out.println(String.format("客户端收到服务端转发消息：%s", normalMessage.toString()));
        // 在UI消息区域显示消息
        List<String> targetClientIds = normalMessage.getTargetClientIds();
        String fromClientId = normalMessage.getFromClientId();
        if (CollectionUtils.isEmpty(targetClientIds)) {
            // 聊天室
            int self = fromClientId.equals(ClientHolder.clientInfo.getClientId()) ? 1 : 0;
            SwingUIUtil.drawRecvPane(normalMessage, ClientHolder.chatRoomUI.getRecvPane(), self);
            if (!ClientHolder.chatRoomUI.getSendPane().isFocusOwner()) {
                // 如果没有聚焦，绘制系统托盘
                TrayUtil.noticeTray(true);
            }
        } else {
            // TODO 这里区分聊天室和私聊消息需要优化一下，目前暂时只有这两种情况
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
                    TrayUtil.noticeTray(true);
                }
            }
        }
    }

}
