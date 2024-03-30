package io.github.wj0410.chatroom.common.enums;

import lombok.Getter;

/**
 * 用户状态
 *
 * @author anlingyi
 * @date 2020/6/1
 */
@Getter
public enum UserStatus {
    ON_LINE("在线"),
    OFF_LINE("离线"),
    IN_GAME("游戏中");

    private String name;

    private UserStatus(String name) {
        this.name = name;
    }
}
