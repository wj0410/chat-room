package io.github.wj0410.chatroom.common.model;

import io.github.wj0410.chatroom.common.enums.MessageContainerType;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ObjectUtils;

/**
 * @author wangjie
 */
@Data
public class MessageContainer {
    /**
     * 1.文本 2.图片
     */
    private MessageContainerType type;
    private String text;
    private byte[] imageData;

    public MessageContainer() {
    }

    public MessageContainer(String text, byte[] imageData) {
        if (StringUtils.isNotBlank(text)) {
            this.text = text;
            type = MessageContainerType.TEXT;
        } else if (!ObjectUtils.isEmpty(imageData)) {
            this.imageData = imageData;
            type = MessageContainerType.IMAGE;
        }
    }

}
