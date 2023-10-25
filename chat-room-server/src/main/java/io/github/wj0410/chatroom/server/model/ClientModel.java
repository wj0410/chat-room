package io.github.wj0410.chatroom.server.model;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

/**
 * @author wangjie
 * @date 2023/10/25
 */
@Data
public class ClientModel {
    private String clientId;
    private String userName;
    private ChannelHandlerContext ctx;
    private Channel channel;
}
