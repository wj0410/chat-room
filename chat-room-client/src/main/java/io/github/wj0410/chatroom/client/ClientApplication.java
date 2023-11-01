package io.github.wj0410.chatroom.client;

import io.github.wj0410.chatroom.client.ui.swing.LoginUI;

/**
 * @author wangjie
 * @date 2023/11/1
 */
public class ClientApplication {

    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
//        loginUI.getAddress().setText("127.0.0.1:5678");
        loginUI.getAddress().setText("47.98.35.95:5678");
    }
}
