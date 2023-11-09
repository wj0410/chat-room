package io.github.wj0410.chatroom.common.data;


import io.github.wj0410.chatroom.common.model.ClientModel;

import java.util.LinkedHashSet;
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

    private static LinkedHashSet<ClientModel> clientOnlineList = new LinkedHashSet<>();


    protected static ConcurrentHashMap<String, ClientModel> getClientModelMap() {
        return clientModelMap;
    }

    protected static LinkedHashSet<ClientModel> getClientOnlineList() {
        return clientOnlineList;
    }
}
