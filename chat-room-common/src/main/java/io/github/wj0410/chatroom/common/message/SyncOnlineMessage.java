package io.github.wj0410.chatroom.common.message;

import io.github.wj0410.chatroom.common.model.ClientModel;
import lombok.Data;

import java.util.LinkedHashSet;

/**
 * @author wangjie
 * @date 2023/10/26
 */
@Data
public class SyncOnlineMessage {
    private LinkedHashSet<ClientModel> clientOnlineList;

    @Override
    public String toString() {
        return "SyncOnlineMessage{" +
                "clientOnlineList=" + clientOnlineList +
                '}';
    }
}
