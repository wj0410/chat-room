package io.github.wj0410.chatroom.server.handler;

import io.github.wj0410.chatroom.common.message.BindMessage;
import io.github.wj0410.chatroom.common.message.SyncOnlineMessage;
import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.github.wj0410.chatroom.common.util.UIUtil;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.github.wj0410.chatroom.server.util.ServerUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.LinkedList;


/**
 * @author wangjie
 * @date 2023/10/25
 */
@Slf4j
public class ServerBindClientHandler extends SimpleChannelInboundHandler<BindMessage> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, BindMessage bindMessage) {
        // 将clientId保存到Channel中,后面可以找到
        ServerHolder.setClientIdAttr(bindMessage.getClientId());
        log.info("服务端接收到客户端的绑定信息：{}", bindMessage.toString());
        ServerUtil.addClient(ctx, bindMessage);
        if (ServerHolder.serverUI != null) {
            UIUtil.drawConsole(ServerHolder.serverUI.getConsolePane(), String.format("客户端 %s 连接了...", ServerUtil.formatClientAccount(ctx)));
            ServerHolder.serverUI.flushClientOnlineList();
        }
        // 服务端向所有客户端发送同步在线列表消息
        SyncOnlineMessage syncOnlineMessage = new SyncOnlineMessage();
        LinkedList<ClientModel> clientOnlineList = ServerUtil.getClientOnlineList();
        LinkedList<ClientModel> newList = new LinkedList<>();
        for (ClientModel clientModel : clientOnlineList) {
            ClientModel client = new ClientModel();
            BeanUtils.copyProperties(clientModel,client);
            client.setCtx(null);
            newList.add(client);
        }
        syncOnlineMessage.setClientOnlineList(newList);
        String syncOnlineMessageJsonStr = MessageUtil.createSyncOnlineMessageJsonStr(syncOnlineMessage);
        for (ClientModel clientModel : clientOnlineList) {
            clientModel.getCtx().writeAndFlush(syncOnlineMessageJsonStr);
        }
        log.info("服务端向所有客户端发送同步在线列表消息：{}", syncOnlineMessageJsonStr);
    }
}
