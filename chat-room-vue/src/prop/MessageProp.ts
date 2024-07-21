import type { IMessageProp } from "./interfaces/IProps";
class MessageProp implements IMessageProp {
    public username: string;
    public nickName: string;
    public avatar: string;
    public msg: any;
    public time: string;
    public type: 'normal' | 'prompt' // 普通消息、提示消息
    
    constructor(username: string, nickName: string, avatar: string, msg: any, time: string, type: 'normal' | 'prompt') {
        this.username = username;
        this.nickName = nickName;
        this.avatar = avatar;
        this.msg = msg;
        this.time = time;
        this.type = type;
    }
}
export default MessageProp;