package io.github.wj0410.chatroom.common.message;

import lombok.Data;

/**
 * @author wangjie
 * @date 2023/10/26
 */
@Data
public class WelcomeMessage {
    private String clientId;
    private String msg;
    private long timestamp;

    @Override
    public String toString() {
        return "WelcomeMessage{" +
                "clientId='" + clientId + '\'' +
                ", msg='" + msg + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
