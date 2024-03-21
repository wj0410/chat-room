import { defineStore } from "pinia";
import LoginDTO from "@/model/LoginDTO";
import User from "@/model/User";
import { login } from "@/api/login";

const useUserStore = defineStore("user", {
  state: () => ({
    userMap: new Map<string, User>(),
    loginUser: undefined as User | undefined,
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
      login(loginDTO);
      this.loginUser = new User(JSON.stringify(loginDTO));
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
      return new Promise((resolve, reject) => {});
    },
  },
});

export default useUserStore;
