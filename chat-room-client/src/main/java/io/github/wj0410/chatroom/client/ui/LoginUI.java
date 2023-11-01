/*
 * Created by JFormDesigner on Tue Oct 24 12:31:27 CST 2023
 */

package io.github.wj0410.chatroom.client.ui;

import io.github.wj0410.chatroom.client.netty.NettyClient;
import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.common.util.SwingUIUtil;
import org.apache.commons.lang.StringUtils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

/**
 * @author wangjie
 */
public class LoginUI {

    public LoginUI() {
        this.initComponents();
        ClientHolder.loginUI = this;
    }

    /**
     * 登录
     *
     * @param host
     * @param port
     * @param account
     */
    private void login(String host, int port, String account) {
        // TODO 校验用户名密码
        // 将客户端信息记录到ClientHolder
        ClientHolder.clientInfo = new ClientModel();
        ClientHolder.clientInfo.setAccount(account);
        ClientHolder.clientInfo.setUserName(account);
        if (connection(host, port)) {
            // 隐藏登录UI
            this.hide();
            // 打开聊天室
            ChatRoomUI chatRoomUI = new ChatRoomUI();
            chatRoomUI.show();
        }
    }

    /**
     * 注册
     */
    private void register() {
        SwingUIUtil.alertError("开发中");
    }

    /**
     * 连接服务器
     *
     * @param host
     * @param port
     * @return
     */
    private boolean connection(String host, int port) {
        try {
            NettyClient nettyClient = new NettyClient(host, port);
            nettyClient.start();
        } catch (Exception e1) {
            SwingUIUtil.alertError("连接服务器失败！");
            ClientHolder.nettyClient = null;
            return false;
        }
        return true;
    }

    public void show() {
        this.loginJFrame.setVisible(true);
    }

    public void hide() {
        this.loginJFrame.setVisible(false);
    }

    public void doLogin() {
        int port;
        String host;
        try {
            String address = this.address.getText();
            String[] addressSplit = address.split(":");
            port = Integer.parseInt(addressSplit[1]);
            host = addressSplit[0];
        } catch (Exception exception) {
            SwingUIUtil.alertError("服务器地址不正确！");
            return;
        }
        String account = this.account.getText();
        if (StringUtils.isBlank(account)) {
            SwingUIUtil.alertError("用户名不能为空！");
            return;
        }
        if (ClientHolder.nettyClient != null) {
            SwingUIUtil.alertError("请勿重复登录！");
            return;
        }
        login(host, port, account);
    }

    private void loginBtnClicked(MouseEvent e) {
        doLogin();
    }

    private void registerLabelClicked(MouseEvent e) {
        register();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        loginJFrame = new JFrame();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        address = new JTextField();
        account = new JTextField();
        loginBtn = new JButton();
        password = new JPasswordField();
        registerLabel = new JLabel();

        //======== loginJFrame ========
        {
            loginJFrame.setTitle("Chat");
            loginJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            loginJFrame.setResizable(false);
            Container loginJFrameContentPane = loginJFrame.getContentPane();

            //---- label1 ----
            label1.setText("\u670d\u52a1\u5668\u5730\u5740\uff1a");

            //---- label2 ----
            label2.setText("\u8d26\u53f7\uff1a");

            //---- label3 ----
            label3.setText("\u5bc6\u7801\uff1a");

            //---- address ----
            address.setText("127.0.0.1:5678");

            //---- loginBtn ----
            loginBtn.setText("\u767b\u5f55");
            loginBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    loginBtnClicked(e);
                }
            });

            //---- password ----
            password.setEnabled(false);

            //---- registerLabel ----
            registerLabel.setText("\u6ce8\u518c\u8d26\u53f7");
            registerLabel.setForeground(Color.blue);
            registerLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    registerLabelClicked(e);
                }
            });

            GroupLayout loginJFrameContentPaneLayout = new GroupLayout(loginJFrameContentPane);
            loginJFrameContentPane.setLayout(loginJFrameContentPaneLayout);
            loginJFrameContentPaneLayout.setHorizontalGroup(
                loginJFrameContentPaneLayout.createParallelGroup()
                    .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(loginJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                                .addGroup(loginJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(label1)
                                    .addComponent(label2))
                                .addGap(18, 18, 18)
                                .addGroup(loginJFrameContentPaneLayout.createParallelGroup()
                                    .addComponent(address, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(account, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                                .addComponent(label3)
                                .addGroup(loginJFrameContentPaneLayout.createParallelGroup()
                                    .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(password, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(registerLabel)
                        .addContainerGap(57, Short.MAX_VALUE))
            );
            loginJFrameContentPaneLayout.setVerticalGroup(
                loginJFrameContentPaneLayout.createParallelGroup()
                    .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(loginJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(loginJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(account, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2)
                            .addComponent(registerLabel))
                        .addGap(9, 9, 9)
                        .addGroup(loginJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(52, Short.MAX_VALUE))
            );
            loginJFrame.pack();
            loginJFrame.setLocationRelativeTo(loginJFrame.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JFrame loginJFrame;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField address;
    public JTextField account;
    private JButton loginBtn;
    private JPasswordField password;
    private JLabel registerLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
