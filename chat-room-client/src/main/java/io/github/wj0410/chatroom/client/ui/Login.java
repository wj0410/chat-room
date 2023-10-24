/*
 * Created by JFormDesigner on Tue Oct 24 11:56:00 CST 2023
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
public class Login  {

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        loginJFrame = new JFrame();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        address = new JTextField();
        account = new JTextField();
        button1 = new JButton();
        password = new JPasswordField();

        //======== loginJFrame ========
        {
            loginJFrame.setTitle("\u767b\u5f55");
            loginJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Container loginJFrameContentPane = loginJFrame.getContentPane();

            //---- label1 ----
            label1.setText("\u670d\u52a1\u5668\u5730\u5740\uff1a");

            //---- label2 ----
            label2.setText("\u8d26\u53f7\uff1a");

            //---- label3 ----
            label3.setText("\u5bc6\u7801\uff1a");

            //---- address ----
            address.setText("47.98.35.95:5678");

            //---- button1 ----
            button1.setText("\u8fde\u63a5");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });

            //---- password ----
            password.setEnabled(false);

            GroupLayout loginJFrameContentPaneLayout = new GroupLayout(loginJFrameContentPane);
            loginJFrameContentPane.setLayout(loginJFrameContentPaneLayout);
            loginJFrameContentPaneLayout.setHorizontalGroup(
                loginJFrameContentPaneLayout.createParallelGroup()
                    .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(loginJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                                .addGroup(loginJFrameContentPaneLayout.createParallelGroup()
                                    .addComponent(label1)
                                    .addComponent(label2, GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(loginJFrameContentPaneLayout.createParallelGroup()
                                    .addComponent(address, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(account, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                                .addComponent(label3)
                                .addGroup(loginJFrameContentPaneLayout.createParallelGroup()
                                    .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(password, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(76, Short.MAX_VALUE))
            );
            loginJFrameContentPaneLayout.setVerticalGroup(
                loginJFrameContentPaneLayout.createParallelGroup()
                    .addGroup(loginJFrameContentPaneLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(loginJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(loginJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2)
                            .addComponent(account, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(loginJFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3)
                            .addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(36, Short.MAX_VALUE))
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
    private JButton button1;
    private JPasswordField password;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
