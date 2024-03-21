<template>
  <div class="gameCenter">
    <middle :itemList="list" />
    <chatView />
  </div>
</template>
<script setup lang="ts">
import middle from "@/components/middle.vue";
import chatView from "@/components/chat/chatView.vue";

let gameObj = {
  type: "gameCenter",
  avatar: "https://avatars.githubusercontent.com/u/43922975?v=4",
  title: "斗地主",
};
let list = [gameObj, gameObj, gameObj, gameObj];
</script>
<style lang="scss" scoped>
.gameCenter {
  display: flex;
  height: 100%;
}
</style>
