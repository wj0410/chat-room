package io.github.wj0410.chatroom.common.data;


import io.github.wj0410.chatroom.common.model.ClientModel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author wangjie
 * @date 2023/10/24
 */
public class ServerData {

    /**
     * key : clientId
     */
    private static final ConcurrentHashMap<String, ClientModel> clientModelMap = new ConcurrentHashMap<>();

    private static final CopyOnWriteArraySet<ClientModel> clientOnlineList = new CopyOnWriteArraySet<>();


    protected static ConcurrentHashMap<String, ClientModel> getClientModelMap() {
        return clientModelMap;
    }

    protected static CopyOnWriteArraySet<ClientModel> getClientOnlineList() {
        return clientOnlineList;
    }
}
