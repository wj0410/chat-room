package io.github.wj0410.chatroom.common.util;


import io.github.wj0410.chatroom.common.constant.CommonConstants;
import io.github.wj0410.chatroom.common.enums.PromptType;
import io.github.wj0410.chatroom.common.message.NormalMessage;
import io.github.wj0410.chatroom.common.message.PromptMessage;
import io.github.wj0410.chatroom.common.model.MessageContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author wangjie
 * @date 2023/10/24
 */
public class SwingUIUtil {
    public static final int IMAGE_MAX_WIDTH = 700;
    public static final int IMAGE_MAX_HEIGHT = 400;
    public static final float SPACE_BELOW = 7.0f;
    // 绿色
    public static final Color GREEN_COLOR = new Color(51, 153, 102);
    // 浅绿色
    public static final Color GREEN_COLOR_LIGHT = new Color(149, 236, 105);
    // 灰色
    public static final Color GREY_COLOR = new Color(211, 211, 211);
    public static final Color GREY_COLOR2 = new Color(184, 184, 184);
    /**
     * style
     */
    public static final String USER_NAME_STYLE_NAME = "recvUserNameStyle";
    public static final String TIMESTAMP_STYLE_NAME = "recvTimestampStyle";
    public static final String MSG_STYLE_NAME = "recvMsgStyle";
    public static final String IMAGE_STYLE_NAME = "recvImageStyle";
    public static final String WELCOME_STYLE_NAME = "recvWelcomeStyle";
    public static final String SERVER_CONSOLE_STYLE_NAME = "serverConsole";

