package io.github.wj0410.chatroom.server.handler;

import io.github.wj0410.chatroom.common.message.MessageRequest;
import io.github.wj0410.chatroom.common.util.UIUtil;
import io.github.wj0410.chatroom.server.Server;
import io.github.wj0410.chatroom.server.data.ServerData;
import io.github.wj0410.chatroom.server.model.ClientModel;
import io.github.wj0410.chatroom.server.util.ServerUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Iterator;

/**
 * @author wangjie
 * @date 2023/10/23
 */
public class ServerHandler extends SimpleChannelInboundHandler<MessageRequest> {
    private Server server;

    public ServerHandler(Server server) {
        this.server = server;
    }

    /**
     * 客户端下线
     *
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        for (Iterator<ClientModel> iterator = ServerData.clientOnlineList.iterator(); iterator.hasNext(); ) {
            ClientModel next = iterator.next();
            if (next.getClientId().equals(ServerUtil.getClientId(ctx))) {
                ServerData.clientOnlineList.remove(next);
                break;
            }
        }
        if (server.getServerUI() != null) {
            UIUtil.drawConsole(server.getServerUI().getConsolePane(), String.format("客户端 %s 下线了...", ServerUtil.getClientId(ctx)));
            server.getServerUI().flushClientOnlineList();
        }
    }

    /**
     * 接收消息
     *
     * @param ctx
     * @param messageRequest
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequest messageRequest) {
        System.out.println(String.format("服务端收到客户端[%s]消息,%s", ServerUtil.getClientId(ctx), messageRequest.toString()));
        if (server.getServerUI() != null) {
            UIUtil.drawConsole(server.getServerUI().getConsolePane(), String.format("服务端收到客户端 %s 消息：%s", ServerUtil.getClientId(ctx), messageRequest.toString()));
        }
    }


}
