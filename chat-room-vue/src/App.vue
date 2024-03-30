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
const userStore = useUserStore()
userStore.login("admin","123456")
// console.log("loginUser", userStore.loginUser);
// console.log("onlineUserList", userStore.onlineUserList);
// console.log("onlineUserTotal", userStore.onlineUserTotal);

</script>

<style scoped></style>
