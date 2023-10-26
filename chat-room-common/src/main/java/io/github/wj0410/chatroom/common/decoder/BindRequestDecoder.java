package io.github.wj0410.chatroom.common.decoder;

import io.github.wj0410.chatroom.common.message.BindMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author wangjie
 * @date 2023/10/25
 */
public class BindRequestDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws IOException, ClassNotFoundException {
        // 读取clientId长度
        int clientIdLength = in.readInt();
        // 读取clientId
        byte[] clientIdBytes = new byte[clientIdLength];
        in.readBytes(clientIdBytes);
        String clientId = new String(clientIdBytes, StandardCharsets.UTF_8);

        // 读取用户名长度
        int accountLength = in.readInt();
        // 读取用户名
        byte[] accountBytes = new byte[accountLength];
        in.readBytes(accountBytes);
        String account = new String(accountBytes, StandardCharsets.UTF_8);

        // 封装BindRequest对象
        BindMessage request = new BindMessage();
        request.setClientId(clientId);
        request.setAccount(account);

        out.add(request);
    }
}
