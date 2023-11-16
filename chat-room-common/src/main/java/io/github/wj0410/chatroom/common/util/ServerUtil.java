package io.github.wj0410.chatroom.common.util;

import io.github.wj0410.chatroom.common.constant.CommonConstants;
import io.github.wj0410.chatroom.common.data.ServerData;
import io.github.wj0410.chatroom.common.enums.ChatType;
import io.github.wj0410.chatroom.common.enums.ClientOrigin;
import io.github.wj0410.chatroom.common.enums.PromptType;
import io.github.wj0410.chatroom.common.message.*;
import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.cloudbox.tools.util.ObjectUtil;
import io.github.wj0410.cloudbox.tools.util.StringUtils;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * @author wangjie
 * @date 2023/10/25
 */
@Slf4j
public class ServerUtil extends ServerData {

    public static ClientModel getClientModelByClientId(String clientId) {
        return getClientModelMap().get(clientId);
    }

    public static ConcurrentHashMap<String, ClientModel> getClientModelMap() {
        return ServerData.getClientModelMap();
    }

    public static CopyOnWriteArraySet<ClientModel> getClientOnlineList() {
        return ServerData.getClientOnlineList();
    }

    public static String getClientId(ChannelHandlerContext ctx) {
        return ctx.channel().attr(AttributeKey.valueOf(CommonConstants.BIND_CLIENT_ID)).get() == null ? "" : (String) ctx.channel().attr(AttributeKey.valueOf(CommonConstants.BIND_CLIENT_ID)).get();
    }

    public static boolean hasClient(String clientId) {
        return ObjectUtil.isNotEmpty(getClientModelMap().get(clientId));
    }

    public static void addClient(ChannelHandlerContext ctx, BindMessage bindMessage, ClientOrigin clientOrigin) {
        ClientModel clientModel = new ClientModel();
        clientModel.setClientOrigin(clientOrigin);
        clientModel.setClientId(bindMessage.getClientId());
        clientModel.setAccount(bindMessage.getAccount());
        clientModel.setAvatar(bindMessage.getAvatar());
        clientModel.setNickName(bindMessage.getNickName());
        clientModel.setCtx(ctx);
        // 存储客户端数据
        ServerData.getClientOnlineList().add(clientModel);
        ServerData.getClientModelMap().put(bindMessage.getClientId(), clientModel);
        // 给所有客户端发送同步在线列表消息
        ServerUtil.sendSyncOnlineMessage();
        // 给所有客户端发送欢迎消息
        ServerUtil.sendWelcomeMessage(bindMessage.getClientId());
    }

    public static void removeClient(ClientModel clientModel) {
        if (clientModel == null || StringUtils.isBlank(clientModel.getClientId())) {
            return;
        }
        ConcurrentHashMap<String, ClientModel> clientModelMap = ServerData.getClientModelMap();
        CopyOnWriteArraySet<ClientModel> clientOnlineList = ServerData.getClientOnlineList();
        clientOnlineList.removeIf(item -> item.getClientId().equals(clientModel.getClientId()));
        clientModelMap.remove(clientModel.getClientId());
        // 给所有客户端发送同步在线列表消息
        ServerUtil.sendSyncOnlineMessage();
        // 给所有客户端发送离开消息
        ServerUtil.sendLeaveMessage(clientModel.getClientId(), clientModel.getNickName());
    }

    public static ClientModel getClientModel(ChannelHandlerContext ctx) {
        return ServerData.getClientModelMap().get(getClientId(ctx));
    }

    public static String formatClientAccount(ChannelHandlerContext ctx) {
        return formatClientAccount(getClientModel(ctx));
    }

    public static String formatClientAccount(ClientModel clientModel) {
        return String.format(" [clientId:%s] %s ", clientModel.getClientId(), clientModel.getAccount());
    }

