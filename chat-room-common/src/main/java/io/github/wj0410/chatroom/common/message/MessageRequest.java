package io.github.wj0410.chatroom.common.message;

import lombok.Data;

import java.util.List;

/**
 * 普通消息
 * @author wangjie
 * @date 2023/10/25
 */
@Data
public class MessageRequest {
    private String fromUserName;
    private String fromClientId;
    private List<String> targetClientId;
    private long timestamp;
    private String msg;

    @Override
    public String toString() {
        return "MessageRequest{" +
                "fromUserName='" + fromUserName + '\'' +
                ", fromClientId='" + fromClientId + '\'' +
                ", targetClientId=" + targetClientId +
                ", timestamp=" + timestamp +
                ", msg='" + msg + '\'' +
                '}';
    }
}
