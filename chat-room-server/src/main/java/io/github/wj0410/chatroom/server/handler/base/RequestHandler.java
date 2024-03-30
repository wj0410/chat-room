package io.github.wj0410.chatroom.server.handler.base;

import cn.hutool.core.thread.GlobalThreadPool;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSONObject;
import io.github.wj0410.chatroom.common.entity.Request;
import io.github.wj0410.chatroom.common.enums.Action;
import io.github.wj0410.chatroom.common.enums.Protocol;
import io.github.wj0410.chatroom.common.enums.UserStatus;
import io.github.wj0410.chatroom.server.action.handler.base.ActionHandler;
import io.github.wj0410.chatroom.server.builder.ResponseBuilder;
import io.github.wj0410.chatroom.server.factory.ActionHandlerFactory;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author anlingyi
 * @date 2020/8/14
 */
public class RequestHandler {

    private final ChannelHandlerContext ctx;

    private final Request request;

    public RequestHandler(final ChannelHandlerContext ctx, final Request request) {
        this.ctx = ctx;
        this.request = request;
    }

    public void exec() {
        if (request.getAction() == null || request.getAction() == Action.HEARTBEAT) {
            return;
        }

        if (ObjectUtil.isEmpty(request.getBody())) {
            ctx.writeAndFlush(ResponseBuilder.system("Body is null!"));
            return;
        }
        GlobalThreadPool.execute(() -> {
            ActionHandler produce = ActionHandlerFactory.INSTANCE.produce(request.getAction());
            Object body = request.getBody();

            // 非默认协议需要转换body的数据类型
            if (request.getProtocol() != Protocol.DEFAULT) {
                try {
                    if (request.getAction() == Action.SET_STATUS) {
                        body = UserStatus.valueOf(body.toString());
                    } else {
                        body = JSONObject.parseObject(body.toString(), ClassUtil.getTypeArgument(produce.getClass()));
                    }
                } catch (Exception e) {
                    ctx.writeAndFlush(ResponseBuilder.system("消息内容解析异常!"));
                    return;
                }
            }

            produce.handle(ctx, body);
        });
    }

}
