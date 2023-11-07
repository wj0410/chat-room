package io.github.wj0410.chatroom.server.util;

import io.github.wj0410.chatroom.common.constant.CommonConstants;
import io.github.wj0410.chatroom.common.enums.ChatType;
import io.github.wj0410.chatroom.common.enums.PromptType;
import io.github.wj0410.chatroom.common.message.*;
import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.github.wj0410.chatroom.server.data.ServerData;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


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

    public static LinkedList<ClientModel> getClientOnlineList() {
        return ServerData.getClientOnlineList();
    }

    public static void addClient(ChannelHandlerContext ctx, BindMessage bindMessage) {
        ClientModel clientModel = new ClientModel();
        clientModel.setClientId(bindMessage.getClientId());
        clientModel.setAccount(bindMessage.getAccount());
        clientModel.setUserName(bindMessage.getUserName());
        clientModel.setCtx(ctx);
        ServerData.getClientOnlineList().add(clientModel);
        ServerData.getClientModelMap().put(ServerHolder.getClientId(ctx), clientModel);
    }

    public static void removeClient(ChannelHandlerContext ctx) {
        String clientId = ServerHolder.getClientId(ctx);
        ServerData.getClientOnlineList().remove(ServerData.getClientModelMap().get(clientId));
        ServerData.getClientModelMap().remove(clientId);
    }

    public static ClientModel getClientModel(ChannelHandlerContext ctx) {
        return ServerData.getClientModelMap().get(ServerHolder.getClientId(ctx));
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
        LinkedList<ClientModel> clientOnlineList = ServerUtil.getClientOnlineList();
        LinkedList<ClientModel> newList = new LinkedList<>();
        for (ClientModel clientModel : clientOnlineList) {
            ClientModel client = new ClientModel();
            BeanUtils.copyProperties(clientModel, client);
            client.setCtx(null);
            newList.add(client);
        }
        syncOnlineMessage.setClientOnlineList(newList);
        String syncOnlineMessageJsonStr = MessageUtil.createSyncOnlineMessageJsonStr(syncOnlineMessage);
        for (ClientModel clientModel : clientOnlineList) {
            clientModel.getCtx().writeAndFlush(MessageUtil.convert2ByteBuf(syncOnlineMessageJsonStr));
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
            promptMessage.setMsg(String.format(CommonConstants.WELCOME_PROMPT_OTHER, client.getUserName()));
            promptMessage.setClientId(clientId);
            String welcomeMessageJsonStr = MessageUtil.createPromptMessageJsonStr(promptMessage);
            for (ClientModel clientModel : ServerUtil.getClientOnlineList()) {
                clientModel.getCtx().writeAndFlush(MessageUtil.convert2ByteBuf(welcomeMessageJsonStr));
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
            clientModel.getCtx().writeAndFlush(MessageUtil.convert2ByteBuf(leaveMessageJsonStr));
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
            ChannelHandlerContext ctx = getClientModelByClientId(clientId).getCtx();
            // 发送拒绝消息，并关闭连接
            ctx.writeAndFlush(MessageUtil.convert2ByteBuf(refuseMessageJsonStr)).addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 转发客户端消息
     */
    public static void relayNormalMessage(NormalMessage normalMessage) {
        List<String> targetClientIds = normalMessage.getTargetClientIds();
        String normalMessageJsonStr = MessageUtil.createNormalMessageJsonStr(normalMessage);
        ChatType chatType = normalMessage.getChatType();
        if (chatType.equals(ChatType.PUBLIC)) {
            // 聊天室消息
            LinkedList<ClientModel> clientOnlineList = ServerUtil.getClientOnlineList();
            clientOnlineList.forEach(item -> {
                item.getCtx().writeAndFlush(MessageUtil.convert2ByteBuf(normalMessageJsonStr));
            });
            log.info("服务端向客户端 {} 转发消息：{}", clientOnlineList.toString(), normalMessage.toString());
        } else if (chatType.equals(ChatType.PRIVATE)) {
            // 私聊消息
            targetClientIds.forEach(item -> {
                ConcurrentHashMap<String, ClientModel> clientModelMap = ServerUtil.getClientModelMap();
                ClientModel clientModel = clientModelMap.get(item);
                if (clientModel != null) {
                    clientModel.getCtx().writeAndFlush(MessageUtil.convert2ByteBuf(normalMessageJsonStr));
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
