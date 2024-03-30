import type User from "@/model/User";
import { ChatType } from "@/constant/Enums";
export interface ChatMiddleProp {
  type: "chat";
  chatType: ChatType;
  avatar: string;
  unread: number;
  name: string;
  news?: string;// 最近消息摘要
  user?: User;
  chatViewProp?: ChatViewProp;
}
export interface GameCenterMiddleProp {
  type: "gameCenter";
  avatar: string;
  title: string;
}
// 聊天窗口prop属性
export interface ChatViewProp {
  chatType: ChatType;
  headProp: HeadProp;
  MessagePropList?: Array<MessageProp>;
}
// 头部prop属性
export interface HeadProp {
  title: string;
  userCount?: number;
  groupUserList?: Array<User>;
}
// 消息展示prop属性
export interface MessageProp {
  username?: string;
  nickName: string;
  avatar?: string;
  msg: any;
  timestamp: number;
  type: 'normal' | 'prompt' // 普通消息、提示消息
}

// export type { ChatMiddleProp, GameCenterMiddleProp, ChatViewProp, HeadProp };
