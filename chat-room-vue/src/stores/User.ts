import { defineStore } from "pinia";
import LoginDTO from "@/model/LoginDTO";
import User from "@/model/User";
import { login } from "@/api/Login";

const useUserStore = defineStore('user', {
    state: () => ({
        onlineUserList: [],
        user: undefined
    }),
    getters: {
        onlineUserTotal: (state) => state.onlineUserList.length,
    },
    actions: {
        login(username: string, password: string) {
            let loginDTO = new LoginDTO(username, password);
            login(loginDTO)
            this.user = new User(JSON.stringify(loginDTO));
        }
    }
})

export default useUserStore