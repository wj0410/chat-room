import {ClientOrigin, ClientType} from "../constant/Enum.ts";
import ClientModel from "./ClientModel";
import {Message} from "./Message";
import {CommonConstants} from "../constant/constants";

interface ChatModel {
    chatClient: ClientModel,// 客户端
    chatMessageList: Array<Message>,// 聊天记录
    memberList: Array<ClientModel>,// 成员列表
    unreadCount: number,// 消息未读数量
    notice: boolean,// 允许消息通知
    show: boolean,// 显示
    online: boolean,// 在线状态
}

export function createChatModel(chatClient: ClientModel,
                                chatMessageList: Array<Message>,
                                memberList: Array<ClientModel>,
                                online?: boolean,
                                unreadCount?: number,
                                notice?: boolean, show?: boolean) {
    return {
        chatClient: chatClient,
        chatMessageList: chatMessageList,
        memberList: memberList,
        online: online == undefined ? false : online,
        unreadCount: unreadCount == undefined ? 0 : unreadCount,
        notice: notice == undefined ? true : notice,
        show: show == undefined ? true : show,
    }
}

export default ChatModel
