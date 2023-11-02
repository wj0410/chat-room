package io.github.wj0410.chatroom.client.conf;

import lombok.Data;

/**
 * @author wangjie
 * @date 2023/11/2
 */
@Data
public class ClientProperties {

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
        private String address;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}