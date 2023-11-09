package io.github.wj0410.chatroom.server;

import io.github.wj0410.chatroom.common.conf.ServerProperties;
import io.github.wj0410.chatroom.common.util.ConfigUtil;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.github.wj0410.chatroom.server.ui.AbstractServerUI;
import io.github.wj0410.chatroom.server.ui.console.Console;

/**
 * @author wangjie
 * @date 2023/11/1
 */
public class ServerApplication {

    public static void main(String[] args) {
        // 可以选择服务启动方式，界面启动 / 控制台启动
        startServer(new Console());
    }

    public static void startServer(AbstractServerUI ui) {
        // 加载配置文件
        ServerHolder.serverProperties = ConfigUtil.loadYaml("server.yml", ServerProperties.class);
        // 运行
        ui.run();
    }
}
