import {UserStatus} from "@/enum/UserStatus";
import MyWebSocket from "@/websocket/MyWebSocket";

class User {
    private readonly _id: number;
    private readonly _username: string;
    private _nickName: string;
    private _userStatus: UserStatus;
    private _avatar: string;
    private _socket: MyWebSocket;

    constructor(json: string) {
        const data = JSON.parse(json);
        this._id = data.id;
        this._username = data.username;
        this._nickName = data.nickName;
        this._userStatus = data.userStatus as UserStatus;
        this._avatar = data.avatar;
    }

    get id() {
        return this._id;
    }

    get username() {
        return this._username;
    }

    get nickName() {
        return this._nickName;
    }

    get userStatus() {
        return this._userStatus;
    }

    get avatar() {
        return this._avatar;
    }

    get socket() {
        return this._socket;
    }

    set userStatus(userStatus: UserStatus) {
        this._userStatus = userStatus;
    }

    set nickName(nickName: string) {
        this._nickName = nickName;
    }

    set avatar(avatar: string) {
        this._avatar = avatar;
    }

    set socket(socket: MyWebSocket) {
        this._socket = socket;
    }

    toJSON() {
        return {
            id: this._id,
            username: this._username,
            nickName: this._nickName,
            userStatus: this._userStatus,
            avatar: this._avatar
        }
    }
}

export default User