    /**
     * 服务端发送同步列表消息
     */
    public static void sendSyncOnlineMessage() {
        // 服务端向所有客户端发送同步在线列表消息
        SyncOnlineMessage syncOnlineMessage = new SyncOnlineMessage();
        LinkedHashSet<ClientModel> newList = new LinkedHashSet<>();
        CopyOnWriteArraySet<ClientModel> clientOnlineList = ServerUtil.getClientOnlineList();
        ArrayList<ClientModel> copyList = new ArrayList<>(clientOnlineList);
        for (ClientModel clientModel : copyList) {
            newList.add(clientModel.clone());
        }
        syncOnlineMessage.setClientOnlineList(newList);
        String syncOnlineMessageJsonStr = MessageUtil.createSyncOnlineMessageJsonStr(syncOnlineMessage);
        for (ClientModel clientModel : copyList) {
            clientModel.writeAndFlush(syncOnlineMessageJsonStr);
        }
        log.info("服务端向所有客户端发送同步在线列表消息：{}", syncOnlineMessageJsonStr);
    }

    /**
     * 服务端发送welcome消息
     */
    public static void sendWelcomeMessage(String clientId) {
        ClientModel client = ServerUtil.getClientModelMap().get(clientId);
        if (client != null) {
            PromptMessage promptMessage = new PromptMessage();
            promptMessage.setPromptType(PromptType.WELCOME);
            promptMessage.setMsg(String.format(CommonConstants.WELCOME_PROMPT_OTHER, client.getNickName()));
            promptMessage.setClientId(clientId);
            String welcomeMessageJsonStr = MessageUtil.createPromptMessageJsonStr(promptMessage);
            for (ClientModel clientModel : ServerUtil.getClientOnlineList()) {
                clientModel.writeAndFlush(welcomeMessageJsonStr);
            }
            log.info("服务端向所有客户端发送欢迎消息：{}", welcomeMessageJsonStr);
        }
    }

    /**
     * 服务端发送leave消息
     */
    public static void sendLeaveMessage(String clientId, String userName) {
        PromptMessage promptMessage = new PromptMessage();
        promptMessage.setPromptType(PromptType.LEAVE);
        promptMessage.setMsg(String.format(CommonConstants.LEAVE_PROMPT_OTHER, userName));
        promptMessage.setClientId(clientId);
        String leaveMessageJsonStr = MessageUtil.createPromptMessageJsonStr(promptMessage);
        for (ClientModel clientModel : ServerUtil.getClientOnlineList()) {
            clientModel.writeAndFlush(leaveMessageJsonStr);
        }
        log.info("服务端向所有客户端发送离开消息：{}", leaveMessageJsonStr);
    }


    /**
     * 服务端发送refuse消息
     */
    public static void sendRefuseMessage(String clientId, String msg) {
        ClientModel client = ServerUtil.getClientModelMap().get(clientId);
        if (client != null) {
            RefuseMessage refuseMessage = new RefuseMessage();
            refuseMessage.setMsg(msg);
            refuseMessage.setClientId(clientId);
            String refuseMessageJsonStr = MessageUtil.createRefuseMessageJsonStr(refuseMessage);
            // 发送拒绝消息，并关闭连接
            client.writeAndFlush(refuseMessageJsonStr).addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 转发客户端消息
     */
    public static void relayNormalMessage(NormalMessage normalMessage) {
        String normalMessageJsonStr = MessageUtil.createNormalMessageJsonStr(normalMessage);
        ChatType chatType = normalMessage.getChatType();
        if (chatType.equals(ChatType.PUBLIC)) {
            // 聊天室消息
            CopyOnWriteArraySet<ClientModel> clientOnlineList = ServerUtil.getClientOnlineList();
            clientOnlineList.forEach(item -> {
                item.writeAndFlush(normalMessageJsonStr);
            });
            log.info("服务端向客户端 {} 转发消息：{}", clientOnlineList.toString(), normalMessage.toString());
        } else if (chatType.equals(ChatType.PRIVATE)) {
            // 私聊消息
            List<String> targetClientIds = normalMessage.getTargetClientIds();
            targetClientIds.forEach(item -> {
                ConcurrentHashMap<String, ClientModel> clientModelMap = ServerUtil.getClientModelMap();
                ClientModel clientModel = clientModelMap.get(item);
                if (clientModel != null) {
                    clientModel.writeAndFlush(normalMessageJsonStr);
                    log.info("服务端向客户端 {} 转发消息：{}", targetClientIds.toString(), normalMessage.toString());
                } else {
                    log.info("客户端[{}]已下线，消息停止转发：{}", item, normalMessage.toString());
                }
            });
        } else {
            log.info("未知消息类型ChatType，服务端无法转发。" + normalMessage.toString());
        }
    }

}
