//package io.github.wj0410.chatroom.common.decoder;
//
//import io.github.wj0410.chatroom.common.message.NormalMessage;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.ByteToMessageDecoder;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author wangjie
// * @date 2023/10/25
// */
//public class MessageRequestDecoder extends ByteToMessageDecoder {
//
//    @Override
//    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws IOException, ClassNotFoundException {
//        // 读取fromUserName
//        String fromUserName = decodeString(in);
//
//        // 读取fromClientId
//        String fromClientId = decodeString(in);
//
//        // 读取targetClientId列表
//        List<String> targetClientIds = decodeStringList(in);
//
//        // 读取时间戳
//        long timestamp = in.readLong();
//
//        // 读取消息内容
//        String message = decodeString(in);
//
//        // 封装MessageRequest
//        NormalMessage request = new NormalMessage();
//        request.setFromUserName(fromUserName);
//        request.setFromClientId(fromClientId);
//        request.setTargetClientIds(targetClientIds);
//        request.setTimestamp(timestamp);
//        request.setMsg(message);
//
//        out.add(request);
//    }
//
//    private String decodeString(ByteBuf in) {
//        int length = in.readInt();
//        byte[] bytes = new byte[length];
//        in.readBytes(bytes);
//        return new String(bytes, StandardCharsets.UTF_8);
//    }
//
//    private List<String> decodeStringList(ByteBuf in) {
//        List<String> list = new ArrayList<>();
//        int size = in.readInt();
//        for (int i = 0; i < size; i++) {
//            list.add(decodeString(in));
//        }
//        return list;
//    }
//}