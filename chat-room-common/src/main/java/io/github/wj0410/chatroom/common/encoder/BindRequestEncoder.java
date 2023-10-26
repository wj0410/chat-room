package io.github.wj0410.chatroom.common.encoder;

import io.github.wj0410.chatroom.common.message.BindMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

/**
 * @author wangjie
 * @date 2023/10/25
 */
public class BindRequestEncoder extends MessageToByteEncoder<BindMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, BindMessage msg, ByteBuf out) throws Exception {
        String clientId = msg.getClientId();
        String account = msg.getAccount();
        // 写入clientId字符串长度
        out.writeInt(clientId.getBytes(StandardCharsets.UTF_8).length);
        // 写入clientId字符串
        out.writeBytes(clientId.getBytes(StandardCharsets.UTF_8));

        // 写入用户名字符串长度
        out.writeInt(account.getBytes(StandardCharsets.UTF_8).length);
        // 写入用户名字符串
        out.writeBytes(account.getBytes(StandardCharsets.UTF_8));
    }

}
