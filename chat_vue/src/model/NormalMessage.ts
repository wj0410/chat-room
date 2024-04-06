import {Message} from "./Message";
import {MessageType,ChatType} from "../constant/Enum";
import {MessageContainer} from "./MessageContainer";

export class NormalMessage {
    chatType: ChatType
    fromAccount: string
    fromNickName: string
    fromClientId: string
    fromClientAvatar: string
    targetClientIds: Array<string>
    timestamp: bigint
    msgList: Array<MessageContainer>

    constructor(data) {
        this.chatType = data.chatType;
        this.fromAccount = data.fromAccount;
        this.fromNickName = data.fromNickName;
        this.fromClientId = data.fromClientId;
        this.fromClientAvatar = data.fromClientAvatar;
        this.targetClientIds = data.targetClientIds;
        this.timestamp = data.timestamp;
        this.msgList = data.msgList;
    }

    buildMessage() {
        let obj = {
            type: MessageType.NORMAL,
            data: this
        }
        return new Message(obj);
    }
}
