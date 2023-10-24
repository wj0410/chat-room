/*
 * Created by JFormDesigner on Tue Oct 24 13:10:21 CST 2023
 */

package io.github.wj0410.chatroom.client.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

/**
 * @author wangjie
 */
public class ChatRoomUI  {

    private void chatJFrameWindowClosing(WindowEvent e) {
        // TODO add your code here
    }

    private void textArea3KeyPressed(KeyEvent e) {
        // TODO add your code here
    }

    private void onlineListMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        chatJFrame = new JFrame();
        scrollPane2 = new JScrollPane();
        sendArea = new JTextArea();
        scrollPane3 = new JScrollPane();
        onlineList = new JList();
        scrollPane4 = new JScrollPane();
        recvPane = new JTextPane();

        //======== chatJFrame ========
        {
            chatJFrame.setTitle("\u804a\u5929\u5ba4");
            chatJFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    chatJFrameWindowClosing(e);
                }
            });
            Container chatJFrameContentPane = chatJFrame.getContentPane();

            //======== scrollPane2 ========
            {

                //---- sendArea ----
                sendArea.setLineWrap(true);
                sendArea.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        textArea3KeyPressed(e);
                    }
                });
                scrollPane2.setViewportView(sendArea);
            }

            //======== scrollPane3 ========
            {

                //---- onlineList ----
                onlineList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        onlineListMouseClicked(e);
                    }
                });
                scrollPane3.setViewportView(onlineList);
            }

            //======== scrollPane4 ========
            {

                //---- recvPane ----
                recvPane.setEditable(false);
                scrollPane4.setViewportView(recvPane);
            }

            GroupLayout chatJFrameContentPaneLayout = new GroupLayout(chatJFrameContentPane);
            chatJFrameContentPane.setLayout(chatJFrameContentPaneLayout);
            chatJFrameContentPaneLayout.setHorizontalGroup(
                chatJFrameContentPaneLayout.createParallelGroup()
                    .addGroup(chatJFrameContentPaneLayout.createSequentialGroup()
                        .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(chatJFrameContentPaneLayout.createParallelGroup()
                            .addComponent(scrollPane4)
                            .addComponent(scrollPane2))
                        .addContainerGap())
            );
            chatJFrameContentPaneLayout.setVerticalGroup(
                chatJFrameContentPaneLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, chatJFrameContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(chatJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollPane3)
                            .addGroup(chatJFrameContentPaneLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26))
            );
            chatJFrame.setSize(620, 475);
            chatJFrame.setLocationRelativeTo(chatJFrame.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    public JFrame chatJFrame;
    private JScrollPane scrollPane2;
    private JTextArea sendArea;
    private JScrollPane scrollPane3;
    private JList onlineList;
    private JScrollPane scrollPane4;
    public JTextPane recvPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
