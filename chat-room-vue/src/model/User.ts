import { UserStatus } from "@/constant/Enums";
import MyWebSocket from "@/websocket/MyWebSocket";
import { RequestBuilder } from "./Request";

class User {
    private readonly _id: number;
    private readonly _username: string;
    private _nickName: string;
    private _userStatus: UserStatus;
    private _avatar: string;
    private _socket?: MyWebSocket;
    private _platform: string;
    constructor(json: string) {
        const data = JSON.parse(json);
        this._id = data.id;
        this._username = data.username;
        this._nickName = data.nickName;
        this._avatar = data.avatar;
        this._userStatus = UserStatus.ON_LINE;
        this._platform = 'WEBSOCKET'
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
    get platform() {
        return this._platform;
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
    set socket(socket: MyWebSocket | undefined) {
        this._socket = socket;
    }

    toJSON() {
        return {
            id: this._id,
            username: this._username,
            nickName: this._nickName,
            userStatus: this._userStatus,
            avatar: this._avatar,
            platform: this._platform
        }
    }

    public bind() {
        this.socket?.send(JSON.stringify(RequestBuilder.loginRequest(this)))
    }
}

export default User