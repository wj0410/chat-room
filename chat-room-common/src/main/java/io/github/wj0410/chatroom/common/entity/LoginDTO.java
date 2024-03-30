package io.github.wj0410.chatroom.common.entity;

import io.github.wj0410.chatroom.common.enums.Platform;
import io.github.wj0410.chatroom.common.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author anlingyi
 * @date 2022/4/3 4:29 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable {
    private Long id;
    private String username;
    private String nickName;
    private String avatar;
    /**
     * 状态
     */
    private UserStatus userStatus;

    /**
     * 是否是重连
     */
    private boolean reconnected;

    /**
     * 客户端版本
     */
    private String clientVersion;

    /**
     * 令牌
     */
    private String token;

    /**
     * 来源平台
     */
    private Platform platform;

    public void setStatus(UserStatus status) {
        this.userStatus = status;
    }

    public void setStatus(String status) {
        try {
            this.userStatus = UserStatus.valueOf(status);
        } catch (Exception e) {
            this.userStatus = UserStatus.ON_LINE;
        }
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public void setPlatform(String platform) {
        try {
            this.platform = Platform.valueOf(platform);
        } catch (Exception e) {
            this.platform = Platform.WEBSOCKET;
        }
    }

}
