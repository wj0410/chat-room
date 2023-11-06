package io.github.wj0410.chatroom.client.ui.swing.custom;

import io.github.wj0410.chatroom.common.constant.CommonConstants;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * 自定义TextPane
 * @author wangjie
 */
public class CustomTextPane extends JTextPane {
    public CustomTextPane() {
        CustomTextPane textPane = this;

        textPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopupMenu(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopupMenu(e);
            }

            private void showPopupMenu(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    JPopupMenu menu = createPopupMenu();
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            private JPopupMenu createPopupMenu() {
                JPopupMenu menu = new JPopupMenu();
                JMenuItem item = new JMenuItem("粘贴");
                item.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textPane.paste();
                    }
                });
                menu.add(item);
                return menu;
            }
        });
    }

    @Override
    public void paste() {
        Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            try {
                Image image = (Image) transferable.getTransferData(DataFlavor.imageFlavor);
                if (image != null) {
                    // 在这里处理获取到的图片，例如插入到文本框中
                    StyledDocument doc = (StyledDocument) getDocument();
                    Style style = doc.addStyle("CustomTextPaneStyle", null);
                    StyleConstants.setIcon(style, new ImageIcon(image));
                    try {
                        doc.insertString(doc.getLength(), CommonConstants.PLACE_HOLDER_IMAGE, style);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                }
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
        } else {
            super.paste();
        }
    }
}