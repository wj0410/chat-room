package io.github.wj0410.chatroom.common.message;

import io.github.wj0410.chatroom.common.enums.old.PromptType;
import lombok.Data;

/**
 * @author wangjie
 * @date 2023/10/26
 */
@Data
public class PromptMessage {
    private PromptType promptType;
    private String clientId;
    private String msg;
    private long timestamp;


    public PromptMessage(){
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "PromptMessage{" +
                "promptType=" + promptType +
                ", clientId='" + clientId + '\'' +
                ", msg='" + msg + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
