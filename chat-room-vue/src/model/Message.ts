import { ChatType, MessageType } from "@/constant/Enums"
import User from '@/model/User'

export interface NoticeMessage {
    msgType: MessageType.NOTICE;
    msg: any;
    timestamp: bigint;
};
export interface ChatMessage {
    msgType: MessageType.CHAT;
    chatType: ChatType;
    fromUsername: string;
    toUsernames?: Array<string>;
    msg: any;
    timestamp: bigint;
};
export interface SyncOnlineMessage {
    msgType: MessageType.SYNC_ONLINE;
    msg: Array<User>;
};
export interface UserOnlineMessage {
    msgType: MessageType.USER_ONLINE;
    msg: User;
};
export interface UserOfflineMessage {
    msgType: MessageType.USER_OFFLINE;
    msg: User;
};