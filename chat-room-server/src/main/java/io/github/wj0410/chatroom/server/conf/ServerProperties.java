package io.github.wj0410.chatroom.server.conf;

import lombok.Data;

/**
 * @author wangjie
 * @date 2023/11/2
 */
@Data
public class ServerProperties {
    private ServerConfig server;

    public class ServerConfig {
        private int port;

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }
}