import MyWebSocket from "@/websocket/MyWebSocket";
import User from "@/model/User";
class Client {
    private _socket: MyWebSocket | undefined;
    private _user:User;

    constructor(socket: MyWebSocket,user:User) {
        this._socket = socket;
        this._user = user;
    }
    get socket() {
        return this._socket;
    }
    get user() {
        return this._user;
    }
}
export default Client;