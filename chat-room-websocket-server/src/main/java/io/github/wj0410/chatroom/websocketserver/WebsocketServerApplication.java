package io.github.wj0410.chatroom.websocketserver;

import io.github.wj0410.chatroom.common.util.ConfigUtil;
import io.github.wj0410.chatroom.websocketserver.conf.ServerProperties;
import io.github.wj0410.chatroom.websocketserver.holder.ServerHolder;
import io.github.wj0410.chatroom.websocketserver.netty.WebSocketServer;

/**
 * @author wangjie
 * @date 2023/11/1
 */
public class WebsocketServerApplication {

    public static void main(String[] args) {
        startServer();
    }

    public static void startServer() {
        // 加载配置文件
        ServerHolder.serverProperties = ConfigUtil.loadYaml("server.yml", ServerProperties.class);
        // 运行
        WebSocketServer instance = WebSocketServer.getInstance(ServerHolder.serverProperties.getServer().getPort());
        instance.start();
    }
}
