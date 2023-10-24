package io.github.wj0410.chatroom.server;

import io.github.wj0410.chatroom.common.util.UIUtil;
import io.github.wj0410.chatroom.server.ui.ServerUI;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * @author wangjie
 * @date 2023/10/23
 */
public class Server {
    private ChannelFuture channelFuture;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start(ServerUI serverUI) throws Exception {
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(
                                new LoggingHandler(LogLevel.DEBUG),
                                new StringEncoder(UTF_8),
                                new StringDecoder(UTF_8),
                                new ServerHandler(serverUI)
                        );
                    }
                });
        this.channelFuture = bootstrap.bind(port);
        this.channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    UIUtil.drawConsole(serverUI.getConsolePane(), "服务启动失败");
                } else {
                    UIUtil.drawConsole(serverUI.getConsolePane(), "Server,启动Netty服务端成功，端口号:" + port);
                }
            }
        });
    }

    public void shutDown() {
        try {
            channelFuture.channel().close();
            System.exit(0);
        } finally {
            if (workerGroup != null) {
                workerGroup.shutdownGracefully();
            }
            if (bossGroup != null) {
                bossGroup.shutdownGracefully();
            }
        }
    }
}
