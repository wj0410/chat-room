import { defineStore } from "pinia";
import LoginDTO from "@/model/LoginDTO";
import User from "@/model/User";
import { UserMsgDTO } from "@/model/MessageDTO";
import MyWebSocket from "@/websocket/MyWebSocket";
import { Response } from "@/model/Response";
const useUserStore = defineStore("user", {
  state: () => ({
    userMap: new Map<string, User>(),
    loginUser: undefined as User | undefined,
    onlineChatRoomHisMsg: [] as Array<Response<UserMsgDTO>>
  }),
  getters: {
    onlineUserTotal: (state) => state.userMap.size,
    onlineUserList: (state): User[] => {
      return Array.from(state.userMap.values()) as User[];
    },
    onlineChatRoomHisMsgList: (state): Array<Response<UserMsgDTO>> => {
      if (state.onlineChatRoomHisMsg) {
        return state.onlineChatRoomHisMsg as Array<Response<UserMsgDTO>>;
      }
      return [];
    },
  },
  actions: {
    async login(username: string, password: string) {
      let loginDTO = new LoginDTO(username, password);
      // let user = login(loginDTO);
      // 1.loginUser
      const loginUser: User = new User(
        '{"id":1,"username":"admin","nickName":"管理员","avatar":"https://avatars.githubusercontent.com/u/43922975?v=4"}'
      );
      this.loginUser = loginUser
      // 2.token
      let token = ''
      // 3.socket
      const WEBSOCKET_SERVER_URL = import.meta.env.VITE_WEBSOCKET_SERVER_URL
      new MyWebSocket(loginUser, WEBSOCKET_SERVER_URL);

    },
    setOnlineUser(userList: User[]) {
      this.userMap.clear();
      userList.forEach((user) => {
        this.addOnlineUser(user)
      });
    },
    addOnlineUser(user: User) {
      this.userMap.set(user.username, user);
    },
    removeOnlineUser(user: User) {
      this.userMap.delete(user.username);
    },
    getUser(username: string) {
      return this.userMap.get(username);
    },
    logOut() {
      return new Promise((resolve, reject) => { });
    },
    setOnlineChatRoomHisMsgList(msgList: Array<Response<UserMsgDTO>>) {
      this.onlineChatRoomHisMsg = msgList
    },
  },
});

export default useUserStore;
