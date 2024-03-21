<template>
  <div class="gameCenter">
    <middle :itemList="gameList" />
    <headView :headProp="headProp" />
  </div>
</template>
<script setup lang="ts">
import middle from "@/components/middle.vue";
import headView from "@/components/headView.vue";
import type { GameCenterMiddleProp, HeadProp } from "@/constant/Props";

const gameObj: GameCenterMiddleProp = {
  type: "gameCenter",
  avatar: "https://avatars.githubusercontent.com/u/43922975?v=4",
  title: "斗地主",
};
let gameList = [gameObj, gameObj, gameObj, gameObj];

let headProp: HeadProp = {
  title: "斗地主"
};
</script>
<style lang="scss" scoped>
.gameCenter {
  display: flex;
  height: 100%;
}
</style>
