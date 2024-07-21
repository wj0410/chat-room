import { Action } from "@/constant/Enums";
import User from "./User";

class Request {
    private _body: any;
    private _action: Action;
    private _protocol: string;

    constructor(action: Action, body: any) {
        this._action = action;
        this._body = body;
        this._protocol = 'WEBSOCKET';
    }
    get body() {
        return this._body;
    }
    get action() {
        return this._action;
    }
    get protocol() {
        return this._protocol;
    }
    
    toJSON() {
        return {
            body: this._body,
            action: this._action,
            protocol: this._protocol
        };
    }
}
export class RequestBuilder {
    public static loginRequest(user:User): Request {
        return new Request(Action.LOGIN, user);
    }
}