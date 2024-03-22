import useUserStore from '@/store/user';
import User from '@/model/User';
import type { ChatMiddleProp, ChatViewProp, GameCenterMiddleProp } from './constant/Props';
import { ChatType } from './constant/Enums';

const userStore = useUserStore();
export const admin = new User(
    '{"id":1,"username":"admin","nickName":"管理员","avatar":"https://avatars.githubusercontent.com/u/43922975?v=4"}'
);
export const user = new User(
    '{"id":2,"username":"user","nickName":"用户","avatar":"https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"}'
);
userStore.loginUser = admin;
userStore.addOnlineUser(admin);
userStore.addOnlineUser(user);

// chatMiddle
export const chatUserObj: ChatMiddleProp = {
    chatType: ChatType.PRIVATE,
    type: "chat",
    avatar: user.avatar,
    unread: 5,
    name: user.nickName,
    news: "最近消息摘要",
    user: user
};
export const chatPublicObj: ChatMiddleProp = {
    chatType: ChatType.GROUP,
    type: "chat",
    avatar: admin.avatar,
    unread: 1,
    name: "在线聊天室",
    news: "最近消息摘要",
};

const chatUserViewProp: ChatViewProp = {
    chatType: chatUserObj.chatType,
    headProp: {
        title: chatUserObj.name,
        groupUserList: [chatUserObj.user] as User[]
    },
    MessagePropList: [
        {
            username: admin.username,
            nickName: admin.nickName,
            avatar: admin.avatar,
            msg: 'hello',
            timestamp: 111111,
            type: 'normal'
        },
        {
            username: user.username,
            nickName: user.nickName,
            avatar: user.avatar,
            msg: 'hi',
            timestamp: 22222,
            type: 'normal'
        }
    ]
};
const chatPublicViewProp: ChatViewProp = {
    chatType: chatPublicObj.chatType,
    headProp: {
        title: chatPublicObj.name,
        groupUserList: userStore.onlineUserList,
        userCount: userStore.onlineUserTotal,
    },
    MessagePropList: [
        {
            nickName: admin.nickName,
            msg: '进来了',
            timestamp: 111111,
            type: 'prompt'
        },
        {
            username: admin.username,
            nickName: admin.nickName,
            avatar: admin.avatar,
            msg: '你好',
            timestamp: 111111,
            type: 'normal'
        },
        {
            username: user.username,
            nickName: user.nickName,
            avatar: user.avatar,
            msg: '你好呀',
            timestamp: 22222,
            type: 'normal'
        }
    ]
};
chatUserObj.chatViewProp = chatUserViewProp;
chatPublicObj.chatViewProp = chatPublicViewProp;


