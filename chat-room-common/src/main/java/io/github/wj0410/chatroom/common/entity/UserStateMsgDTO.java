package io.github.wj0410.chatroom.common.entity;

import io.github.wj0410.chatroom.common.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户上线、离线状态消息
 *
 * @author anlingyi
 * @date 2022/7/23 3:35 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStateMsgDTO implements Serializable {
    private User user;

    private UserStatus userStatus;

}
