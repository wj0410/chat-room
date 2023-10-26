package io.github.wj0410.chatroom.server.util;

import io.github.wj0410.chatroom.common.message.BindMessage;
import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.server.data.ServerData;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.netty.channel.ChannelHandlerContext;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author wangjie
 * @date 2023/10/25
 */
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
        // TODO 获取用户名称
        clientModel.setUserName(bindMessage.getAccount());
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
}
