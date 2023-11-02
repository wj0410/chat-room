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
        // 指定链接的服务端的ip端口
//        loginUI.getAddress().setText("127.0.0.1:5678");
        loginUI.getAddress().setText("47.98.35.95:5678");
    }

}
