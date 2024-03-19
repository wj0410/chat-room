import {defineStore} from "pinia";
import LoginDTO from "@/model/LoginDTO";

const useUserStore: ReturnType<typeof defineStore> = defineStore('user', {
    state: () => ({
        onlineUserList: [],
        user: {},
    }),
    getters: {
        onlineUserTotal: (state) => state.onlineUserList,
        onlineUserList: (state) => state.onlineUserList,
        user: (state) => state.user,
    },
    actions: {
        login(username: string, password: string) {
            let loginDTO = new LoginDTO(username, password);
            console.log("login exec")
        }
    }
})

export default useUserStore