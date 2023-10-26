package io.github.wj0410.chatroom.common.message;

import io.github.wj0410.chatroom.common.enums.MessageType;
import lombok.Data;

/**
 * @author wangjie
 * @date 2023/10/26
 */
@Data
public class Message<T> {
    private MessageType type;
    private T data;

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", data=" + data +
                '}';
    }
}
