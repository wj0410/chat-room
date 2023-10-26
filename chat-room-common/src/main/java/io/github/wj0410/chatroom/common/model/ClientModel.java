package io.github.wj0410.chatroom.common.model;

import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangjie
 * @date 2023/10/25
 */
@Data
public class ClientModel implements Serializable {
    private String clientId;
    private String account;
    private String userName;
    private ChannelHandlerContext ctx;
}
