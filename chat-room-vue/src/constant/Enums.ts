export enum UserStatus {
  ON_LINE = "ON_LINE",// 在线
  OFF_LINE = "OFF_LINE",// 离线
  IN_GAME = "IN_GAME",// 游戏中
}
export enum ChatType {
  PUBLIC = "PUBLIC", // 全部
  PRIVATE = "PRIVATE", // 私聊
  GROUP = "GROUP", // 群聊
}
export enum MessageType {
  SYSTEM = "SYSTEM", // 系统消息
  NOTICE = "NOTICE", // 公告消息
  CHAT = "CHAT", // 聊天消息
  SYNC_ONLINE_LIST = "SYNC_ONLINE_LIST", // 同步在线用户
  USER_STATE = "USER_STATE",// 用户上线、离线消息
  STATUS_UPDATE =  "STATUS_UPDATE", // 用户状态更新消息
  HISTORY_MSG = "HISTORY_MSG", // 历史聊天记录消息
  HEARTBEAT = "HEARTBEAT", // 心跳消息
}
export enum Action{
   /**
     * 登录
     */
   LOGIN="LOGIN",

   /**
    * 聊天
    */
   CHAT="CHAT",
}