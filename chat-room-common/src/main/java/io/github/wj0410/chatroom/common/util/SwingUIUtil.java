package io.github.wj0410.chatroom.common.util;


import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * @author wangjie
 * @date 2023/10/24
 */
public class SwingUIUtil {
    // 绿色
    public static final Color GREEN_COLOR = new Color(51, 153, 102);
    // 浅绿色
    public static final Color GREEN_COLOR_LIGHT = new Color(149, 236, 105);
    // 灰色
    public static final Color GREY_COLOR = new Color(211, 211, 211);
    public static final Color GREY_COLOR2 = new Color(184, 184, 184);
    public static final String USER_NAME_STYLE_NAME = "recvUserNameStyle";
    public static final String TIMESTAMP_STYLE_NAME = "recvTimestampStyle";
    public static final String MSG_STYLE_NAME = "recvMsgStyle";
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
                                   int italic, int underline, Color color, String fontName, Color bgColor) {
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
    }

    public static void alertSuccess(String str) {
        JOptionPane.showMessageDialog(null, str);
    }

    public static void alertError(String str) {
        JOptionPane.showMessageDialog(null, str, "提示", JOptionPane.ERROR_MESSAGE);
    }

    public static void buildServerConsoleStyle(StyledDocument doc) {
        SwingUIUtil.createStyle(SwingUIUtil.SERVER_CONSOLE_STYLE_NAME, doc, 15, 0, 0, 0, Color.RED, "宋体", null);
    }

    public static void buildUserNameStyle(StyledDocument doc, int self) {
        createStyle(USER_NAME_STYLE_NAME, doc, 15, 1, 0, 0, self == 1 ? GREEN_COLOR : Color.black, "Arial", null);
    }

    public static void buildTimestampStyle(StyledDocument doc) {
        createStyle(TIMESTAMP_STYLE_NAME, doc, 13, 0, 0, 0, GREY_COLOR2, "Arial", null);
    }

    public static void buildWelcomeStyle(StyledDocument doc) {
        createStyle(WELCOME_STYLE_NAME, doc, 13, 0, 0, 0, GREY_COLOR2, "Arial", null);
    }

    public static void buildMsgStyle(StyledDocument doc, int self) {
        createStyle(MSG_STYLE_NAME, doc, 14, 0, 0, 0, Color.black, "Arial", self == 1 ? GREEN_COLOR_LIGHT : Color.WHITE);
    }
}
