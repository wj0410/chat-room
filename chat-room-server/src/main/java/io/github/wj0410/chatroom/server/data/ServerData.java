package io.github.wj0410.chatroom.server.data;


import io.github.wj0410.chatroom.common.model.ClientModel;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangjie
 * @date 2023/10/24
 */
public class ServerData {

    /**
     * key : clientId
     */
    private static ConcurrentHashMap<String, ClientModel> clientModelMap = new ConcurrentHashMap<>();

    private static LinkedList<ClientModel> clientOnlineList = new LinkedList<>();

    protected static ConcurrentHashMap<String, ClientModel> getClientModelMap() {
        return clientModelMap;
    }

    protected static LinkedList<ClientModel> getClientOnlineList() {
        return clientOnlineList;
    }
}
