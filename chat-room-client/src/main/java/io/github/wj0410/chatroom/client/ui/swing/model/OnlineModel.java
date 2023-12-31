package io.github.wj0410.chatroom.client.ui.swing.model;
import lombok.Data;

/**
 * @author wangjie
 * @date 2023/10/31
 */
@Data
public class OnlineModel {
    private String clientId;
    private String account;
    private String nickName;
    private int unreadCount;
}
