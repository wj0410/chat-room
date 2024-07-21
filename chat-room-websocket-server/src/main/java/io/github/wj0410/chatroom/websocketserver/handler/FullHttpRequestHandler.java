package io.github.wj0410.chatroom.websocketserver.handler;

import io.github.wj0410.chatroom.common.constant.CommonConstants;
import io.github.wj0410.chatroom.common.enums.old.ClientOrigin;
import io.github.wj0410.chatroom.common.message.BindMessage;
import io.github.wj0410.chatroom.common.util.ServerUtil;
import io.github.wj0410.chatroom.websocketserver.holder.HttpAndWebSocketServerHolder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;
import static io.netty.handler.codec.http.HttpUtil.setContentLength;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 客户端上线
 * @author wangjie
 * @date 2023/11/8
 */
public class FullHttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) {
        // 如果HTTP解码失败，返回HHTP异常
        if (!req.decoderResult().isSuccess()
                || (!"websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1,
                    BAD_REQUEST));
            return;
        }
        // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws://localhost:" + HttpAndWebSocketServerHolder.serverProperties.getServer().getPort(), null, false);
        HttpAndWebSocketServerHolder.handshaker = wsFactory.newHandshaker(req);
        if (HttpAndWebSocketServerHolder.handshaker == null) {
            WebSocketServerHandshakerFactory
                    .sendUnsupportedVersionResponse(ctx.channel());
        } else {
            HttpAndWebSocketServerHolder.handshaker.handshake(ctx.channel(), req);
        }
        // 客户端上线，绑定clientId
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(req.uri());
        Map<String, List<String>> params = queryStringDecoder.parameters();
        String clientId = params.get(CommonConstants.BIND_CLIENT_ID).get(0);
        if (ServerUtil.hasClient(clientId)) {
            // 客户端已登陆，返回错误
            return;
        }
        String account = params.get(CommonConstants.BIND_ACCOUNT).get(0);
        String nickName = params.get(CommonConstants.BIND_NICK_NAME).get(0);
        BindMessage bindMessage = new BindMessage();
        bindMessage.setClientId(clientId);
        bindMessage.setAccount(account);
        bindMessage.setNickName(nickName);
        if (params.get(CommonConstants.BIND_AVATAR) != null) {
            bindMessage.setAvatar(params.get(CommonConstants.BIND_AVATAR).get(0));
        }
        HttpAndWebSocketServerHolder.setClientIdAttr(clientId);
        ServerUtil.addClient(ctx, bindMessage, ClientOrigin.WEBSOCKET);
        ctx.pipeline().remove(this);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx,
                                         FullHttpRequest req, FullHttpResponse res) {
        // 返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(),
                    CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            setContentLength(res, res.content().readableBytes());
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

}
