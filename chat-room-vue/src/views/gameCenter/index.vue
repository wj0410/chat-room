<template>
  <div class="gameCenter">
    <middle :itemList="gameList" @middleClick="middleClick" />
    <headView :headProp="refHeadProp" />
  </div>
</template>
<script setup lang="ts">
import middle from "@/components/middle.vue";
import headView from "@/components/headView.vue";
import type { GameCenterMiddleProp, HeadProp } from "@/constant/Props";
import { admin, user } from "@/Data";
import { ref } from "vue";
// gameCenterMiddle
const gameObj1: GameCenterMiddleProp = {
  type: "gameCenter",
  avatar: admin.avatar,
  title: "欢乐斗地主",
};
const gameObj2: GameCenterMiddleProp = {
  type: "gameCenter",
  avatar: user.avatar,
  title: "五子棋",
};
let gameList = [gameObj1, gameObj2];
const refHeadProp = ref<HeadProp>()
refHeadProp.value = {
  title: gameList[0].title
};
const middleClick = (item: GameCenterMiddleProp) => {
  refHeadProp.value = {
    title: item.title
  };
};
</script>
<style lang="scss" scoped>
.gameCenter {
  display: flex;
  height: 100%;
}
</style>
