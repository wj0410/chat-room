package io.github.wj0410.chatroom.server.action.handler.base;

import io.github.wj0410.chatroom.common.entity.User;
import io.github.wj0410.chatroom.server.action.ChannelAction;
import io.github.wj0410.chatroom.server.builder.ResponseBuilder;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author anlingyi
 * @date 2020/8/14
 */
public abstract class AbstractActionHandler<T> implements ActionHandler<T> {

    @Override
    public final void handle(ChannelHandlerContext ctx, T body) {
        User user = ChannelAction.getUser(ctx);
        if (user == null) {
            ctx.writeAndFlush(ResponseBuilder.system("未获取到用户信息，请先登录！"));
            ctx.close();
            return;
        }

        process(user, body);
    }

    protected abstract void process(User user, T body);

}
