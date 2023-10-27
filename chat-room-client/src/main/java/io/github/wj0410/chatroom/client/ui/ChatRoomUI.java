/*
 * Created by JFormDesigner on Tue Oct 24 13:10:21 CST 2023
 */

package io.github.wj0410.chatroom.client.ui;

import javax.swing.event.*;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.ui.style.OnlineListCellRenderer;
import io.github.wj0410.chatroom.client.util.ClientUtil;
import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.github.wj0410.chatroom.common.util.UIUtil;

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

    /**
     * 刷新在线列表
     *
     * @param clientModelLinkedList
     */
    public void flushClientOnlineList(LinkedList<ClientModel> clientModelLinkedList) {
        DefaultListModel<ClientModel> model = new DefaultListModel<>();
        for (ClientModel clientModel : clientModelLinkedList) {
            model.addElement(clientModel);
        }
        this.onlineList.setModel(model);
        this.onlineList.setCellRenderer(new OnlineListCellRenderer());
    }

    private void textArea3KeyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
            // ctrl+回车切换下一行
            e.consume(); // 停止事件的默认行为
            sendArea.append("\n");
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // 回车
            // 发送并清空发送域
            e.consume(); // 停止事件的默认行为
            String sendContent = sendArea.getText();
            // 发送消息
            ClientUtil.sendNormalMessage(ClientHolder.clientInfo.getCtx(), sendContent, null);
            // 清空发送框
            sendArea.setText("");
        }
    }

    private void onlineListMouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            // 处理双击事件
            int index = onlineList.locationToIndex(e.getPoint());
            if (index != -1) {
                ClientModel clientModel = (ClientModel) onlineList.getModel().getElementAt(index);
                if (!clientModel.getClientId().equals(ClientHolder.clientInfo.getClientId())) {
                    // TODO 打开私聊对话框
                    UIUtil.alertSuccess("开发中！");
                }
            }
        }
    }

    private void onlineListSelectChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int index = onlineList.getSelectedIndex();
            if (index != -1) {
                Object elementAt = onlineList.getModel().getElementAt(index);
                ClientModel clientModel = (ClientModel) elementAt;
                //判断自己还是其他人
                if (clientModel.getClientId().equals(ClientHolder.clientInfo.getClientId())) {
                    // 如果选中的是带有标签（即自己），则清除选择
                    onlineList.clearSelection();
                }
            }
        }
    }

    public JTextPane getRecvPane() {
        return recvPane;
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
            chatJFrame.setPreferredSize(new Dimension(1083, 824));
            chatJFrame.setMinimumSize(new Dimension(950, 750));
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
                sendArea.setBackground(new Color(0xf3f3f3));
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
                onlineList.addListSelectionListener(e -> onlineListSelectChanged(e));
                scrollPane3.setViewportView(onlineList);
            }

            //======== scrollPane4 ========
            {

                //---- recvPane ----
                recvPane.setEditable(false);
                recvPane.setBackground(new Color(0xf3f3f3));
                scrollPane4.setViewportView(recvPane);
            }

            GroupLayout chatJFrameContentPaneLayout = new GroupLayout(chatJFrameContentPane);
            chatJFrameContentPane.setLayout(chatJFrameContentPaneLayout);
            chatJFrameContentPaneLayout.setHorizontalGroup(
                chatJFrameContentPaneLayout.createParallelGroup()
                    .addGroup(chatJFrameContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
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
    private JTextPane recvPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
