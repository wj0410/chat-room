package io.github.wj0410.chatroom.server.builder;


import io.github.wj0410.chatroom.common.entity.Response;
import io.github.wj0410.chatroom.common.entity.User;
import io.github.wj0410.chatroom.common.enums.MessageType;

/**
 * @author anlingyi
 * @date 2020/8/17
 */
public class ResponseBuilder {

    public static Response build(User user, Object msg, MessageType messageType) {
        return new Response(user, msg, messageType);
    }
    public static Response system(String msg) {
        return build(null, msg, MessageType.SYSTEM);
    }
}
