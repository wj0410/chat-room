/*
 * Created by JFormDesigner on Tue Oct 31 10:48:03 CST 2023
 */

package io.github.wj0410.chatroom.client.ui.swing;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.ui.swing.model.OnlineModel;
import io.github.wj0410.chatroom.client.ui.swing.style.WrapEditorKit;
import io.github.wj0410.chatroom.client.util.ClientUtil;
import io.github.wj0410.chatroom.common.enums.ChatType;
import io.github.wj0410.chatroom.common.message.NormalMessage;
import io.github.wj0410.chatroom.common.util.SwingUIUtil;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

/**
 * @author wangjie
 */
public class PrivateChatUI {
    private OnlineModel targetClient;

    public PrivateChatUI(OnlineModel targetClient) {
        this.targetClient = targetClient;
        this.initComponents();
        // 设置消息回显区自动换行
        this.recvPane.setEditorKit(new WrapEditorKit());
        this.privateChatJFrame.setTitle(targetClient.getUserName());
        ClientHolder.privateChatUIMap.put(targetClient.getClientId(), this);
    }

    public void show() {
        this.privateChatJFrame.setVisible(true);
    }

    public boolean isShow() {
        return this.privateChatJFrame.isVisible();
    }

    public JTextPane getRecvPane() {
        return recvPane;
    }

    private void resetSendPane() {
        // 清空发送框
        sendPane.setText("");
        // 将光标位置设置为0
        sendPane.setCaretPosition(0);
        // 清除掉发送框滚动条
        JScrollBar verticalScrollBar = scrollPane2.getVerticalScrollBar();
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // 将滚动条位置设置为0
        verticalScrollBar.setValue(0);
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
                // 发送私聊消息
                NormalMessage normalMessage = ClientUtil.sendNormalMessage(ClientHolder.clientInfo.getCtx(), ClientUtil.processTextPane(sendPane), ChatType.PRIVATE, Arrays.asList(this.targetClient.getClientId()));
                // 渲染接收区域
                SwingUIUtil.drawRecvPane(normalMessage, this.recvPane, 1);
                // 重置发送框
                resetSendPane();
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        privateChatJFrame = new JFrame();
        scrollPane4 = new JScrollPane();
        recvPane = new JTextPane();
        scrollPane2 = new JScrollPane();
        sendPane = new JTextPane();

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

            GroupLayout privateChatJFrameContentPaneLayout = new GroupLayout(privateChatJFrameContentPane);
            privateChatJFrameContentPane.setLayout(privateChatJFrameContentPaneLayout);
            privateChatJFrameContentPaneLayout.setHorizontalGroup(
                    privateChatJFrameContentPaneLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, privateChatJFrameContentPaneLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(privateChatJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(scrollPane4)
                                            .addComponent(scrollPane2))
                                    .addContainerGap())
            );
            privateChatJFrameContentPaneLayout.setVerticalGroup(
                    privateChatJFrameContentPaneLayout.createParallelGroup()
                            .addGroup(privateChatJFrameContentPaneLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
