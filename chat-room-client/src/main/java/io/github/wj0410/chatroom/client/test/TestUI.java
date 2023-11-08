/*
 * Created by JFormDesigner on Tue Nov 07 14:12:25 CST 2023
 */

package io.github.wj0410.chatroom.client.test;

import io.github.wj0410.chatroom.client.ClientApplication;
import io.github.wj0410.chatroom.common.util.ImageUtil;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

/**
 * @author wangjie
 */
public class TestUI  {
    TestUI(){
        initComponents();
        this.privateChatJFrame.setVisible(true);
    }

    public static void main(String[] args) {
        TestUI testUI = new TestUI();
        // 读取GIF文件
        URL gifFileUrl = ClientApplication.class.getResource("/1.gif");
        ImageIcon imageIcon = new ImageIcon(gifFileUrl);
        JLabel label = new JLabel(imageIcon);
        // 将 JLabel 添加到 JTextPane 中
        testUI.recvPane.insertComponent(label);

    }

    private void sendPaneKeyPressed(KeyEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        privateChatJFrame = new JFrame();
        scrollPane4 = new JScrollPane();
        recvPane = new JTextPane();
        scrollPane2 = new JScrollPane();
        sendPane = new JTextPane();
        button2 = new JButton();

        //======== privateChatJFrame ========
        {
            privateChatJFrame.setPreferredSize(new Dimension(700, 824));
            privateChatJFrame.setMinimumSize(new Dimension(750, 650));
            Container privateChatJFrameContentPane = privateChatJFrame.getContentPane();

            //======== scrollPane4 ========
            {
                scrollPane4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                //---- recvPane ----
                recvPane.setBackground(new Color(0xf3f3f3));
                recvPane.setMargin(new Insets(5, 5, 5, 5));
                recvPane.setMinimumSize(new Dimension(426, 248));
                recvPane.setPreferredSize(new Dimension(426, 248));
                recvPane.setEditable(false);
                scrollPane4.setViewportView(recvPane);
            }

            //======== scrollPane2 ========
            {

                //---- sendPane ----
                sendPane.setBackground(new Color(0xf3f3f3));
                sendPane.setMargin(new Insets(5, 5, 5, 5));
                sendPane.setMinimumSize(new Dimension(426, 248));
                sendPane.setPreferredSize(new Dimension(426, 248));
                sendPane.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        sendPaneKeyPressed(e);
                    }
                });
                scrollPane2.setViewportView(sendPane);
            }

            //---- button2 ----
            button2.setText("text");

            GroupLayout privateChatJFrameContentPaneLayout = new GroupLayout(privateChatJFrameContentPane);
            privateChatJFrameContentPane.setLayout(privateChatJFrameContentPaneLayout);
            privateChatJFrameContentPaneLayout.setHorizontalGroup(
                privateChatJFrameContentPaneLayout.createParallelGroup()
                    .addGroup(privateChatJFrameContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(privateChatJFrameContentPaneLayout.createParallelGroup()
                            .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                            .addGroup(privateChatJFrameContentPaneLayout.createSequentialGroup()
                                .addComponent(button2)
                                .addGap(0, 396, Short.MAX_VALUE))
                            .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
                        .addContainerGap())
            );
            privateChatJFrameContentPaneLayout.setVerticalGroup(
                privateChatJFrameContentPaneLayout.createParallelGroup()
                    .addGroup(privateChatJFrameContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );
            privateChatJFrame.setSize(460, 475);
            privateChatJFrame.setLocationRelativeTo(privateChatJFrame.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    public JFrame privateChatJFrame;
    private JScrollPane scrollPane4;
    private JTextPane recvPane;
    private JScrollPane scrollPane2;
    private JTextPane sendPane;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
