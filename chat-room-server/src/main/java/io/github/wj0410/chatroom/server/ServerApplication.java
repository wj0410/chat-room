package io.github.wj0410.chatroom.server;

import io.github.wj0410.chatroom.common.util.ConfigUtil;
import io.github.wj0410.chatroom.server.conf.ServerProperties;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.github.wj0410.chatroom.server.ui.AbstractServerUI;
import io.github.wj0410.chatroom.server.ui.console.Console;
import io.github.wj0410.chatroom.server.ui.swing.SwingUI;

/**
 * @author wangjie
 * @date 2023/11/1
 */
public class ServerApplication {

    public static void main(String[] args) {
        // 加载配置文件
        ServerProperties serverProperties = ConfigUtil.loadYaml("application.yml", ServerProperties.class);
        ServerHolder.serverProperties = serverProperties;
        // 可以选择服务启动方式，界面启动 / 控制台启动
//        AbstractServerUI serverUI = new SwingUI();
        AbstractServerUI serverUI = new Console();
        serverUI.run();
    }
}
