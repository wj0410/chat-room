import {MessageType, PromptType} from "../constant/Enum";
import {Message} from "./Message";

export class PromptMessage {
    promptType: PromptType
    clientId: string
    msg: string
    timestamp: bigint

    constructor(data) {
        this.promptType = data.promptType;
        this.clientId = data.clientId;
        this.msg = data.msg;
        this.timestamp = data.timestamp;
    }

    buildMessage() {
        let obj = {
            type: MessageType.PROMPT,
            data: this
        }
        return new Message(obj);
    }
}
