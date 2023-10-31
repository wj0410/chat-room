package io.github.wj0410.chatroom.server.util;

import io.github.wj0410.chatroom.common.message.BindMessage;
import io.github.wj0410.chatroom.common.message.NormalMessage;
import io.github.wj0410.chatroom.common.message.SyncOnlineMessage;
import io.github.wj0410.chatroom.common.message.WelcomeMessage;
import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.github.wj0410.chatroom.common.util.UIUtil;
import io.github.wj0410.chatroom.server.data.ServerData;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author wangjie
 * @date 2023/10/25
 */
@Slf4j
public class ServerUtil extends ServerData {

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

    public static String getClientAccount(ChannelHandlerContext ctx) {
        return getClientModel(ctx).getAccount();
    }

    public static String getClientUserName(ChannelHandlerContext ctx) {
        return getClientModel(ctx).getUserName();
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
            WelcomeMessage welcomeMessage = new WelcomeMessage();
            welcomeMessage.setMsg(client.getUserName() + " 进入了聊天室");
            welcomeMessage.setClientId(clientId);
            welcomeMessage.setTimestamp(System.currentTimeMillis());
            String welcomeMessageJsonStr = MessageUtil.createWelcomeMessageJsonStr(welcomeMessage);
            for (ClientModel clientModel : ServerUtil.getClientOnlineList()) {
                clientModel.getCtx().writeAndFlush(MessageUtil.convert2ByteBuf(welcomeMessageJsonStr));
            }
            log.info("服务端向所有客户端发送欢迎消息：{}", welcomeMessageJsonStr);
        }
    }

    /**
     * 转发客户端消息
     */
    public static void relayNormalMessage(NormalMessage normalMessage) {
        List<String> targetClientIds = normalMessage.getTargetClientIds();
        String normalMessageJsonStr = MessageUtil.createNormalMessageJsonStr(normalMessage);
        if (CollectionUtils.isEmpty(targetClientIds)) {
            // 聊天室消息
            LinkedList<ClientModel> clientOnlineList = ServerUtil.getClientOnlineList();
            clientOnlineList.forEach(item -> {
                item.getCtx().writeAndFlush(MessageUtil.convert2ByteBuf(normalMessageJsonStr));
            });
            log.info("服务端向客户端 {} 转发消息：{}", clientOnlineList.toString(), normalMessageJsonStr);
        } else {
            // 发送给指定用户的消息
            targetClientIds.forEach(item -> {
                ConcurrentHashMap<String, ClientModel> clientModelMap = ServerUtil.getClientModelMap();
                clientModelMap.get(item).getCtx().writeAndFlush(MessageUtil.convert2ByteBuf(normalMessageJsonStr));
            });
            log.info("服务端向客户端 {} 转发消息：{}", targetClientIds.toString(), normalMessageJsonStr);
        }

    }

    /**
     * 在控制台上输出内容
     *
     * @param str
     */
    public static void drawConsole(JTextPane consolePane, String str) {
        StyledDocument styledDoc = consolePane.getStyledDocument();
        UIUtil.buildServerConsoleStyle(styledDoc);
        consolePane.setDocument(styledDoc);
        try {
            styledDoc.insertString(styledDoc.getLength(), str + "\n", styledDoc.getStyle(UIUtil.SERVER_CONSOLE_STYLE_NAME));
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

}
