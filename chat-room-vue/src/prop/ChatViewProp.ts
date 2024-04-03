import type { IChatViewProp } from "./interfaces/IProps";
import MessageProp from "./MessageProp";
import { ChatType } from "@/constant/Enums";
class ChatViewProp implements IChatViewProp {
    public chatType: ChatType;
    public messagePropList?: Array<MessageProp>;

    constructor(chatType: ChatType, messagePropList?: Array<MessageProp>) {
        this.chatType = chatType;
        this.messagePropList = messagePropList;
    }
}
export default ChatViewProp;