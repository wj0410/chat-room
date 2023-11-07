package io.github.wj0410.chatroom.common.util;

import com.alibaba.fastjson.JSON;
import io.github.wj0410.chatroom.common.enums.MessageType;
import io.github.wj0410.chatroom.common.message.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.util.Base64;

import static io.github.wj0410.chatroom.common.enums.MessageType.*;

/**
 * @author wangjie
 * @date 2023/10/26
 */
public class MessageUtil {

    public static String createBindMessageJsonStr(BindMessage bindMessage) {
        Message<BindMessage> message = new Message();
        message.setType(BIND);
        message.setData(bindMessage);
        return JSON.toJSONString(message);
    }

    public static String createNormalMessageJsonStr(NormalMessage normalMessage) {
        Message<NormalMessage> message = new Message();
        message.setType(NORMAL);
        message.setData(normalMessage);
        return JSON.toJSONString(message);
    }

    public static String createSyncOnlineMessageJsonStr(SyncOnlineMessage syncOnlineMessage) {
        Message<SyncOnlineMessage> message = new Message();
        message.setType(SYNC_ONLINE);
        message.setData(syncOnlineMessage);
        return JSON.toJSONString(message);
    }

    public static String createPromptMessageJsonStr(PromptMessage promptMessage) {
        Message<PromptMessage> message = new Message();
        message.setType(PROMPT);
        message.setData(promptMessage);
        return JSON.toJSONString(message);
    }

    public static String createRefuseMessageJsonStr(RefuseMessage refuseMessage) {
        Message<RefuseMessage> message = new Message();
        message.setType(REFUSE);
        message.setData(refuseMessage);
        return JSON.toJSONString(message);
    }

    public static Object getMessage(String jsonStr) {
        Message message = JSON.parseObject(jsonStr, Message.class);
        MessageType type = message.getType();
        String data = message.getData().toString();
        switch (type) {
            case BIND:
                BindMessage bindMessage = JSON.parseObject(data, BindMessage.class);
                return bindMessage;
            case NORMAL:
                NormalMessage normalMessage = JSON.parseObject(data, NormalMessage.class);
                return normalMessage;
            case SYNC_ONLINE:
                SyncOnlineMessage syncOnlineMessage = JSON.parseObject(data, SyncOnlineMessage.class);
                return syncOnlineMessage;
            case PROMPT:
                PromptMessage promptMessage = JSON.parseObject(data, PromptMessage.class);
                return promptMessage;
            case REFUSE:
                RefuseMessage refuseMessage = JSON.parseObject(data, RefuseMessage.class);
                return refuseMessage;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public static ByteBuf convert2ByteBuf(String jsonStr) {
        byte[] bytes = jsonStr.getBytes(Charset.forName("UTF-8"));
        ByteBuf buf = Unpooled.wrappedBuffer(bytes);
        return buf;
    }

    public static String convert2String(ByteBuf buf) {
        return buf.toString(Charset.forName("UTF-8"));
    }

    public static String convertByteArrayToString(byte[] byteArray) {
        String encodedString = Base64.getEncoder().encodeToString(byteArray);
        return encodedString;
    }

    public static byte[] convertStringToByteArray(String str) {
        byte[] byteArray = Base64.getDecoder().decode(str);
        return byteArray;
    }
}
