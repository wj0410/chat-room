package io.github.wj0410.chatroom.client;

import io.github.wj0410.chatroom.client.ui.LoginUI;

/**
 * @author wangjie
 * @date 2023/11/1
 */
public class ClientApplication {

    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        loginUI.show();
    }
}
