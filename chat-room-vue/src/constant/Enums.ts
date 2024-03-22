export enum UserStatus {
  ON_LINE = "ON_LINE",// 在线
  IN_GAME = "IN_GAME",// 游戏中
}
export enum ChatType {
  PUBLIC = "PUBLIC", // 全部
  PRIVATE = "PRIVATE", // 私聊
  GROUP = "GROUP", // 群聊
}
export enum MessageType {
  NOTICE = "NOTICE", // 公告消息
  CHAT = "CHAT", // 聊天消息
  SYNC_ONLINE = "SYNC_ONLINE", // 同步在线用户
  USER_ONLINE = "USER_ONLINE", // 新用户上线
  USER_OFFLINE = "USER_OFFLINE", // 用户下线
}