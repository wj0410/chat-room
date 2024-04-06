import {NormalMessage} from "./NormalMessage";
import {SyncOnlineMessage} from "./SyncOnlineMessage";
import {PromptMessage} from "./PromptMessage";
import {MessageType} from "../constant/Enum";

export class Message {
    type: MessageType
    data: NormalMessage | SyncOnlineMessage | PromptMessage

    constructor(data) {
        this.type = data.type;
        this.data = data.data
    }

    getRealityMessage(): NormalMessage | SyncOnlineMessage | PromptMessage {
        if (this.type === MessageType.NORMAL) {
            // 普通消息
            return new NormalMessage(this.data)
        } else if (this.type === MessageType.SYNC_ONLINE) {
            // 同步在线列表信息
            return new SyncOnlineMessage(this.data)
        } else if (this.type === MessageType.PROMPT) {
            // 提示信息
            return new PromptMessage(this.data)
        }
    }
}
