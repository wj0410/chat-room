import useUserStore from '@/store/user';
import User from '@/model/User';
import { ChatMiddleProp, ChatMiddlePropBuilder } from "@/prop/ChatMiddleProp";
import ChatViewProp from "@/prop/ChatViewProp";
import MessageProp from './prop/MessageProp';
import { useMsgCache } from "@/cache/userCache";
const { addMsg } = useMsgCache()
const userStore = useUserStore();

export const admin = new User(
    '{"id":1,"username":"admin","nickName":"管理员","avatar":"https://avatars.githubusercontent.com/u/43922975?v=4"}'
);
export const user = new User(
    '{"id":2,"username":"user","nickName":"用户","avatar":"https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"}'
);

// chatMiddle
export const chatUserMiddle: ChatMiddleProp = ChatMiddlePropBuilder.buildByUser(user)
chatUserMiddle.unread = 5
chatUserMiddle.news = '最近消息摘要'

export const msg1 = new MessageProp(admin.username, admin.nickName, admin.avatar, 'hello', '2024/4/3 13:06', 'normal')
export const msg2 = new MessageProp(user.username, user.nickName, user.avatar, 'hi', '2024/4/3 13:06', 'normal')