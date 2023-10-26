package io.github.wj0410.chatroom.common.model;

import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

/**
 * @author wangjie
 * @date 2023/10/25
 */
@Data
public class ClientModel {

    private String clientId;
    private String account;
    private String userName;
    private ChannelHandlerContext ctx;

}
