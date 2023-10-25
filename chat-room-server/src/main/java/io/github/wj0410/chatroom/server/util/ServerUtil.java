package io.github.wj0410.chatroom.server.util;

import io.github.wj0410.chatroom.common.constant.CommonConstants;
import io.github.wj0410.chatroom.common.message.BindRequest;
import io.github.wj0410.chatroom.server.data.ServerData;
import io.github.wj0410.chatroom.server.model.ClientModel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

/**
 * @author wangjie
 * @date 2023/10/25
 */
public class ServerUtil {

    public static void addClient(ChannelHandlerContext ctx, BindRequest bindRequest) {
        ClientModel clientModel = new ClientModel();
        clientModel.setClientId(bindRequest.getClientId());
        clientModel.setUserName(bindRequest.getUserName());
        clientModel.setCtx(ctx);
        clientModel.setChannel(ctx.channel());
        ServerData.clientOnlineList.add(clientModel);
    }

    public static String getClientId(ChannelHandlerContext ctx) {
        return (String) ctx.channel().attr(AttributeKey.valueOf(CommonConstants.CLIENT_ID)).get();
    }
}
