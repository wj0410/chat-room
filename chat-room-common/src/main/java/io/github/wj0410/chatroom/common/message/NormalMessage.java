package io.github.wj0410.chatroom.common.message;

import io.github.wj0410.chatroom.common.enums.ChatType;
import io.github.wj0410.chatroom.common.enums.old.MessageContainerType;
import io.github.wj0410.chatroom.common.model.MessageContainer;
import lombok.Data;
import org.springframework.util.CollectionUtils;

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
    private String fromNickName;
    private String fromClientId;
    private String fromClientAvatar;
    private List<String> targetClientIds;
    private long timestamp;
    private List<MessageContainer> msgList;

    public NormalMessage() {
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        String msg = "";
        if(!CollectionUtils.isEmpty(this.msgList)){
            for (MessageContainer messageContainer : this.msgList) {
                if (messageContainer.getType().equals(MessageContainerType.IMAGE)) {
                    msg += "[img],";
                } else {
                    msg += messageContainer.getText() + ",";
                }
            }
        }
        return "NormalMessage{" +
                "chatType=" + chatType +
                ", fromAccount='" + fromAccount + '\'' +
                ", fromNickName='" + fromNickName + '\'' +
                ", fromClientId='" + fromClientId + '\'' +
                ", fromClientAvatar=" + fromClientAvatar +
                ", targetClientIds=" + targetClientIds +
                ", timestamp=" + timestamp +
                ", msgList=" + String.format("[%s]", msg.replaceAll("^,|,$", "")) +
                '}';
    }
}
