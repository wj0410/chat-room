/*
 * Created by JFormDesigner on Mon Oct 23 16:26:56 CST 2023
 */

package io.github.wj0410.chatroom.server.ui.swing;

import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.common.util.ServerUtil;
import io.github.wj0410.chatroom.common.util.SwingUIUtil;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.github.wj0410.chatroom.server.ui.AbstractServerUI;
import io.github.wj0410.cloudbox.tools.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Java swing 启动
 *
 * @author wangjie
 */
@Slf4j
public class ServerSwingUI extends AbstractServerUI {

    public ServerSwingUI() {
        this.initComponents();
        this.show();
    }

    @Override
    public void flushClientOnlineList() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (ClientModel clientModel : ServerUtil.getClientOnlineList()) {
            model.addElement(ServerUtil.formatClientAccount(clientModel));
        }
        this.onlineList.setModel(model);
        this.onlineCount.setText(String.valueOf(model.size()));
    }

    @Override
    public void show() {
        this.serverJFrame.setVisible(true);
    }

    @Override
    public int getServerPort() {
        return Integer.parseInt(this.portText.getText());
    }


    @Override
    public void stopCheck() {
        if (!shutdownBtn.isEnabled()) {
            return;
        }
    }

    @Override
    public void afterStop() {
        runBtn.setEnabled(true);
        shutdownBtn.setEnabled(false);
    }

    @Override
    public void printConsole(String data) {
        StyledDocument styledDoc = this.consolePane.getStyledDocument();
        SwingUIUtil.buildServerConsoleStyle(styledDoc);
        this.consolePane.setDocument(styledDoc);
        try {
            styledDoc.insertString(styledDoc.getLength(), data + "\n", styledDoc.getStyle(SwingUIUtil.SERVER_CONSOLE_STYLE_NAME));
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void runCheck() {
        if (!runBtn.isEnabled()) {
            return;
        }
        String port = this.portText.getText();
        if (StringUtils.isBlank(port)) {
            SwingUIUtil.alertError("端口号不能为空！");
            return;
        }
        if (ServerHolder.nettyServer != null) {
            SwingUIUtil.alertError("服务端已启动！");
            return;
        }
    }

    @Override
    public void runSuccess() {
        this.printConsole("Server：启动Netty服务端成功，端口号:" + getServerPort());
        this.runBtn.setEnabled(false);
        this.shutdownBtn.setEnabled(true);
    }

    @Override
    public void runFailed() {
        this.printConsole("Netty服务启动失败！");
    }

    private void runBtnClicked(MouseEvent e) {
        run();
    }

    private void shutdownBtnClicked(MouseEvent e) {
        shutdown();
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
            shutdownBtn.setEnabled(false);
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
