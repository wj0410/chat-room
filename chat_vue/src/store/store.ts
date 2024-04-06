import {defineStore} from 'pinia'

const useStore = defineStore('main', {
    state: () => {
        return {
            userInfo: localStorage.getItem("userInfo") ? JSON.parse(localStorage.getItem("userInfo")) : {},
        }
    },
    getters: {

    },
    actions: {},
})
export default useStore
