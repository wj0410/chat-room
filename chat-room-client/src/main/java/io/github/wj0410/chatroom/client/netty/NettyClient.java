package io.github.wj0410.chatroom.client.netty;

import io.github.wj0410.chatroom.client.handler.ClientHandler;
import io.github.wj0410.chatroom.client.handler.ClientNormalHandler;
import io.github.wj0410.chatroom.client.handler.ClientSyncOnlineHandler;
import io.github.wj0410.chatroom.client.handler.ClientWelcomeHandler;
import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import lombok.extern.slf4j.Slf4j;


/**
 * @author wangjie
 * @date 2023/10/24
 */
@Slf4j
public class NettyClient {
    private int port;
    private String host;
    private ChannelFuture channelFuture;
    private EventLoopGroup workerGroup;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
        ClientHolder.nettyClient = this;
    }

    public ChannelFuture start() {
        Bootstrap bootstrap = new Bootstrap();
        workerGroup = new NioEventLoopGroup();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // 在第一个请求包中编码用户ID
                        ch.pipeline().addLast(
                                new JsonObjectDecoder(),
                                new ClientHandler(),
                                new ClientSyncOnlineHandler(),
                                new ClientWelcomeHandler(),
                                new ClientNormalHandler()
                        );
                    }
                });
        try {
            // sync()方法会让主线程阻塞,直到绑定动作完成。
            channelFuture = bootstrap.connect(host, port).sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    log.info("客户端启动失败");
                }
            }
        });
        return channelFuture;
    }

    public void shutDown() {
        try {
            channelFuture.channel().close();
            System.exit(0);
        } finally {
            if (workerGroup != null) {
                workerGroup.shutdownGracefully();
            }
        }
    }

}
