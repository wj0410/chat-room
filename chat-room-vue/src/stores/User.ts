import {defineStore} from "pinia";

const useUserStore = defineStore('user',{
    state:() => ({
        onlineUserTotal: 0,
        onlineUserList: [],
        user: undefined,
    }),
    getters: {
        onlineUserTotal: (state) => state.onlineUserTotal,
        onlineUserList: (state) => state.onlineUserList,
        user: (state) => state.user,
    },
    actions: {

    }
})

export default useUserStore