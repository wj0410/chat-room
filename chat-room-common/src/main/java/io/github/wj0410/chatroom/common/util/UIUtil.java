package io.github.wj0410.chatroom.common.util;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

/**
 * @author wangjie
 * @date 2023/10/24
 */
public class UIUtil {

    public static void alertError(String str) {
        JOptionPane.showMessageDialog(null, str, "提示", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * 在控制台上输出内容
     *
     * @param str
     */
    public static void drawConsole(JTextPane consolePane, String str) {
        StyledDocument styledDoc = consolePane.getStyledDocument();
        createStyle("console", styledDoc, 15, 0, 1, 0, Color.RED, "宋体");
        consolePane.setDocument(styledDoc);
        try {
            styledDoc.insertString(styledDoc.getLength(), str + "\n", styledDoc.getStyle("console"));
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createStyle(String style, StyledDocument doc, int size, int bold, int italic, int underline, Color color, String fontName) {
        Style sys = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        // 先删除这种Style,假使他存在
        try {
            doc.removeStyle(style);
        } catch (Exception e) {
        }
        // 加入
        Style s = doc.addStyle(style, sys);
        // 大小
        StyleConstants.setFontSize(s, size);
        // 粗体
        StyleConstants.setBold(s, (bold == 1) ? true : false);
        // 斜体
        StyleConstants.setItalic(s, (italic == 1) ? true : false);
        // 下划线
        StyleConstants.setUnderline(s, (underline == 1) ? true : false);
        // 颜色
        StyleConstants.setForeground(s, color);
        // 字体
        StyleConstants.setFontFamily(s, fontName);
    }
}
