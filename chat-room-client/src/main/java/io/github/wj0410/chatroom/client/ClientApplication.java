package io.github.wj0410.chatroom.client;

import io.github.wj0410.chatroom.client.conf.ClientProperties;
import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.ui.swing.LoginUI;
import io.github.wj0410.chatroom.common.util.ConfigUtil;

/**
 * @author wangjie
 * @date 2023/11/1
 */
public class ClientApplication {

    public static void main(String[] args) {
        // 加载配置文件
        ClientProperties clientProperties = ConfigUtil.loadYaml("application.yml", ClientProperties.class);

        ClientHolder.clientProperties = clientProperties;
        // 启动UI
        LoginUI loginUI = new LoginUI();
        loginUI.getAddress().setText(clientProperties.getServer().getAddress());
    }

}
