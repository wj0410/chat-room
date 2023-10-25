package io.github.wj0410.chatroom.client;

import io.github.wj0410.chatroom.common.message.BindRequest;
import io.github.wj0410.chatroom.common.message.MessageRequest;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author wangjie
 * @date 2023/10/23
 */
public class ClientHandler extends SimpleChannelInboundHandler<MessageRequest> {
    private Client client;

    public ClientHandler(Client client) {
        this.client = client;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        BindRequest bindRequest = new BindRequest();
        bindRequest.setClientId(channel.id().toString());
        bindRequest.setUserName(client.getAccount());
        ctx.writeAndFlush(bindRequest);
        System.out.println("客户端向服务端发送绑定channelId请求，" + bindRequest.toString());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequest messageRequest) {
        System.out.println("client: 读取到服务端消息：" + messageRequest.toString());

    }

}
