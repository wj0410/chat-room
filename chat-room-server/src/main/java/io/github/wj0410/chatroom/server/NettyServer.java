package io.github.wj0410.chatroom.server;

import io.github.wj0410.chatroom.common.util.UIUtil;
import io.github.wj0410.chatroom.server.handler.ServerBindClientHandler;
import io.github.wj0410.chatroom.server.handler.ServerHandler;
import io.github.wj0410.chatroom.server.handler.ServerNormalHandler;
import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.github.wj0410.chatroom.server.util.ServerUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

/**
 * @author wangjie
 * @date 2023/10/23
 */
public class NettyServer {
    private static volatile NettyServer instance;
    private ChannelFuture channelFuture;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private int port;

    public static NettyServer getInstance(int port) {
        if (instance == null) {
            synchronized (NettyServer.class) {
                if (instance == null) {
                    instance = new NettyServer(port);
                }
            }
        }
        return instance;
    }

    private NettyServer(int port) {
        this.port = port;
        ServerHolder.nettyServer = this;
    }

    public void start() throws Exception {
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ServerHolder.serverSocketChannel = ch;
                        ch.pipeline().addLast(
                                new JsonObjectDecoder(),
                                new ServerHandler(),
                                new ServerBindClientHandler(),
                                new ServerNormalHandler()
                        );
                    }
                });
        // sync()方法会让主线程阻塞,直到绑定动作完成。
        this.channelFuture = bootstrap.bind(port).sync();
        this.channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    ServerUtil.drawConsole(ServerHolder.serverUI.getConsolePane(), "服务启动失败");
                } else {
                    ServerUtil.drawConsole(ServerHolder.serverUI.getConsolePane(), "Server：启动Netty服务端成功，端口号:" + port);

                }
            }
        });
    }

    public void shutDown() {
        try {
            channelFuture.channel().close();
            ServerHolder.nettyServer = null;
            ServerUtil.drawConsole(ServerHolder.serverUI.getConsolePane(), "Server：服务已停止！");
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
