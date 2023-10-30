package io.github.wj0410.chatroom.client.util;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.common.message.BindMessage;
import io.github.wj0410.chatroom.common.message.NormalMessage;
import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.common.util.DateUtil;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.github.wj0410.chatroom.common.util.UIUtil;
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
     * @param clientModel
     * @return
     */
    public static String formatClientAccount(ClientModel clientModel) {
        return clientModel.getUserName();
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
     * @param ctx        客户端与服务端的通道
     * @param msg        消息内容
     * @param targetList 为NULL则代表发送给所有客户端
     */
    public static void sendNormalMessage(ChannelHandlerContext ctx, String msg, List<String> targetList) {
        NormalMessage message = new NormalMessage();
        message.setTimestamp(System.currentTimeMillis());
        message.setMsg(msg);
        message.setFromAccount(ClientHolder.clientInfo.getAccount());
        message.setFromClientId(ClientHolder.clientInfo.getClientId());
        message.setFromUserName(ClientHolder.clientInfo.getUserName());
        message.setTargetClientIds(targetList);
        String normalMessageJsonStr = MessageUtil.createNormalMessageJsonStr(message);
        ctx.writeAndFlush(MessageUtil.convert2ByteBuf(normalMessageJsonStr));
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
        // 创建段落样式，并设置行间距为 20 像素
        MutableAttributeSet marginStyle = new SimpleAttributeSet();
        MutableAttributeSet lineSpacingStyle = new SimpleAttributeSet();
        // 设置行间距，参数值为行高的倍数
        StyleConstants.setLineSpacing(lineSpacingStyle, 0.5f);
        StyleConstants.setAlignment(marginStyle, self == 1 ? StyleConstants.ALIGN_RIGHT : StyleConstants.ALIGN_LEFT);
        try {
            int marginStart = doc.getLength();
            // 用户名
            UIUtil.buildUserNameStyle(doc, self);
            doc.insertString(doc.getLength(), userNameContent, doc.getStyle(UIUtil.USER_NAME_STYLE_NAME));
            // 时间
            UIUtil.buildTimestampStyle(doc);
            doc.insertString(doc.getLength(), timestampContent, doc.getStyle(UIUtil.TIMESTAMP_STYLE_NAME));

            int lineSpacingStart = doc.getLength();
            // 消息
            UIUtil.buildMsgStyle(doc, self);
            doc.insertString(doc.getLength(), msgContent + "\n", doc.getStyle(UIUtil.MSG_STYLE_NAME));
            // 将段落样式应用到指定范围内的文本
            doc.setParagraphAttributes(marginStart, doc.getLength() - marginStart, marginStyle, false);
            doc.setParagraphAttributes(lineSpacingStart, doc.getLength() - lineSpacingStart, lineSpacingStyle, false);

            recvPane.setDocument(doc);
            recvPane.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }
}
