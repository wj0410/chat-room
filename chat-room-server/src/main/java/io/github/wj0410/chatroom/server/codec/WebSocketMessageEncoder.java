package io.github.wj0410.chatroom.server.codec;

import com.alibaba.fastjson2.JSONObject;
import io.github.wj0410.chatroom.common.entity.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

/**
 * WebSocket消息编码
 *
 * @author anlingyi
 * @date 2023/9/2 12:48 AM
 */
public class WebSocketMessageEncoder extends MessageToMessageEncoder<Response> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Response response, List<Object> list) throws Exception {
        TextWebSocketFrame frame = new TextWebSocketFrame(JSONObject.toJSONString(response));
        list.add(frame);
    }

}
