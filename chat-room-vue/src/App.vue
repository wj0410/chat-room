<template>
  <div>
    <RouterView />
  </div>
</template>
<script setup lang="ts">
import User from "./model/User";
import useUserStore from "@/store/user";
import { onMounted } from "vue";
import MyWebSocket from "./websocket/MyWebSocket";
import { getToken } from "@/util/auth";

const userStore = useUserStore();
let user1 = new User(
  '{"id":1,"username":"admin","nickName":"管理员","avatar":"https://avatars.githubusercontent.com/u/43922975?v=4"}'
);
let user2 = new User(
  '{"id":2,"username":"user","nickName":"用户","avatar":"https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"}'
);
userStore.loginUser = user1;
// let socket = new MyWebSocket('','ws://127.0.0.1:5679/websocket');
// user1.socket = socket
userStore.addOnlineUser(user1);
userStore.addOnlineUser(user2);
// console.log("loginUser", userStore.loginUser);
// console.log("onlineUserList", userStore.onlineUserList);
// console.log("onlineUserTotal", userStore.onlineUserTotal);
</script>

<style scoped></style>
