package io.github.wj0410.chatroom.client.holder;


import io.github.wj0410.chatroom.client.conf.ClientProperties;
import io.github.wj0410.chatroom.client.netty.NettyClient;
import io.github.wj0410.chatroom.client.ui.swing.ChatRoomUI;
import io.github.wj0410.chatroom.client.ui.swing.LoginUI;
import io.github.wj0410.chatroom.client.ui.swing.PrivateChatUI;
import io.github.wj0410.chatroom.common.model.ClientModel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjie
 * @date 2023/10/26
 */
public class ClientHolder {
    public static ClientProperties clientProperties;
    public static ClientModel clientInfo;
    public static NettyClient nettyClient;
    public static LoginUI loginUI;
    public static ChatRoomUI chatRoomUI;
    /**
     * key : 对方的clientId
     */
    public static Map<String, PrivateChatUI> privateChatUIMap = new HashMap<>();

    private ClientHolder() {

    }

}
