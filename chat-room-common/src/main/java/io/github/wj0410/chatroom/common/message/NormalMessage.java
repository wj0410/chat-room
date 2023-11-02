package io.github.wj0410.chatroom.common.message;

import lombok.Data;

import java.util.List;

/**
 * 普通消息
 *
 * @author wangjie
 * @date 2023/10/25
 */
@Data
public class NormalMessage {
    private String fromAccount;
    private String fromUserName;
    private String fromClientId;
    private List<String> targetClientIds;
    private long timestamp;
    private String msg;

    public NormalMessage(){
        this.timestamp = System.currentTimeMillis();
    }
    @Override
    public String toString() {
        return "NormalMessage{" +
                "fromAccount='" + fromAccount + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", fromClientId='" + fromClientId + '\'' +
                ", targetClientIds=" + targetClientIds +
                ", timestamp=" + timestamp +
                ", msg='" + msg + '\'' +
                '}';
    }
}
