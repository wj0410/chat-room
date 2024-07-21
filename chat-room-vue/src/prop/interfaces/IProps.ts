import type User from "@/model/User";
import { ChatType } from "@/constant/Enums";
export interface IMiddleProp {
  id: string;
  type:string;
  avatar: string;
  title: string;
  chatType?: ChatType;
  unread?: number;
  news?: string;
  groupUserList?: Array<User>;
}
// 聊天窗口prop属性
export interface IChatViewProp {
  chatType: ChatType;
  // headProp: IHeadProp;
  MessagePropList?: Array<IMessageProp>;
}
// 消息展示prop属性
export interface IMessageProp {
  username: string;
  nickName: string;
  avatar: string;
  msg: any;
  time: string;
  type: string;
}