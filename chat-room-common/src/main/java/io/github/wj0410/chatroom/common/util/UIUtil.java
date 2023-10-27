package io.github.wj0410.chatroom.common.util;


import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

/**
 * @author wangjie
 * @date 2023/10/24
 */
public class UIUtil {
    // 绿色
    public static final Color GREEN_COLOR = new Color(51, 153, 102);
    // 灰色
    public static final Color GREY_COLOR = new Color(211, 211, 211);
    public static final Color GREY_COLOR2 = new Color(184, 184, 184);
    // 浅绿色
    public static final Color LIGHT_GREEN_COLOR = new Color(149, 236, 105);

    public static final String USER_NAME_STYLE_NAME = "recvUserNameStyle";
    public static final String TIMESTAMP_STYLE_NAME = "recvTimestampStyle";
    public static final String MSG_STYLE_NAME = "recvMsgStyle";
    public static final String SERVER_CONSOLE_STYLE_NAME = "serverConsole";

    public static void alertSuccess(String str) {
        JOptionPane.showMessageDialog(null, str);
    }

    public static void alertError(String str) {
        JOptionPane.showMessageDialog(null, str, "提示", JOptionPane.ERROR_MESSAGE);
    }

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
     * @param align     对齐方式
     */
    public static void createStyle(String styleName, StyledDocument doc, int fontSize, int bold,
                                   int italic, int underline, Color color, String fontName, int align, Color bgColor) {
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
        // 设置对齐方式
        StyleConstants.setAlignment(style, align);
        // 背景颜色
        if (bgColor != null) {
            StyleConstants.setBackground(style, bgColor);
        }
    }

    public static void buildServerConsoleStyle(StyledDocument doc) {
        UIUtil.createStyle(SERVER_CONSOLE_STYLE_NAME, doc, 15, 0, 0, 0, Color.black, "宋体", StyleConstants.ALIGN_LEFT, null);
    }

    public static void buildUserNameStyle(StyledDocument doc) {
        createStyle(USER_NAME_STYLE_NAME, doc, 15, 0, 0, 0, Color.black, "Arial", StyleConstants.ALIGN_LEFT, null);
    }

    public static void buildTimestampStyle(StyledDocument doc) {
        createStyle(TIMESTAMP_STYLE_NAME, doc, 15, 0, 0, 0, GREY_COLOR2, "Arial", StyleConstants.ALIGN_LEFT, null);
    }

    public static void buildMsgStyle(StyledDocument doc) {
        createStyle(MSG_STYLE_NAME, doc, 14, 0, 0, 0, Color.black, "Arial", StyleConstants.ALIGN_LEFT, LIGHT_GREEN_COLOR);
    }
}
