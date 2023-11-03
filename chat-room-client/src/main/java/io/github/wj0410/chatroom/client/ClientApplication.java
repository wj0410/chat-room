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
        startClient();
    }

    public static void startClient() {
        // 加载配置文件
        ClientHolder.clientProperties = ConfigUtil.loadYaml("client.yml", ClientProperties.class);
        new LoginUI();
    }
}
