package io.github.wj0410.chatroom.server.action.handler;


import io.github.wj0410.chatroom.common.entity.User;
import io.github.wj0410.chatroom.common.enums.Action;
import io.github.wj0410.chatroom.common.enums.UserStatus;
import io.github.wj0410.chatroom.server.action.ChannelAction;
import io.github.wj0410.chatroom.server.action.handler.base.AbstractActionHandler;
import io.github.wj0410.chatroom.server.annotation.DoAction;

/**
 * @author anlingyi
 * @date 2020/8/14
 */
@DoAction(Action.SET_STATUS)
public class SetStatusActionHandler extends AbstractActionHandler<UserStatus> {

    @Override
    protected void process(User user, UserStatus body) {
        user.setStatus(body);
        ChannelAction.updateUserStatus(user);
    }

}
