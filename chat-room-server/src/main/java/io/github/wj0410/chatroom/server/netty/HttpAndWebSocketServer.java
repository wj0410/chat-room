package io.github.wj0410.chatroom.server.netty;

import io.github.wj0410.chatroom.server.codec.WebSocketMessageEncoder;
import io.github.wj0410.chatroom.server.handler.HttpChannelHandler;
import io.github.wj0410.chatroom.server.handler.WebSocketChannelHandler;
import io.github.wj0410.chatroom.websocketserver.handler.ServerNormalHandler;
import io.github.wj0410.chatroom.websocketserver.handler.TextWebSocketFrameHandler;
import io.github.wj0410.chatroom.websocketserver.handler.WebSocketFrameHandler;
import io.github.wj0410.chatroom.websocketserver.holder.HttpAndWebSocketServerHolder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangjie
 */
@Slf4j
public class HttpAndWebSocketServer {
    private int port;
    private ChannelFuture channelFuture;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private static volatile HttpAndWebSocketServer instance;

    private HttpAndWebSocketServer(int port) {
        this.port = port + 1;
    }

    public static HttpAndWebSocketServer getInstance(int port) {
        if (instance == null) {
            synchronized (HttpAndWebSocketServer.class) {
                if (instance == null) {
                    instance = new HttpAndWebSocketServer(port);
                }
            }
        }
        return instance;
    }

    public void start() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap httpAndWebSocketServer = new ServerBootstrap();
            httpAndWebSocketServer.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            HttpAndWebSocketServerHolder.serverSocketChannel = ch;
                            ch.pipeline().addLast(
                                    /**
                                     * IdleStateHandler 是 Netty 提供的一个用于处理连接空闲状态的处理器，可以用来检测连接的空闲时间，并在特定条件下触发相应的事件。
                                     * 这里的配置表示当连接在 60 秒内没有进行读写操作时，会触发 IdleStateEvent 事件。
                                     * 开发者可以通过重写 ChannelInboundHandler 或 ChannelOutboundHandler 中的相应方法来处理这些事件
                                     * 例如 userEventTriggered 方法。
                                     */
                                    new IdleStateHandler(0, 0, 600 * 6 * 24),
                                    /**
                                     * HttpServerCodec，可以实现对 HTTP 请求和响应的编解码处理，从而使得 Netty 应用程序能够处理 HTTP 协议相关的通讯。
                                     */
                                    new HttpServerCodec(),
                                    /**
                                     * 指定了聚合的消息体的最大长度为 65536 字节。这意味着当接收到的 HTTP 消息体超过指定的长度时，将会抛出异常，以防止由于过大的消息体导致的内存消耗问题。
                                     */
                                    new HttpObjectAggregator(65536),
                                    /**
                                     * ChunkedWriteHandler 是用于支持写入大数据块的处理器，它可以将数据划分为较小的数据块进行写入，以避免大数据块导致的内存溢出或者其他性能问题。
                                     */
                                    new ChunkedWriteHandler(),
                                    /**
                                     * 服务端返回消息编码
                                     */
                                    new WebSocketMessageEncoder(),
                                    /**
                                     * 可以实现对传输的 WebSocket 消息进行压缩和解压缩操作，从而在一定程度上减少数据传输的大小，提高网络传输效率。
                                     */
                                    new WebSocketServerCompressionHandler(),
                                    /**
                                     * 用于在 Netty 应用程序中实现 WebSocket 服务器端的协议支持。
                                     * /chatRoom：这是指定的 WebSocket 路径，客户端需要使用 "/chatRoom" 路径来请求建立 WebSocket 连接。
                                     * null：这里是指定的用于扩展握手的自定义处理器，因为这里传入了 null，表示没有设置任何扩展处理器。
                                     * true：该参数表示是否支持扩展。在这里设置为 true，表示允许客户端和服务器端进行 WebSocket 协议的扩展。
                                     */
                                    new WebSocketServerProtocolHandler("/chatRoom", null, true),
                                    /**
                                     * 用于处理接收到的 FullHttpRequest 对象。当有新的 HTTP 请求到达时，Netty 会调用这个方法来处理请求。
                                     */
                                    new HttpChannelHandler(),
                                    new WebSocketChannelHandler()
                            );
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            channelFuture = httpAndWebSocketServer.bind(port).sync();
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        log.info("websocket server start success,port:{}", port);
                    } else {
                        log.error("websocket server start failed");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shutDown() {
        try {
            channelFuture.channel().close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
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