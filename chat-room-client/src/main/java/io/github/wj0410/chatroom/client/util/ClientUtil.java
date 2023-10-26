package io.github.wj0410.chatroom.client.util;

import io.github.wj0410.chatroom.common.model.ClientModel;

/**
 * @author wangjie
 * @date 2023/10/26
 */
public class ClientUtil {
    /**
     * 格式化展示在线列表
     *
     * @param clientModel
     * @return
     */
    public static String formatClientAccount(ClientModel clientModel) {
        return clientModel.getUserName();
    }
}
