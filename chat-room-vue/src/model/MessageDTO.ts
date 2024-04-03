import type { Response } from "./Response";
import User from "./User";
import { ChatType, UserStatus } from "@/constant/Enums";

export class UserMsgDTO {
    private _content: any;
    private _contentType: ContentType;
    private _toUsers?: string[];
    private _chatType: ChatType;

    constructor(chatType: ChatType, content: any, toUsers?: string[], contentType?: ContentType) {
        this._chatType = chatType;
        this._content = content;
        this._contentType = contentType ?? ContentType.TEXT;
        this._toUsers = toUsers ?? undefined;
    }

    get content() {
        return this._content;
    }

    get contentType() {
        return this._contentType;
    }

    get toUsers() {
        return this._toUsers;
    }

    get chatType() {
        return this._chatType;
    }
}

export enum ContentType {
    TEXT = 'TEXT',
    IMAGE = 'IMAGE'
}

export class OnlineListMsgDTO {
    private _userList: User[];

    constructor(userList: User[]) {
        this._userList = userList;
    }

    get userList() {
        return this._userList;
    }
}

export class UserStateMsgDTO {
    private _user: User;
    private _userStatus: UserStatus;

    constructor(user: User, userStatus: UserStatus) {
        this._user = user;
        this._userStatus = userStatus;
    }

    get user() {
        return this._user;
    }

    get userStatus() {
        return this._userStatus;
    }
}

export class HistoryMsgDTO {
    private _msgList: Array<Response<UserMsgDTO>>;

    constructor(msgList: Array<Response<UserMsgDTO>>) {
        this._msgList = msgList;
    }

    get msgList() {
        return this._msgList;
    }
}