package io.github.wj0410.chatroom.client.handler;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.common.message.BindMessage;
import io.github.wj0410.chatroom.common.util.MessageUtil;
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
        BindMessage bindMessage = new BindMessage();
        bindMessage.setClientId(channel.id().toString());
        bindMessage.setAccount(ClientHolder.clientInfo.getAccount());
        bindMessage.setUserName(ClientHolder.clientInfo.getUserName());
        String bindMessageJsonStr = MessageUtil.createBindMessageJsonStr(bindMessage);
        ctx.writeAndFlush(bindMessageJsonStr);
        log.info("客户端向服务端发送绑定channelId请求，{}", bindMessageJsonStr);
        // 将客户端client信息记录到ClientHolder
        ClientHolder.clientInfo.setClientId(bindMessage.getClientId());
        ClientHolder.clientInfo.setCtx(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        Object message = MessageUtil.getMessage(msg.toString());
        ctx.fireChannelRead(message);
    }

}
