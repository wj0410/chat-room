package io.github.wj0410.chatroom.client.util;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.ui.model.OnlineModel;
import io.github.wj0410.chatroom.common.message.BindMessage;
import io.github.wj0410.chatroom.common.message.NormalMessage;
import io.github.wj0410.chatroom.common.message.WelcomeMessage;
import io.github.wj0410.chatroom.common.util.DateUtil;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.github.wj0410.chatroom.common.util.SwingUIUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.text.*;
import java.util.List;

/**
 * @author wangjie
 * @date 2023/10/26
 */
@Slf4j
public class ClientUtil {
    /**
     * 格式化展示在线列表
     *
     * @param onlineModel
     * @return
     */
    public static String formatClientAccount(OnlineModel onlineModel) {
        return onlineModel.getUserName();
    }

    /**
     * 给服务端发送绑定消息
     *
     * @param ctx
     * @param bindMessage
     */
    public static void sendBindMessage(ChannelHandlerContext ctx, BindMessage bindMessage) {
        String bindMessageJsonStr = MessageUtil.createBindMessageJsonStr(bindMessage);
        ctx.writeAndFlush(MessageUtil.convert2ByteBuf(bindMessageJsonStr));
        log.info("客户端向服务端发送绑定ChannelId请求，{}", bindMessageJsonStr);
    }

    /**
     * 给服务端发送普通消息
     *
     * @param ctx              客户端与服务端的通道
     * @param msg              消息内容
     * @param targetClientList 为NULL则代表发送给所有客户端
     */
    public static NormalMessage sendNormalMessage(ChannelHandlerContext ctx, String msg, List<String> targetClientList) {
        NormalMessage message = new NormalMessage();
        message.setTimestamp(System.currentTimeMillis());
        message.setMsg(msg);
        message.setFromAccount(ClientHolder.clientInfo.getAccount());
        message.setFromClientId(ClientHolder.clientInfo.getClientId());
        message.setFromUserName(ClientHolder.clientInfo.getUserName());
        message.setTargetClientIds(targetClientList);
        String normalMessageJsonStr = MessageUtil.createNormalMessageJsonStr(message);
        ctx.writeAndFlush(MessageUtil.convert2ByteBuf(normalMessageJsonStr));
        return message;
    }

    /**
     * 回显接收到的服务器消息
     *
     * @param welcomeMessage
     * @param recvPane
     */
    public static void drawRecvArea(WelcomeMessage welcomeMessage, JTextPane recvPane, int self) {
        StyledDocument doc = recvPane.getStyledDocument();
        String timestampContent = "\n" + DateUtil.convertTimestampToString(welcomeMessage.getTimestamp()) + "\n";
        String welcomeContent = self == 1 ? "您已进入聊天室" : welcomeMessage.getMsg();
        welcomeContent += "\n";
        // 创建段落样式
        MutableAttributeSet alignStyle = new SimpleAttributeSet();
        // 居中
        StyleConstants.setAlignment(alignStyle, StyleConstants.ALIGN_CENTER);
        try {
            int alignStart = doc.getLength();
            SwingUIUtil.buildTimestampStyle(doc);
            doc.insertString(doc.getLength(), timestampContent, doc.getStyle(SwingUIUtil.TIMESTAMP_STYLE_NAME));

            SwingUIUtil.buildWelcomeStyle(doc);
            doc.insertString(doc.getLength(), welcomeContent, doc.getStyle(SwingUIUtil.WELCOME_STYLE_NAME));

            // 将段落样式应用到指定范围内的文本
            doc.setParagraphAttributes(alignStart, doc.getLength() - alignStart, alignStyle, false);

            recvPane.setDocument(doc);
            recvPane.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回显接收到的服务器消息
     *
     * @param normalMessage
     * @param recvPane
     */
    public static void drawRecvArea(NormalMessage normalMessage, JTextPane recvPane, int self) {
        StyledDocument doc = recvPane.getStyledDocument();
        String userNameContent = normalMessage.getFromUserName();
        String timestampContent = "\n" + DateUtil.convertTimestampToString(normalMessage.getTimestamp()) + "\n";
        String msgContent = normalMessage.getMsg();
        // 创建段落样式
        MutableAttributeSet alignStyle = new SimpleAttributeSet();
        MutableAttributeSet spaceBelowStyle = new SimpleAttributeSet();
        // 靠左/右对齐
        StyleConstants.setAlignment(alignStyle, self == 1 ? StyleConstants.ALIGN_RIGHT : StyleConstants.ALIGN_LEFT);
        // 每条消息之间间隔
        StyleConstants.setSpaceBelow(spaceBelowStyle, 7.0f);

        try {
            int alignStart = doc.getLength();
            // 用户名
            SwingUIUtil.buildUserNameStyle(doc, self);
            doc.insertString(doc.getLength(), userNameContent, doc.getStyle(SwingUIUtil.USER_NAME_STYLE_NAME));

            // 时间
            SwingUIUtil.buildTimestampStyle(doc);
            doc.insertString(doc.getLength(), timestampContent, doc.getStyle(SwingUIUtil.TIMESTAMP_STYLE_NAME));

            int spaceBelowStart = doc.getLength();
            // 消息
            SwingUIUtil.buildMsgStyle(doc, self);
            doc.insertString(doc.getLength(), msgContent + "\n", doc.getStyle(SwingUIUtil.MSG_STYLE_NAME));
            // 将段落样式应用到指定范围内的文本
            doc.setParagraphAttributes(alignStart, doc.getLength() - alignStart, alignStyle, false);
            doc.setParagraphAttributes(spaceBelowStart, doc.getLength() - spaceBelowStart, spaceBelowStyle, false);

            recvPane.setDocument(doc);
            recvPane.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }
}
