package io.github.wj0410.chatroom.server;

import io.github.wj0410.chatroom.common.decoder.BindRequestDecoder;
import io.github.wj0410.chatroom.common.decoder.MessageRequestDecoder;
import io.github.wj0410.chatroom.common.encoder.BindRequestEncoder;
import io.github.wj0410.chatroom.common.encoder.MessageRequestEncoder;
import io.github.wj0410.chatroom.common.util.UIUtil;
import io.github.wj0410.chatroom.server.handler.BindClientHandler;
import io.github.wj0410.chatroom.server.handler.ServerHandler;
import io.github.wj0410.chatroom.server.ui.ServerUI;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author wangjie
 * @date 2023/10/23
 */
public class Server {
    private ChannelFuture channelFuture;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private int port;
    private ServerUI serverUI;

    public Server(int port) {
        this.port = port;
    }

    public void start(ServerUI serverUI) throws Exception {
        this.serverUI = serverUI;
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
        Server server = this;
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(
                                new BindRequestDecoder(),// 对接收自客户端的绑定类型消息响应进行自定义解码
                                new MessageRequestDecoder(),// 对接收自客户端的消息响应进行自定义解码
                                new BindRequestEncoder(),
                                new MessageRequestEncoder(),// 对写出到客户端的消息进行字符串编码
                                new BindClientHandler(ch, server),
                                new ServerHandler(server)
                        );
                    }
                });
        // sync()方法会让主线程阻塞,直到绑定动作完成。
        this.channelFuture = bootstrap.bind(port).sync();
        this.channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    UIUtil.drawConsole(serverUI.getConsolePane(), "服务启动失败");
                } else {
                    UIUtil.drawConsole(serverUI.getConsolePane(), "Server：启动Netty服务端成功，端口号:" + port);
                }
            }
        });
    }

    public void shutDown() {
        try {
            channelFuture.channel().close();
            serverUI.setServer(null);
            UIUtil.drawConsole(serverUI.getConsolePane(), "Server：服务已停止！");
        } finally {
            if (workerGroup != null) {
                workerGroup.shutdownGracefully();
            }
            if (bossGroup != null) {
                bossGroup.shutdownGracefully();
            }
        }
    }

    public ServerUI getServerUI() {
        return serverUI;
    }

}
