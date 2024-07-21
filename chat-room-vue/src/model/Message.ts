import { UserMsgDTO, OnlineListMsgDTO, UserStateMsgDTO, HistoryMsgDTO } from '@/model/MessageDTO'
import { Response } from "./Response";
import useUserStore from '@/store/user';
import User from '@/model/User';

export interface MessageHandler<T> {
    handle(response: Response<T>): void;
}
/**
 * 系统消息处理
 */
export class SystemMessageHandler implements MessageHandler<string> {
    handle(response: Response<string>) {
        const content = response.body
        console.log("系统消息：", content)
    }
};
/**
 * 公告消息处理
 */
export class NoticeMessageHandler implements MessageHandler<string> {
    handle(response: Response<string>) {
        const content = response.body
        console.log("公告消息：", content)
    }
};
/**
 * 用户聊天消息处理
 */
export class ChatMessageHandler implements MessageHandler<UserMsgDTO> {
    handle(response: Response<UserMsgDTO>) {
        const content = response.body.content
        const contentType = response.body.contentType
        console.log("用户消息：", content)
    }
};
/**
 * 同步在线用户列表消息处理
 */
export class SyncOnlineListMessageHandler implements MessageHandler<OnlineListMsgDTO> {
    handle(response: Response<OnlineListMsgDTO>) {
        const userList: User[] = response.body.userList
        console.log("同步在线用户列表消息：", userList)
        useUserStore().setOnlineUser(userList)
    }
};
/**
 * 更新用户在线状态消息处理
 */
export class UserStateMessageHandler implements MessageHandler<UserStateMsgDTO> {
    handle(response: Response<UserStateMsgDTO>) {
        const user = response.body.user
        const userStatus = response.body.userStatus
        console.log('用户在线状态消息', user, userStatus)
    }
};
/**
 * 更新用户状态消息处理
 */
export class StatusUpdateMessageHandler implements MessageHandler<Object> {
    handle(response: Response<Object>) {
        const user = response.user
        console.log('更新用户状态消息', user)
    }
};
/**
 * 历史消息处理
 */
export class HistoryMessageHandler implements MessageHandler<HistoryMsgDTO> {
    handle(response: Response<HistoryMsgDTO>) {
        const userStore = useUserStore()
        const msgList = response.body.msgList
        console.log('历史记录消息', msgList)
        userStore.setOnlineChatRoomHisMsgList(msgList)
    }
};