package io.github.wj0410.chatroom.server.action;

import io.github.wj0410.chatroom.common.entity.*;
import io.github.wj0410.chatroom.common.enums.ChatType;
import io.github.wj0410.chatroom.common.enums.MessageType;
import io.github.wj0410.chatroom.common.enums.UserStatus;
import io.github.wj0410.chatroom.server.builder.ResponseBuilder;
import io.github.wj0410.chatroom.server.cache.UserCache;
import io.github.wj0410.chatroom.server.factory.ObjectFactory;
import io.github.wj0410.chatroom.server.service.AbstractResponseHistoryService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ChannelAction {
    private static final ChannelGroup GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void add(Channel channel) {
        GROUP.add(channel);
    }

    public static void sendOnlineUsers(User user) {
        Response response = ResponseBuilder.build(null, new OnlineListMsgDTO(UserCache.listUser()), MessageType.SYNC_ONLINE_LIST);
        if (user == null) {
            send(response);
        } else {
            user.send(response);
        }
    }

    public static String getChannelId(ChannelHandlerContext ctx) {
        return ctx.channel().id().asLongText();
    }

    public static void cleanUser(ChannelHandlerContext ctx) {
        cleanUser(getChannelId(ctx));
    }

    public static User cleanUser(String channelId) {
        log.debug("清理用户, channelId -> {}", channelId);
        User user = getUser(channelId);
        if (user == null) {
            return null;
        }
        UserCache.remove(channelId);
        sendUserState(user, UserStatus.OFF_LINE);
        return user;
    }

    public static User getUser(ChannelHandlerContext ctx) {
        return getUser(getChannelId(ctx));
    }

    public static User getUser(String channelId) {
        return UserCache.get(channelId);
    }

    public static void send(Response resp) {
        GROUP.writeAndFlush(resp);
        if (resp.getType() == MessageType.CHAT
                && resp.getBody() instanceof UserMsgDTO
                && (((UserMsgDTO) resp.getBody()).getChatType().equals(ChatType.PUBLIC))) {
            // 公共聊天室消息记录历史消息
            ObjectFactory.getObject(AbstractResponseHistoryService.class).addHistory(resp);
        }
    }

    public static void send(User user, Object body, MessageType messageType) {
        send(ResponseBuilder.build(user, body, messageType));
    }

    // 上线下线
    public static void sendUserState(User user, UserStatus state) {
        send(ResponseBuilder.build(null, new UserStateMsgDTO(user, state), MessageType.USER_STATE));
    }

    // 用户状态更新
    public static void updateUserStatus(User user) {
        send(ResponseBuilder.build(user, null, MessageType.STATUS_UPDATE));
    }
}
