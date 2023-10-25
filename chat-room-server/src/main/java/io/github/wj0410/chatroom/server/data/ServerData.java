package io.github.wj0410.chatroom.server.data;

import io.github.wj0410.chatroom.common.model.ClientModel;
import io.netty.channel.ChannelHandlerContext;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangjie
 * @date 2023/10/24
 */
public class ServerData {

    protected static ConcurrentHashMap<ChannelHandlerContext, ClientModel> getClientModelMap() {
        return clientModelMap;
    }

    protected static LinkedList<ClientModel> getClientOnlineList() {
        return clientOnlineList;
    }

    private static ConcurrentHashMap<ChannelHandlerContext, ClientModel> clientModelMap = new ConcurrentHashMap<>();

    private static LinkedList<ClientModel> clientOnlineList = new LinkedList<>();

}
