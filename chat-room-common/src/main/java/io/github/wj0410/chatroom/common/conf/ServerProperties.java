package io.github.wj0410.chatroom.common.conf;

import lombok.Data;

/**
 * @author wangjie
 * @date 2023/11/2
 */
@Data
public class ServerProperties {
    private ClientConfig client;

    private ServerConfig server;

    public class ClientConfig {
        private String version;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

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