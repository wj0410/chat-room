package io.github.wj0410.chatroom.common.decoder;

import io.github.wj0410.chatroom.common.message.BindRequest;
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
        int userNameLength = in.readInt();
        // 读取用户名
        byte[] userNameBytes = new byte[userNameLength];
        in.readBytes(userNameBytes);
        String userName = new String(userNameBytes, StandardCharsets.UTF_8);

        // 封装BindRequest对象
        BindRequest request = new BindRequest();
        request.setClientId(clientId);
        request.setAccount(userName);

        out.add(request);
    }
}
