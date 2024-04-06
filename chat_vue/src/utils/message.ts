import {Message} from "../model/Message";
import {ChatType, ClientType, MessageType} from "../constant/Enum";
import {NormalMessage} from "../model/NormalMessage";
import {CommonConstants} from "../constant/constants";
import {$store} from "./MyCommon";
import ChatModel, {createChatModel} from "../model/ChatModel";

let userInfo: UserInfo = $store.userInfo;

// 聊天室
export const publicChatRoomChatModel = createChatModel(
    {
        account: CommonConstants.PUBLIC_CHAT_ROOM_ACCOUNT,
        clientId: CommonConstants.PUBLIC_CHAT_ROOM_ACCOUNT,
        nickName: CommonConstants.PUBLIC_CHAT_ROOM_NAME,
        clientType: ClientType.PUBLIC,
        avatar: 'https://img2.baidu.com/it/u=2042079370,1542907602&fm=253&fmt=auto&app=138&f=JPEG?w=614&h=500'
    },
    [],
    [],
    true)

// 根据收到的消息，判断该消息应该在哪个ChatModel里渲染，返回对应的ChatClient的account
export function getChatClientIdByMessage(message: Message): String {
    switch (message.type) {
        case MessageType.NORMAL:
            let normalMessage: NormalMessage = message.getRealityMessage();
            if (normalMessage.chatType === ChatType.PUBLIC) {
                return publicChatRoomChatModel.chatClient.clientId;
            } else if (normalMessage.chatType === ChatType.PRIVATE) {
                // 私聊消息
                if (normalMessage.fromClientId === userInfo.clientId) {
                    // 发送者是自己 在websocket里 clientId = account
                    return normalMessage.targetClientIds[0];
                } else if (normalMessage.targetClientIds[0] === userInfo.clientId) {
                    // 接收者是自己
                    return normalMessage.fromClientId;
                }
            } else if (normalMessage.chatType === ChatType.GROUP) {
                // 暂未开发
                return null;
            }
        case MessageType.SYNC_ONLINE:
            // 同步在线列表消息，不需要处理
            return null;
        case MessageType.PROMPT:
            // 提示消息默认都是聊天室内的消息
            return publicChatRoomChatModel.chatClient.clientId;
    }
}

