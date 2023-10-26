package io.github.wj0410.chatroom.client.holder;


import io.github.wj0410.chatroom.client.NettyClient;
import io.github.wj0410.chatroom.client.ui.ChatRoomUI;
import io.github.wj0410.chatroom.client.ui.LoginUI;

/**
 * @author wangjie
 * @date 2023/10/26
 */
public class ClientHolder {
    public static NettyClient nettyClient;
    public static LoginUI loginUI;
    public static ChatRoomUI chatRoomUI;

    private ClientHolder() {

    }
}
