package io.github.wj0410.chatroom.common.message;

import lombok.Data;

/**
 * 绑定类型消息
 * @author wangjie
 * @date 2023/10/25
 */
@Data
public class BindMessage {
    private String clientId;
    private String account;

    @Override
    public String toString() {
        return "BindRequest{" +
                "clientId='" + clientId + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}