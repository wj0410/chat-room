/*
 * Created by JFormDesigner on Tue Oct 24 13:10:21 CST 2023
 */

package io.github.wj0410.chatroom.client.ui;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.util.ClientUtil;
import io.github.wj0410.chatroom.common.model.ClientModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

/**
 * @author wangjie
 */
public class ChatRoomUI {

    public ChatRoomUI() {
        this.initComponents();
        ClientHolder.chatRoomUI = this;
    }

    public void show() {
        this.chatJFrame.setVisible(true);
    }

    public void hide() {
        this.chatJFrame.setVisible(false);
    }

    public void flushClientOnlineList(LinkedList<ClientModel> clientModelLinkedList) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (ClientModel clientModel : clientModelLinkedList) {
            model.addElement(ClientUtil.formatClientAccount(clientModel));
        }
        this.onlineList.setModel(model);
    }

    private void textArea3KeyPressed(KeyEvent e) {
        // TODO add your code here
    }

    private void onlineListMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    /**
     * 关闭聊天室
     * 程序退出
     *
     * @param e
     */
    private void chatRoomJFrameWindowClosing(WindowEvent e) {
        ClientHolder.nettyClient.shutDown();
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
                    chatRoomJFrameWindowClosing(e);
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
                                    .addContainerGap()
                                    .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(chatJFrameContentPaneLayout.createParallelGroup()
                                            .addComponent(scrollPane4)
                                            .addComponent(scrollPane2))
                                    .addContainerGap())
            );
            chatJFrameContentPaneLayout.setVerticalGroup(
                    chatJFrameContentPaneLayout.createParallelGroup()
                            .addGroup(chatJFrameContentPaneLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(chatJFrameContentPaneLayout.createParallelGroup()
                                            .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                                            .addGroup(chatJFrameContentPaneLayout.createSequentialGroup()
                                                    .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap())
            );
            chatJFrame.setSize(640, 490);
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
