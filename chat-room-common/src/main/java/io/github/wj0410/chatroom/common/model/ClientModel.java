package io.github.wj0410.chatroom.common.model;

import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

/**
 * @author wangjie
 * @date 2023/10/25
 */
@Data
public class ClientModel {
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 客户端在服务端的ctx
     */
    private ChannelHandlerContext ctx;

}
