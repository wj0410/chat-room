export namespace CommonConstants {
    export const PUBLIC_CHAT_ROOM_ACCOUNT: string = '0';
    export const PUBLIC_CHAT_ROOM_NAME: string = '聊天室';
}

export namespace HeartBeatConstants {
    export const HEART_BEAT: string = 'heart_beat';
    export const HEART_BEAT_SUCCESS: string = 'heart_beat:success';
    export const HEARTBEAT_INTERVAL: number = 5000; // 心跳包发送间隔时间，单位：毫秒
    export const SERVER_TIMEOUT: number = 10000; // 服务器超时时间，即多长时间没有收到服务器响应认为网络异常，单位：毫秒
}

export namespace MittConstants {
    // 同步在线列表
    export const SYNC_ONLINE_MESSAGE: string = 'mitt:syncOnlineMessage';
    // 进入聊天室消息提醒
    export const WELCOME_MESSAGE: string = 'mitt:welcomeMessage';
    // 离开聊天室消息提醒
    export const LEAVE_MESSAGE: string = 'mitt:leaveMessage';
    // 接收到消息
    export const NORMAL_MESSAGE: string = 'mitt:normalMessage';
    // 显示离线提示
    export const SHOW_OFFLINE: string = 'mitt:showOffline';
    // 显示新消息提示
    export const SHOW_NEW_MESSAGE: string = 'mitt:showNewMessage';
    // 显示更多面板
    export const SHOW_MORE_CONTAINER: string = 'mitt:showMoreContainer';
    // 隐藏表情面板
    export const HIDE_EMOJI: string = 'mitt:hideEmoji';
    // 隐藏ChatList右键菜单
    export const HIDE_CHAT_LIST_MENU: string ='mitt:hideChatListMenu';
    // 滚动到底部
    export const SCROLL_TO_BOTTOM: string = 'mitt:scrollToBottom';
    // 追加发送文本
    export const APPEND_SEND_TEXT: string = 'mitt:appendSendText';
    // 新增ChatModel
    export const ADD_CHAT_MODEL: string = 'mitt:add:chatModel';

}