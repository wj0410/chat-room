package io.github.wj0410.chatroom.common.message;

import lombok.Data;

/**
 * @author wangjie
 * @date 2023/10/26
 */
@Data
public class RefuseMessage {
    private String clientId;
    private String msg;
    private long timestamp;

    public RefuseMessage(){
        this.timestamp = System.currentTimeMillis();
    }
    @Override
    public String toString() {
        return "RefuseMessage{" +
                "clientId='" + clientId + '\'' +
                ", msg='" + msg + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