    /**
     * 创建样式
     *
     * @param styleName 样式名称
     * @param doc       文档对象
     * @param fontSize  字体大小
     * @param bold      是否加粗
     * @param italic    是否斜体
     * @param underline 是否下划线
     * @param color     字体颜色
     * @param fontName  字体名称
     */
    public static void createStyle(String styleName, StyledDocument doc, int fontSize, int bold,
                                   int italic, int underline, Color color, String fontName, Color bgColor, Icon icon) {
        // 先删除这种Style,假使他存在
        try {
            doc.removeStyle(styleName);
        } catch (Exception e) {
        }
        // 加入
        Style style = doc.addStyle(styleName, StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE));
        // 大小
        StyleConstants.setFontSize(style, fontSize);
        // 粗体
        StyleConstants.setBold(style, bold != 0);
        // 斜体
        StyleConstants.setItalic(style, italic != 0);
        // 下划线
        StyleConstants.setUnderline(style, (underline != 0));
        // 颜色
        StyleConstants.setForeground(style, color);
        // 字体
        StyleConstants.setFontFamily(style, fontName);
        // 背景颜色
        if (bgColor != null) {
            StyleConstants.setBackground(style, bgColor);
        }
        // 图标
        if (icon != null) {
            StyleConstants.setIcon(style, icon);
        }
    }

    public static void alertSuccess(String str) {
        JOptionPane.showMessageDialog(null, str);
    }

    public static void alertError(String str) {
        JOptionPane.showMessageDialog(null, str, "提示", JOptionPane.ERROR_MESSAGE);
    }

    public static void buildServerConsoleStyle(StyledDocument doc) {
        SwingUIUtil.createStyle(SwingUIUtil.SERVER_CONSOLE_STYLE_NAME, doc, 15, 0, 0, 0, Color.RED, "宋体", null, null);
    }

    public static void buildUserNameStyle(StyledDocument doc, int self) {
        createStyle(USER_NAME_STYLE_NAME, doc, 15, 1, 0, 0, self == 1 ? GREEN_COLOR : Color.black, "Arial", null, null);
    }

    public static void buildTimestampStyle(StyledDocument doc) {
        createStyle(TIMESTAMP_STYLE_NAME, doc, 13, 0, 0, 0, GREY_COLOR2, "Arial", null, null);
    }

    public static void buildWelcomeStyle(StyledDocument doc) {
        createStyle(WELCOME_STYLE_NAME, doc, 13, 0, 0, 0, GREY_COLOR2, "Arial", null, null);
    }

    public static void buildMsgStyle(StyledDocument doc, int self) {
        createStyle(MSG_STYLE_NAME, doc, 14, 0, 0, 0, Color.black, "Arial", self == 1 ? GREEN_COLOR_LIGHT : Color.WHITE, null);
    }

    public static void buildImageStyle(StyledDocument doc, Icon icon, int self) {
        createStyle(IMAGE_STYLE_NAME, doc, 14, 0, 0, 0, Color.black, "Arial", self == 1 ? GREEN_COLOR_LIGHT : Color.WHITE, icon);
    }

    /**
     * 回显接收到的服务器消息
     *
     * @param normalMessage
     * @param recvPane
     */
    public static void drawRecvPane(NormalMessage normalMessage, JTextPane recvPane, int self) {
        List<MessageContainer> msgList = normalMessage.getMsg();
        for (MessageContainer msg : msgList) {
            // 绘制头部
            drawRecvHead(normalMessage.getFromNickName(), normalMessage.getTimestamp(), recvPane, self);
            // 绘制消息内容
            switch (msg.getType()) {
                case TEXT:
                    drawRecvTextMessage(msg.getText(), recvPane, self);
                    break;
                case IMAGE:
                    drawRecvImageMessage(msg.getImageData(), recvPane, self);
                    break;
                default:
                    throw new IllegalStateException("Unexpected messageContainerType: " + msg.getType());
            }
        }
    }

    private static void drawRecvHead(String userNameContent, long timestamp, JTextPane recvPane, int self) {
        StyledDocument doc = recvPane.getStyledDocument();
        String timestampContent = "\n" + DateUtil.convertTimestampToString(timestamp) + "\n";
        // 创建段落样式
        MutableAttributeSet alignStyle = new SimpleAttributeSet();
        MutableAttributeSet spaceBelowStyle = new SimpleAttributeSet();
        // 靠左/右对齐
        StyleConstants.setAlignment(alignStyle, self == 1 ? StyleConstants.ALIGN_RIGHT : StyleConstants.ALIGN_LEFT);
        // 每条消息之间间隔
        StyleConstants.setSpaceBelow(spaceBelowStyle, SPACE_BELOW);
        try {
            int alignStart = doc.getLength();
            // 用户名
            SwingUIUtil.buildUserNameStyle(doc, self);
            doc.insertString(doc.getLength(), userNameContent, doc.getStyle(SwingUIUtil.USER_NAME_STYLE_NAME));

            // 时间
            SwingUIUtil.buildTimestampStyle(doc);
            doc.insertString(doc.getLength(), timestampContent, doc.getStyle(SwingUIUtil.TIMESTAMP_STYLE_NAME));

            // 靠左/靠右
            doc.setParagraphAttributes(alignStart, doc.getLength() - alignStart, alignStyle, false);
            recvPane.setDocument(doc);
            recvPane.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    private static void drawRecvImageMessage(byte[] imageData, JTextPane recvPane, int self) {
        StyledDocument doc = recvPane.getStyledDocument();
        // 创建段落样式
        MutableAttributeSet alignStyle = new SimpleAttributeSet();
        MutableAttributeSet spaceBelowStyle = new SimpleAttributeSet();
        // 靠左/右对齐
        StyleConstants.setAlignment(alignStyle, self == 1 ? StyleConstants.ALIGN_RIGHT : StyleConstants.ALIGN_LEFT);
        // 每条消息之间间隔
        StyleConstants.setSpaceBelow(spaceBelowStyle, SPACE_BELOW);
        try {
            int start = doc.getLength();
            // 创建BufferedImage对象
            BufferedImage image = null;
            try {
                image = ImageIO.read(new ByteArrayInputStream(imageData));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 图片
            ImageIcon icon = new ImageIcon(image);
            SwingUIUtil.buildImageStyle(doc, ImageUtil.getScaledIcon(icon, IMAGE_MAX_WIDTH, IMAGE_MAX_HEIGHT), self);
            doc.insertString(doc.getLength(), CommonConstants.PLACE_HOLDER_IMAGE + "\n", doc.getStyle(SwingUIUtil.IMAGE_STYLE_NAME));

            // 将段落样式应用到指定范围内的文本
            // 靠左/靠右
            doc.setParagraphAttributes(start, doc.getLength() - start, alignStyle, false);
            // 每条消息之间间隔
            doc.setParagraphAttributes(start, doc.getLength() - start, spaceBelowStyle, false);

            recvPane.setDocument(doc);
            recvPane.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    private static void drawRecvTextMessage(String msgContent, JTextPane recvPane, int self) {
        StyledDocument doc = recvPane.getStyledDocument();
        // 创建段落样式
        MutableAttributeSet alignStyle = new SimpleAttributeSet();
        MutableAttributeSet spaceBelowStyle = new SimpleAttributeSet();
        // 靠左/右对齐
        StyleConstants.setAlignment(alignStyle, self == 1 ? StyleConstants.ALIGN_RIGHT : StyleConstants.ALIGN_LEFT);
        // 每条消息之间间隔
        StyleConstants.setSpaceBelow(spaceBelowStyle, 7.0f);
        try {
            int start = doc.getLength();
            // 消息
            SwingUIUtil.buildMsgStyle(doc, self);
            doc.insertString(doc.getLength(), msgContent + "\n", doc.getStyle(SwingUIUtil.MSG_STYLE_NAME));
            // 将段落样式应用到指定范围内的文本
            // 靠左/靠右
            doc.setParagraphAttributes(start, doc.getLength() - start, alignStyle, false);
            // 每条消息之间间隔
            doc.setParagraphAttributes(start, doc.getLength() - start, spaceBelowStyle, false);

            recvPane.setDocument(doc);
            recvPane.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 回显接收到的服务器消息
     *
     * @param promptMessage
     * @param recvPane
     */
    public static void drawPromptMsgRecvPane(PromptMessage promptMessage, JTextPane recvPane, int self) {
        StyledDocument doc = recvPane.getStyledDocument();
        String timestampContent = "\n" + DateUtil.convertTimestampToString(promptMessage.getTimestamp()) + "\n";
        String promptContent = "";
        if (promptMessage.getPromptType().equals(PromptType.WELCOME)) {
            promptContent = self == 1 ? CommonConstants.WELCOME_PROMPT_SELF : promptMessage.getMsg();
        } else {
            promptContent = promptMessage.getMsg();
        }
        promptContent += "\n";
        // 创建段落样式
        MutableAttributeSet alignStyle = new SimpleAttributeSet();
        // 居中
        StyleConstants.setAlignment(alignStyle, StyleConstants.ALIGN_CENTER);
        try {
            int alignStart = doc.getLength();
            SwingUIUtil.buildTimestampStyle(doc);
            doc.insertString(doc.getLength(), timestampContent, doc.getStyle(SwingUIUtil.TIMESTAMP_STYLE_NAME));

            SwingUIUtil.buildWelcomeStyle(doc);
            doc.insertString(doc.getLength(), promptContent, doc.getStyle(SwingUIUtil.WELCOME_STYLE_NAME));

            // 将段落样式应用到指定范围内的文本
            doc.setParagraphAttributes(alignStart, doc.getLength() - alignStart, alignStyle, false);

            recvPane.setDocument(doc);
            recvPane.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }


}
