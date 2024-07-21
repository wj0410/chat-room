package io.github.wj0410.chatroom.server.action.handler;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.github.wj0410.chatroom.common.entity.User;
import io.github.wj0410.chatroom.common.entity.UserMsgDTO;
import io.github.wj0410.chatroom.common.enums.Action;
import io.github.wj0410.chatroom.common.enums.MessageType;
import io.github.wj0410.chatroom.server.action.ChannelAction;
import io.github.wj0410.chatroom.server.action.handler.base.AbstractActionHandler;
import io.github.wj0410.chatroom.server.annotation.DoAction;
import io.github.wj0410.chatroom.server.builder.ResponseBuilder;
import io.github.wj0410.chatroom.server.cache.UserCache;

/**
 * @author anlingyi
 * @date 2020/8/14
 */
@DoAction(Action.CHAT)
public class ChatActionHandler extends AbstractActionHandler<UserMsgDTO> {

    @Override
    protected void process(User user, UserMsgDTO body) {
        if (body.getMsgType() == UserMsgDTO.MsgType.TEXT) {
            String msg = Convert.toStr(body.getContent());
            if (StrUtil.length(msg) > 200) {
                user.send(ResponseBuilder.system("发送的内容长度不能超过200字符！"));
                return;
            }
        } else {
            // 暂时不支持这种形式的消息，全部转为文本消息
            body.setMsgType(UserMsgDTO.MsgType.TEXT);
        }
        if (ObjectUtil.isEmpty(body.getToUsers())) {
            ChannelAction.send(user, body, MessageType.CHAT);
        } else {
            for (String toUsername : body.getToUsers()) {
                User u = UserCache.getUserByUsername(toUsername);
                if (u == null) {
                    // 用户下线了
                    // 暂时不处理
                    return;
                }
                u.send(ResponseBuilder.build(user, body, MessageType.CHAT));
            }
        }
    }

}
