package io.github.wj0410.chatroom.client.handler;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.util.ClientUtil;
import io.github.wj0410.chatroom.common.message.BindMessage;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


/**
 * @author wangjie
 * @date 2023/10/23
 */
@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<Object> {
    /**
     * 客户端上线
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String clientId = channel.id().toString();
        // 将客户端client信息记录到ClientHolder
        ClientHolder.clientInfo.setClientId(clientId);
        ClientHolder.clientInfo.setCtx(ctx);
        BindMessage bindMessage = new BindMessage();
        bindMessage.setClientId(clientId);
        bindMessage.setAccount(ClientHolder.clientInfo.getAccount());
        bindMessage.setUserName(ClientHolder.clientInfo.getUserName());
        bindMessage.setClientVersion(ClientHolder.clientProperties.getClient().getVersion());
        // 发送绑定消息
        ClientUtil.sendBindMessage(ctx, bindMessage);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf) msg;
            Object message = MessageUtil.getMessage(MessageUtil.convert2String(byteBuf));
            // 将msg交给下一个handler处理
            ctx.fireChannelRead(message);
        }
    }

}
