import type { IMiddleProp } from "./interfaces/IProps";
import MessageProp from "./MessageProp";
import { ChatType } from "@/constant/Enums";
import User from "@/model/User";
import useUserStore from "@/store/user";
const userStore = useUserStore();
export class ChatMiddleProp implements IMiddleProp {
    public id: string;
    public type: string;
    public chatType: ChatType;
    public avatar: string;
    public unread: number;
    public title: string;
    public news: string;
    public groupUserList: Array<User>;
    constructor(id: string, chatType: ChatType, avatar: string, unread: number, title: string, news: string, groupUserList: Array<User>) {
        this.id = id;
        this.type = 'chat';
        this.chatType = chatType;
        this.avatar = avatar;
        this.unread = unread;
        this.title = title;
        this.news = news;
        this.groupUserList = groupUserList;
    }
}
export class ChatMiddlePropBuilder {

    public static buildOnlineChatRoom(): ChatMiddleProp {
        const id = "onlineChatRoom"
        const avatar = "https://img2.baidu.com/it/u=4085937757,1625118201&fm=253&fmt=auto&app=138&f=JPEG?w=749&h=500"
        const title = "在线聊天室"
        return new ChatMiddleProp(id, ChatType.PUBLIC, avatar, 0, title, '', userStore.onlineUserList)
    }

    public static buildByUser(user: User): ChatMiddleProp {
        return new ChatMiddleProp(user.username, ChatType.PRIVATE, user.avatar, 0, user.nickName, '', [user]);
    }
}
export default ChatMiddleProp;