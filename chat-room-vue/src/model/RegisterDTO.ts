class UserDTO {
    private _username: string;
    private _password: string;
    private _nickName: string;

    constructor(username: string, password: string, nickName: string) {
        this._username = username;
        this._password = password;
        this._nickName = nickName;
    }

    get username() {
        return this._username;
    }

    get password() {
        return this._password;
    }

    get nickName() {
        return this._nickName;
    }

    set username(username: string) {
        this._username = username;
    }

    set password(password: string) {
        this._password = password;
    }

    set nickName(nickName: string) {
        this._nickName = nickName;
    }
}

export default UserDTO