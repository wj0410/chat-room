package io.github.wj0410.chatroom.common.entity;

import cn.hutool.core.map.MapUtil;
import io.github.wj0410.chatroom.common.constant.IpConstants;
import io.github.wj0410.chatroom.common.enums.Platform;
import io.github.wj0410.chatroom.common.enums.UserStatus;
import io.netty.channel.Channel;
import lombok.Data;

import java.util.Objects;

@Data
public class User {
    private Long id;
    private String channelId;
    private String username;
    private String nickName;
    private String avatar;
    private Platform platform;
    private String shortRegion;
    private UserStatus status;
    private transient String ip;
    private transient IpRegion region;
    private transient Channel channel;

    public User(LoginDTO loginDTO, String channelId, String ip, IpRegion region, Channel channel) {
        this.id = loginDTO.getId();
        this.channelId = channelId;
        this.username = loginDTO.getUsername();
        this.nickName = loginDTO.getNickName();
        this.avatar = loginDTO.getAvatar();
        this.status = UserStatus.ON_LINE;
        this.ip = ip;
        this.region = region;
        this.channel = channel;
        this.platform = loginDTO.getPlatform();
        this.shortRegion = MapUtil.getStr(IpConstants.SHORT_PROVINCE, region.getProvince(), region.getCountry());
    }

    public void send(Response response) {
        if (channel == null) {
            return;
        }

        channel.writeAndFlush(response);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
