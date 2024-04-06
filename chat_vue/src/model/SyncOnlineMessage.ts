import {Message} from "./Message";
import {MessageType} from "../constant/Enum";
import ClientModel from "./ClientModel.ts";

export class SyncOnlineMessage {

    public clientOnlineList: Array<ClientModel>


    constructor(data) {
        this.clientOnlineList = data.clientOnlineList;
    }

    buildMessage() {
        let obj = {
            type: MessageType.SYNC_ONLINE,
            data: this
        }
        return new Message(obj);
    }
}
