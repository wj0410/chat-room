<template>
  <div class="gameCenter">
    <middle :itemList="gameList" />
    <headView :headObj="headObj" />
  </div>
</template>
<script setup lang="ts">
import middle from "@/components/middle.vue";
import headView from "@/components/headView.vue";
import type { GameCenterMiddle } from "@/constant/Props";
const gameObj:GameCenterMiddle = {
  type: "gameCenter",
  avatar: "https://avatars.githubusercontent.com/u/43922975?v=4",
  title: "斗地主",
};
let gameList = [gameObj, gameObj, gameObj, gameObj];

let headObj = {
  title: "斗地主"
};
</script>
<style lang="scss" scoped>
.gameCenter {
  display: flex;
  height: 100%;
}
</style>
