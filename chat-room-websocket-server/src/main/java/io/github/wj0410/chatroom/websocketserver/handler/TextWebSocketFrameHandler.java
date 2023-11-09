package io.github.wj0410.chatroom.websocketserver.handler;

import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;


/**
 * 文本格式的数据
 *
 * @author wangjie
 * @date 2023/11/8
 */
@Slf4j
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) {
        // 增加对象的引用计数
        frame.retain();
        String content = frame.text();
        Object message = MessageUtil.getMessage(content);
        // 将msg交给下一个handler处理
        ctx.fireChannelRead(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常处理逻辑
        System.err.println("Exception caught: " + cause);
    }
}
