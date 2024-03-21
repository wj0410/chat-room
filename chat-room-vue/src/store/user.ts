import { defineStore } from "pinia";
import LoginDTO from "@/model/LoginDTO";
import User from "@/model/User";
import Client from "@/model/Client";
import { login } from "@/api/login";
import MyWebSocket from "@/websocket/MyWebSocket";

const WEBSOCKET_SERVER_URL = import.meta.env.VITE_WEBSOCKET_SERVER_URL
const useUserStore = defineStore("user", {
  state: () => ({
    userMap: new Map<string, User>(),
    client: undefined as Client | undefined,
    loginUser: undefined as User | undefined
  }),
  getters: {
    onlineUserTotal: (state) => state.userMap.size,
    onlineUserList: (state): User[] => {
      return Array.from(state.userMap.values()) as User[];
    },
  },
  actions: {
    async login(username: string, password: string) {
      let loginDTO = new LoginDTO(username, password);
      let user = login(loginDTO);
      // 1.loginUser
      const loginUser = new User(JSON.stringify(user));
      this.loginUser = loginUser
      // 2.token
      let token = ''
      // 3.client
      let socket = new MyWebSocket(loginUser, WEBSOCKET_SERVER_URL);
      this.client = new Client(socket,loginUser)
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
  },
});

export default useUserStore;
