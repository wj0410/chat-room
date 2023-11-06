package io.github.wj0410.chatroom.common.message;

import io.github.wj0410.chatroom.common.enums.ChatType;
import io.github.wj0410.chatroom.common.enums.MessageContainerType;
import io.github.wj0410.chatroom.common.model.MessageContainer;
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
    private ChatType chatType;
    private String fromAccount;
    private String fromUserName;
    private String fromClientId;
    private List<String> targetClientIds;
    private long timestamp;
    private List<MessageContainer> msg;

    public NormalMessage() {
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        String msg = "";
        for (MessageContainer messageContainer : this.msg) {
            if (messageContainer.getType().equals(MessageContainerType.IMAGE)) {
                msg += "[img],";
            } else {
                msg += messageContainer.getText() + ",";
            }
        }
        return "NormalMessage{" +
                "chatType=" + chatType +
                ", fromAccount='" + fromAccount + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", fromClientId='" + fromClientId + '\'' +
                ", targetClientIds=" + targetClientIds +
                ", timestamp=" + timestamp +
                ", msg=" + String.format("[%s]", msg.replaceAll("^,|,$", "")) +
                '}';
    }
}
