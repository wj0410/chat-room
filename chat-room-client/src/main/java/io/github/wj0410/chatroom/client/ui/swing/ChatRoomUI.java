/*
 * Created by JFormDesigner on Tue Oct 24 13:10:21 CST 2023
 */

package io.github.wj0410.chatroom.client.ui.swing;


import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.ui.swing.model.OnlineModel;
import io.github.wj0410.chatroom.client.ui.swing.style.OnlineListCellRenderer;
import io.github.wj0410.chatroom.client.ui.swing.style.WrapEditorKit;
import io.github.wj0410.chatroom.client.util.ClientUtil;
import io.github.wj0410.chatroom.client.util.TrayUtil;
import io.github.wj0410.chatroom.common.model.ClientModel;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author wangjie
 */
public class ChatRoomUI {

    public ChatRoomUI() {
        this.initComponents();
        ClientHolder.chatRoomUI = this;
        // 在线列表样式
        this.onlineList.setCellRenderer(new OnlineListCellRenderer());
        // 设置消息回显区自动换行
        this.recvPane.setEditorKit(new WrapEditorKit());

    }

    public void show() {
        this.chatJFrame.setVisible(true);
    }

    public void hide() {
        this.chatJFrame.setVisible(false);
    }

    public void exit() {
        // 1.退出聊天室
        this.hide();
        ClientHolder.chatRoomUI = null;
        ClientHolder.privateChatUIMap = new HashMap<>(0);
        // 2.退出到登录页
        ClientHolder.loginUI.show();
        ClientHolder.nettyClient.shutDown();
        ClientHolder.nettyClient = null;
        // 3.关闭系统托盘
        TrayUtil.closeTray();
    }

    /**
     * 通过ClientModel列表，刷新在线列表
     * 新的客户端上线时，同步客户端的上线列表时使用
     *
     * @param clientModelLinkedList
     */
    public void flushOnlineModelList(LinkedList<ClientModel> clientModelLinkedList) {
        DefaultListModel<OnlineModel> model = new DefaultListModel<>();
        for (ClientModel clientModel : clientModelLinkedList) {
            OnlineModel onlineModel = new OnlineModel();
            onlineModel.setClientId(clientModel.getClientId());
            onlineModel.setAccount(clientModel.getAccount());
            onlineModel.setUserName(clientModel.getUserName());
            // 防止把未读消息刷新掉
            // 如果当前客户端已存在与JList中，则获取到未读数量
            OnlineModel oldClientModel = getOnlineModel(clientModel.getClientId());
            onlineModel.setUnreadCount(oldClientModel == null ? 0 : oldClientModel.getUnreadCount());
            model.addElement(onlineModel);
        }
        // 重新覆盖onlineList
        this.onlineList.setModel(model);
    }

    /**
     * 刷新未读列表
     *
     * @param fromClientId
     */
    public void flushUnread(String fromClientId) {
        OnlineModel onlineModel = getOnlineModel(fromClientId);
        if (onlineModel != null) {
            // 未读+1
            onlineModel.setUnreadCount(onlineModel.getUnreadCount() + 1);
            // 由于OnlineListCellRenderer当鼠标焦点不在窗体上时不会触发，因此这里重新覆盖onlineList，实现未读消息刷新
            DefaultListModel<OnlineModel> model = new DefaultListModel<>();
            for (OnlineModel element : getOnlineModelList()) {
                model.addElement(element);
            }
            // 重新覆盖onlineList
            this.onlineList.setModel(model);
        }
    }

    public LinkedList<OnlineModel> getOnlineModelList() {
        LinkedList<OnlineModel> onlineModelList = new LinkedList<>();
        ListModel<OnlineModel> model = this.onlineList.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            onlineModelList.add(model.getElementAt(i));
        }
        return onlineModelList;
    }

    public OnlineModel getOnlineModel(String clientId) {
        ListModel<OnlineModel> model = this.onlineList.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            OnlineModel element = model.getElementAt(i);
            if (element.getClientId().equals(clientId)) {
                return element;
            }
        }
        return null;
    }

    private void onlineListMouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            // 处理双击事件
            int index = onlineList.locationToIndex(e.getPoint());
            if (index != -1) {
                OnlineModel onlineModel = (OnlineModel) onlineList.getModel().getElementAt(index);
                String targetClientId = onlineModel.getClientId();
                if (!targetClientId.equals(ClientHolder.clientInfo.getClientId())) {
                    // 打开私聊对话框
                    PrivateChatUI privateChatUI = ClientHolder.privateChatUIMap.get(targetClientId);
                    if (privateChatUI == null) {
                        privateChatUI = new PrivateChatUI(onlineModel);
                    }
                    privateChatUI.show();
                    // 清空未读
                    onlineModel.setUnreadCount(0);
                }
            }
        }
    }

    private void onlineListValueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int index = onlineList.getSelectedIndex();
            if (index != -1) {
                Object elementAt = onlineList.getModel().getElementAt(index);
                OnlineModel onlineModel = (OnlineModel) elementAt;
                //判断自己还是其他人
                if (onlineModel.getClientId().equals(ClientHolder.clientInfo.getClientId())) {
                    // 如果选中的是带有标签（即自己），则清除选择
                    onlineList.clearSelection();
                }
            }
        }
    }

    public JTextPane getRecvPane() {
        return recvPane;
    }

    public JTextPane getSendPane() {
        return sendPane;
    }

    /**
     * 关闭聊天室
     * 程序退出
     *
     * @param e
     */
    private void chatRoomJFrameWindowClosing(WindowEvent e) {
        exit();
    }

    private void sendPaneKeyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
            // ctrl+回车切换下一行
            e.consume(); // 停止事件的默认行为
            sendPane.setText(sendPane.getText() + "\n");
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // 回车
            // 发送并清空发送域
            e.consume(); // 停止事件的默认行为
            String sendContent = sendPane.getText();
            if (StringUtils.isNotBlank(sendContent)) {
                // 发送消息
                ClientUtil.sendNormalMessage(ClientHolder.clientInfo.getCtx(), sendContent, null);
                // 清空发送框
                sendPane.setText("");
            }
        }
    }

    private void chatJFrameWindowGainedFocus(WindowEvent e) {
        // 系统托盘图标清空未读
        TrayUtil.noticeTray(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        chatJFrame = new JFrame();
        scrollPane2 = new JScrollPane();
        sendPane = new JTextPane();
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
            chatJFrame.addWindowFocusListener(new WindowAdapter() {
                @Override
                public void windowGainedFocus(WindowEvent e) {
                    chatJFrameWindowGainedFocus(e);
                }
            });
            Container chatJFrameContentPane = chatJFrame.getContentPane();

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

            //======== scrollPane3 ========
            {

                //---- onlineList ----
                onlineList.setFont(new Font("sansserif", Font.PLAIN, 20));
                onlineList.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
                onlineList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        onlineListMouseClicked(e);
                    }
                });
                onlineList.addListSelectionListener(e -> onlineListValueChanged(e));
                scrollPane3.setViewportView(onlineList);
            }

            //======== scrollPane4 ========
            {
                scrollPane4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                //---- recvPane ----
                recvPane.setEditable(false);
                recvPane.setBackground(new Color(0xf3f3f3));
                recvPane.setMargin(new Insets(5, 5, 5, 5));
                recvPane.setMinimumSize(new Dimension(426, 248));
                recvPane.setPreferredSize(new Dimension(426, 248));
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
                            .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                            .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
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
    private JTextPane sendPane;
    private JScrollPane scrollPane3;
    private JList onlineList;
    private JScrollPane scrollPane4;
    private JTextPane recvPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
