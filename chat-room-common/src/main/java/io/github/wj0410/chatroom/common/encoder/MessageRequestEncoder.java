package io.github.wj0410.chatroom.common.encoder;

import io.github.wj0410.chatroom.common.message.NormalMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

/**
 * @author wangjie
 * @date 2023/10/25
 */
public class MessageRequestEncoder extends MessageToByteEncoder<NormalMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, NormalMessage msg, ByteBuf out) throws Exception {
        // 写入fromUserName长度
        out.writeInt(msg.getFromUserName().length());
        // 写入fromUserName
        out.writeBytes(msg.getFromUserName().getBytes(StandardCharsets.UTF_8));

        // 写入fromClientId长度
        out.writeInt(msg.getFromClientId().length());
        // 写入fromClientId
        out.writeBytes(msg.getFromClientId().getBytes(StandardCharsets.UTF_8));

        // 写入targetClientId列表大小
        out.writeInt(msg.getTargetClientId().size());
        // 遍历写入每个targetClientId
        for (String clientId : msg.getTargetClientId()) {
            out.writeInt(clientId.length());
            out.writeBytes(clientId.getBytes(StandardCharsets.UTF_8));
        }

        // 写入时间戳
        out.writeLong(msg.getTimestamp());

        // 写入消息长度
        out.writeInt(msg.getMsg().length());
        // 写入消息
        out.writeBytes(msg.getMsg().getBytes(StandardCharsets.UTF_8));
    }

}