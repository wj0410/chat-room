package io.github.wj0410.chatroom.common.model;

import io.github.wj0410.chatroom.common.enums.MessageContainerType;
import lombok.Data;

/**
 * @author wangjie
 */
@Data
public class MessageContainer {
    /**
     * 文本、图片
     */
    private MessageContainerType type;
    private String text;
    private byte[] imageData;

    public MessageContainer() {
    }

    public MessageContainer(String text) {
        this.text = text;
        type = MessageContainerType.TEXT;
    }
    public MessageContainer(byte[] imageData) {
        this.imageData = imageData;
        type = MessageContainerType.IMAGE;
    }

}
