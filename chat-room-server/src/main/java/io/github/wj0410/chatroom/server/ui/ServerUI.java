/*
 * Created by JFormDesigner on Mon Oct 23 16:26:56 CST 2023
 */

package io.github.wj0410.chatroom.server.ui;

import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.common.util.UIUtil;
import io.github.wj0410.chatroom.server.Server;
import io.github.wj0410.chatroom.server.util.ServerUtil;
import org.apache.commons.lang.StringUtils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

/**
 * @author wangjie
 */
public class ServerUI {

    private Server server;

    public ServerUI() {
        this.initComponents();
    }

    public static void main(String[] args) {
        ServerUI serverUI = new ServerUI();
        serverUI.show();
    }

    public void flushClientOnlineList() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (ClientModel clientModel : ServerUtil.getClientOnlineList()) {
            model.addElement(UIUtil.formatClientName(clientModel));
        }
        this.onlineList.setModel(model);
        this.onlineCount.setText(String.valueOf(ServerUtil.getClientOnlineList().size()));
    }

    public void show() {
        this.serverJFrame.setVisible(true);
    }

    private void runBtnClicked(MouseEvent e) {
        String port = this.portText.getText();
        if (StringUtils.isBlank(port)) {
            UIUtil.alertError("端口号不能为空！");
            return;
        }
        try {
            if (this.server != null) {
                UIUtil.alertError("服务端已启动！");
                return;
            }
            this.server = new Server(Integer.parseInt(port));
            server.start(this);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void shutdownBtnClicked(MouseEvent e) {
        server.shutDown();
    }


    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public JTextPane getConsolePane() {
        return this.consolePane;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        serverJFrame = new JFrame();
        scrollPane1 = new JScrollPane();
        consolePane = new JTextPane();
        portText = new JTextField();
        label1 = new JLabel();
        runBtn = new JButton();
        shutdownBtn = new JButton();
        label2 = new JLabel();
        onlineCount = new JLabel();
        scrollPane3 = new JScrollPane();
        onlineList = new JList();

        //======== serverJFrame ========
        {
            serverJFrame.setTitle("Server");
            serverJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            serverJFrame.setAutoRequestFocus(false);
            Container serverJFrameContentPane = serverJFrame.getContentPane();

            //======== scrollPane1 ========
            {

                //---- consolePane ----
                consolePane.setEditable(false);
                scrollPane1.setViewportView(consolePane);
            }

            //---- portText ----
            portText.setText("5678");

            //---- label1 ----
            label1.setText("\u7aef\u53e3\u53f7");

            //---- runBtn ----
            runBtn.setText("\u542f\u52a8");
            runBtn.setBackground(new Color(0xd6d9df));
            runBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    runBtnClicked(e);
                }
            });

            //---- shutdownBtn ----
            shutdownBtn.setText("\u505c\u6b62");
            shutdownBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    shutdownBtnClicked(e);
                }
            });

            //---- label2 ----
            label2.setText("\u5728\u7ebf\u4eba\u6570\uff1a");

            //---- onlineCount ----
            onlineCount.setText("0");

            //======== scrollPane3 ========
            {
                scrollPane3.setViewportView(onlineList);
            }

            GroupLayout serverJFrameContentPaneLayout = new GroupLayout(serverJFrameContentPane);
            serverJFrameContentPane.setLayout(serverJFrameContentPaneLayout);
            serverJFrameContentPaneLayout.setHorizontalGroup(
                    serverJFrameContentPaneLayout.createParallelGroup()
                            .addGroup(serverJFrameContentPaneLayout.createSequentialGroup()
                                    .addGroup(serverJFrameContentPaneLayout.createParallelGroup()
                                            .addGroup(serverJFrameContentPaneLayout.createSequentialGroup()
                                                    .addGap(24, 24, 24)
                                                    .addComponent(label1)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(portText, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(runBtn)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(shutdownBtn)
                                                    .addGap(82, 82, 82)
                                                    .addComponent(label2)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(onlineCount)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                                                    .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(serverJFrameContentPaneLayout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)))
                                    .addContainerGap())
            );
            serverJFrameContentPaneLayout.setVerticalGroup(
                    serverJFrameContentPaneLayout.createParallelGroup()
                            .addGroup(serverJFrameContentPaneLayout.createSequentialGroup()
                                    .addGroup(serverJFrameContentPaneLayout.createParallelGroup()
                                            .addGroup(serverJFrameContentPaneLayout.createSequentialGroup()
                                                    .addGap(26, 26, 26)
                                                    .addGroup(serverJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(label1)
                                                            .addComponent(portText)
                                                            .addComponent(runBtn)
                                                            .addComponent(shutdownBtn)
                                                            .addComponent(label2)
                                                            .addComponent(onlineCount))
                                                    .addGap(42, 42, 42))
                                            .addGroup(serverJFrameContentPaneLayout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                                    .addContainerGap())
            );
            serverJFrame.pack();
            serverJFrame.setLocationRelativeTo(serverJFrame.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JFrame serverJFrame;
    private JScrollPane scrollPane1;
    private JTextPane consolePane;
    private JTextField portText;
    private JLabel label1;
    private JButton runBtn;
    private JButton shutdownBtn;
    private JLabel label2;
    private JLabel onlineCount;
    private JScrollPane scrollPane3;
    private JList onlineList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
