/*
 * Created by JFormDesigner on Tue Oct 24 12:31:27 CST 2023
 */

package io.github.wj0410.chatroom.client.ui.swing;

import io.github.wj0410.chatroom.client.conf.AccountConf;
import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.netty.NettyClient;
import io.github.wj0410.chatroom.common.enums.ClientOrigin;
import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.common.util.SwingUIUtil;
import io.github.wj0410.cloudbox.tools.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * @author wangjie
 */
public class LoginUI {
    private int port;
    private String host;
    public static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public LoginUI() {
        this.initComponents();
        ClientHolder.loginUI = this;
        this.show();
        this.address.setText(ClientHolder.clientProperties.getServer().getAddress());
    }

    /**
     * TODO 校验用户名密码
     *
     * @param account
     * @return
     */
    private ClientModel checkUserValid(String account) {
        ClientModel temporaryAccount = AccountConf.accountMap.get(account);
        if (temporaryAccount == null) {
            SwingUIUtil.alertError("账号不存在！");
            throw new RuntimeException("账号不存在！");
        }
        return temporaryAccount;
    }

    /**
     * 登录
     *
     * @param host
     * @param port
     * @param sysUser
     */
    private void loginSuccess(String host, int port, ClientModel sysUser) {
        // 将客户端信息记录到ClientHolder
        ClientHolder.clientInfo = new ClientModel();
        ClientHolder.clientInfo.setClientOrigin(ClientOrigin.SWING);
        ClientHolder.clientInfo.setAccount(sysUser.getAccount());
        ClientHolder.clientInfo.setUserName(sysUser.getUserName());
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
        SwingUIUtil.alertError("暂未开放");
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

    public JTextField getAddress() {
        return address;
    }

    public void show() {
        this.loginJFrame.setVisible(true);
    }

    public void hide() {
        this.loginJFrame.setVisible(false);
    }

    /**
     * 游客登录
     */
    public void visitorLogin() {
        checkAddress();
        ClientModel clientModel = generateVisitorClientModel();
        loginSuccess(host, port, clientModel);
    }

    /**
     * 用户登录
     */
    public void doLogin() {
        checkAddress();
        String account = this.account.getText();
        if (StringUtils.isBlank(account)) {
            SwingUIUtil.alertError("用户名不能为空！");
            throw new RuntimeException("用户名不能为空");
        }
        if (ClientHolder.nettyClient != null) {
            SwingUIUtil.alertError("请勿重复登录！");
            throw new RuntimeException("请勿重复登录");
        }
        ClientModel clientModel = checkUserValid(account);
        loginSuccess(host, port, clientModel);
    }

    private void checkAddress() {
        try {
            String address = this.address.getText();
            String[] addressSplit = address.split(":");
            this.port = Integer.parseInt(addressSplit[1]);
            this.host = addressSplit[0];
        } catch (Exception exception) {
            SwingUIUtil.alertError("服务器地址不正确！");
            throw new RuntimeException("服务器地址不正确");
        }
    }

    private static ClientModel generateVisitorClientModel() {
        int length = 5;
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(rnd.nextInt(characters.length())));
        }
        String account = sb.toString();
        ClientModel clientModel = new ClientModel();
        clientModel.setClientOrigin(ClientOrigin.SWING);
        clientModel.setAccount(account);
        clientModel.setUserName("游客-" + account);
        return clientModel;
    }


    private void visitorLoginBtnClicked(MouseEvent e) {
        visitorLogin();
    }

    private void registerLabelClicked(MouseEvent e) {
        register();
    }

    private void accountKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // 回车
            doLogin();
        }
    }

    private void loginBtnClicked(MouseEvent e) {
        doLogin();
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
        visitorLoginBtn = new JButton();

        //======== loginJFrame ========
        {
            loginJFrame.setTitle("Chat");
            loginJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            loginJFrame.setResizable(false);
            Container loginJFrameContentPane = loginJFrame.getContentPane();

            //---- label1 ----
            label1.setText("\u670d\u52a1\u5668\u5730\u5740\uff1a");
            label1.setVisible(false);

            //---- label2 ----
            label2.setText("\u8d26\u53f7\uff1a");

            //---- label3 ----
            label3.setText("\u5bc6\u7801\uff1a");

            //---- address ----
            address.setText("127.0.0.1:5678");
            address.setVisible(false);

            //---- account ----
            account.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    accountKeyPressed(e);
                }
            });

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

            //---- visitorLoginBtn ----
            visitorLoginBtn.setText("\u6e38\u5ba2\u767b\u5f55");
            visitorLoginBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    visitorLoginBtnClicked(e);
                }
            });

            GroupLayout loginJFrameContentPaneLayout = new GroupLayout(loginJFrameContentPane);
            loginJFrameContentPane.setLayout(loginJFrameContentPaneLayout);
            loginJFrameContentPaneLayout.setHorizontalGroup(
                    loginJFrameContentPaneLayout.createParallelGroup()
                            .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                                    .addGap(151, 151, 151)
                                    .addComponent(label1)
                                    .addGap(40, 40, 40)
                                    .addComponent(address, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(227, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, loginJFrameContentPaneLayout.createSequentialGroup()
                                    .addContainerGap(69, Short.MAX_VALUE)
                                    .addGroup(loginJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                                                    .addComponent(label2)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(account, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                                                    .addComponent(label3)
                                                    .addGap(18, 18, 18)
                                                    .addGroup(loginJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                            .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                                                                    .addComponent(visitorLoginBtn, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(password, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(registerLabel)
                                    .addGap(50, 50, 50))
            );
            loginJFrameContentPaneLayout.setVerticalGroup(
                    loginJFrameContentPaneLayout.createParallelGroup()
                            .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addGroup(loginJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label1)
                                            .addComponent(address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                    .addGroup(loginJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label2)
                                            .addComponent(registerLabel)
                                            .addComponent(account, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(loginJFrameContentPaneLayout.createParallelGroup()
                                            .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                                                    .addGap(24, 24, 24)
                                                    .addComponent(label3))
                                            .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(password, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(loginJFrameContentPaneLayout.createParallelGroup()
                                            .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(visitorLoginBtn, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
                                    .addGap(39, 39, 39))
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
    private JButton visitorLoginBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
