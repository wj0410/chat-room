package io.github.wj0410.chatroom.client.ui.model;

import lombok.Data;

/**
 * @author wangjie
 * @date 2023/10/31
 */
@Data
public class OnlineModel {
    private String clientId;
    private String account;
    private String userName;
    private int unreadCount;
}
