package io.github.wj0410.chatroom.common.enums;

/**
 * @author wangjie
 */

public enum MessageType {
    /**
     * 系统消息
     */
    SYSTEM,
    /**
     * 公告消息
     */
    NOTICE ,
    /**
     * 聊天消息
     */
    CHAT ,
    /**
     * 同步在线用户
     */
    SYNC_ONLINE_LIST,
    /**
     * 用户上线、离线消息
     */
    USER_STATE,
    /**
     * 用户状态更新消息
     */
    STATUS_UPDATE,
    /**
     * 心跳消息
     */
    HEARTBEAT,
    /**
     * 历史聊天记录消息
     */
    HISTORY_MSG,
}
