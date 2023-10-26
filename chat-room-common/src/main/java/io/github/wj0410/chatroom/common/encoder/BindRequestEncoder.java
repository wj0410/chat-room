package io.github.wj0410.chatroom.common.encoder;

import io.github.wj0410.chatroom.common.message.BindRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

/**
 * @author wangjie
 * @date 2023/10/25
 */
public class BindRequestEncoder extends MessageToByteEncoder<BindRequest> {

    @Override
    protected void encode(ChannelHandlerContext ctx, BindRequest msg, ByteBuf out) throws Exception {
        // 写入clientId字符串长度
        out.writeInt(msg.getClientId().length());
        // 写入clientId字符串
        out.writeBytes(msg.getClientId().getBytes(StandardCharsets.UTF_8));

        // 写入用户名字符串长度
        out.writeInt((msg.getAccount().getBytes(StandardCharsets.UTF_8)).length);
        // 写入用户名字符串
        out.writeBytes(msg.getAccount().getBytes(StandardCharsets.UTF_8));
    }

}
