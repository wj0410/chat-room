package io.github.wj0410.chatroom.server.util;

import io.github.wj0410.chatroom.common.constant.CommonConstants;
import io.github.wj0410.chatroom.common.message.BindRequest;
import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.common.util.UIUtil;
import io.github.wj0410.chatroom.server.data.ServerData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author wangjie
 * @date 2023/10/25
 */
public class ServerUtil extends ServerData {

    public static ConcurrentHashMap<ChannelHandlerContext, ClientModel> getClientModelMap() {
        return ServerData.getClientModelMap();
    }

    public static LinkedList<ClientModel> getClientOnlineList() {
        return ServerData.getClientOnlineList();
    }

    public static void addClient(ChannelHandlerContext ctx, BindRequest bindRequest) {
        ClientModel clientModel = new ClientModel();
        clientModel.setClientId(bindRequest.getClientId());
        clientModel.setUserName(bindRequest.getUserName());
        clientModel.setCtx(ctx);
        ServerData.getClientOnlineList().add(clientModel);
        ServerData.getClientModelMap().put(ctx, clientModel);
    }

    public static void removeClient(ChannelHandlerContext ctx) {
        ServerData.getClientOnlineList().remove(ServerData.getClientModelMap().get(ctx));
        ServerData.getClientModelMap().remove(ctx);
    }

    public static ClientModel getClientModel(ChannelHandlerContext ctx) {
        return ServerData.getClientModelMap().get(ctx);
    }

    public static String getClientId(ChannelHandlerContext ctx) {
        return (String) ctx.channel().attr(AttributeKey.valueOf(CommonConstants.CLIENT_ID)).get();
    }

    public static String getClientUserName(ChannelHandlerContext ctx) {
        return getClientModel(ctx).getUserName();
    }

    public static String getFormatClient(ChannelHandlerContext ctx) {
        return UIUtil.formatClientName(getClientModel(ctx));
    }
}
