import User from "./User";
import { MessageType } from "../constant/Enums";
import {
    ChatMessageHandler, SyncOnlineListMessageHandler, HistoryMessageHandler, NoticeMessageHandler,
    StatusUpdateMessageHandler, SystemMessageHandler, UserStateMessageHandler
} from "./Message";

export class ResponseHandler {
    private response: Response<any>;
    constructor(response: Response<any>) {
        this.response = response;
    }
    public exec() {
        if (this.response.msgType == MessageType.HEARTBEAT) {
            return;
        }
        this.process();
    }

    private process() {
        // 实现策略模式
        switch (this.response.msgType) {
            // 系统消息
            case MessageType.SYSTEM:
                new SystemMessageHandler().handle(this.response);
                break;
            // 公告消息
            case MessageType.NOTICE:
                new NoticeMessageHandler().handle(this.response);
                break;
            // 聊天消息
            case MessageType.CHAT:
                new ChatMessageHandler().handle(this.response);
                break;
            // 同步在线列表
            case MessageType.SYNC_ONLINE_LIST:
                new SyncOnlineListMessageHandler().handle(this.response);
                break;
            // 设置用户在线状态    
            case MessageType.USER_STATE:
                new UserStateMessageHandler().handle(this.response);
                break;
            // 用户状态更新    
            case MessageType.STATUS_UPDATE:
                new StatusUpdateMessageHandler().handle(this.response);
                break;
            // 历史消息    
            case MessageType.HISTORY_MSG:
                new HistoryMessageHandler().handle(this.response);
                break;
        }
    }
}
export class Response<T> {
    private _user: User;
    private _body: T;
    private _msgType: MessageType;
    private _time: string;

    constructor(json: string) {
        const data = JSON.parse(json);
        this._user = data.user;
        this._msgType = data.type;
        this._body = data.body;
        this._time = data.time;
    }
    get msgType() {
        return this._msgType;
    }
    get body() {
        return this._body;
    }
    get user() {
        return this._user;
    }
    get time() {
        return this._time;
    }

    toJson() {
        return JSON.stringify({
            user: this._user,
            msgType: this._msgType,
            body: this._body,
            time: this._time
        })
    }
}
