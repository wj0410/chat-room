/*
 * Created by JFormDesigner on Mon Oct 23 16:26:56 CST 2023
 */

package io.github.wj0410.chatroom.server.ui;

import io.github.wj0410.chatroom.common.util.UIUtil;
import io.github.wj0410.chatroom.server.Server;
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
    private static Server server;

    public static void main(String[] args) {
        ServerUI serverUI = new ServerUI();
        serverUI.run();
    }

    /**
     * 运行
     */
    public void run() {
        this.initComponents();
        this.mainJFrame.setVisible(true);
    }

    /**
     * 启动
     *
     * @param e
     * @throws Exception
     */
    private void runBtnClicked(MouseEvent e) {
        String port = this.portText.getText();
        if (StringUtils.isBlank(port)) {
            UIUtil.alertError("端口号不能为空！");
            return;
        }
        try {
            if (ServerUI.server != null) {
                UIUtil.alertError("服务端已启动！");
                return;
            }
            Server server = new Server(Integer.parseInt(port));
            ServerUI.server = server;
            server.start(this);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 停止
     *
     * @param e
     */
    private void shutdownBtnClicked(MouseEvent e) {
        server.shutDown();
    }

    public JTextPane getConsolePane() {
        return this.consolePane;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        mainJFrame = new JFrame();
        scrollPane1 = new JScrollPane();
        consolePane = new JTextPane();
        portText = new JTextField();
        label1 = new JLabel();
        runBtn = new JButton();
        shutdownBtn = new JButton();
        label2 = new JLabel();
        online_count = new JLabel();

        //======== mainJFrame ========
        {
            mainJFrame.setTitle("Server");
            mainJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainJFrame.setAutoRequestFocus(false);
            Container mainJFrameContentPane = mainJFrame.getContentPane();

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

            //---- online_count ----
            online_count.setText("0");

            GroupLayout mainJFrameContentPaneLayout = new GroupLayout(mainJFrameContentPane);
            mainJFrameContentPane.setLayout(mainJFrameContentPaneLayout);
            mainJFrameContentPaneLayout.setHorizontalGroup(
                mainJFrameContentPaneLayout.createParallelGroup()
                    .addGroup(mainJFrameContentPaneLayout.createSequentialGroup()
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
                        .addComponent(online_count)
                        .addContainerGap(318, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, mainJFrameContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                        .addContainerGap())
            );
            mainJFrameContentPaneLayout.setVerticalGroup(
                mainJFrameContentPaneLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, mainJFrameContentPaneLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(mainJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(portText)
                            .addComponent(runBtn)
                            .addComponent(shutdownBtn)
                            .addComponent(label2)
                            .addComponent(online_count))
                        .addGap(42, 42, 42)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                        .addContainerGap())
            );
            mainJFrame.pack();
            mainJFrame.setLocationRelativeTo(mainJFrame.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JFrame mainJFrame;
    private JScrollPane scrollPane1;
    private JTextPane consolePane;
    private JTextField portText;
    private JLabel label1;
    private JButton runBtn;
    private JButton shutdownBtn;
    private JLabel label2;
    private JLabel online_count;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
