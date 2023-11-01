package io.github.wj0410.chatroom.server;

import io.github.wj0410.chatroom.server.ui.AbstractServerUI;
import io.github.wj0410.chatroom.server.ui.console.Console;
import io.github.wj0410.chatroom.server.ui.swing.SwingUI;

/**
 * @author wangjie
 * @date 2023/11/1
 */
public class ServerApplication {

    public static void main(String[] args) {
        AbstractServerUI serverUI = new SwingUI();
//        AbstractServerUI serverUI = new Console();
        serverUI.show();
        serverUI.run();
    }
}
