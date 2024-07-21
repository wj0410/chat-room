<template>
  <div class="gameCenter">
    <middle :middleList="gameListProp" @middleClick="middleClick" />
    <headView :middleProp="selectMiddleProp" />
  </div>
</template>
<script setup lang="ts">
import middle from "@/components/middle.vue";
import headView from "@/components/headView.vue";
import GameCenterMiddleProp from "@/prop/GameCenterMiddleProp";
import { admin, user } from "@/Data";
import { ref } from "vue";
const gameObj1 = new GameCenterMiddleProp("game:doudizhu", admin.avatar, '欢乐斗地主');
const gameObj2 = new GameCenterMiddleProp("game:wuziqi", user.avatar, '五子棋');

const gameListProp = ref([gameObj1, gameObj2]);
const selectMiddleProp = ref()
selectMiddleProp.value = gameListProp.value[0]
const middleClick = (item: GameCenterMiddleProp) => {
  selectMiddleProp.value = item
};
</script>
<style lang="scss" scoped>
.gameCenter {
  display: flex;
  height: 100%;
}
</style>