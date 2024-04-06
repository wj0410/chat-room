<template>
  <div class="left">
    <div class="occupying-area"></div>
    <div class="menu-bar-area">
      <div class="menu-bar-self">
        <img class="avatar"
             :src="userInfo.avatar"/>
      </div>
      <MenuBarTop/>
      <MenuBarBottom/>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {$store} from '@/utils/MyCommon.ts'
let userInfo: UserInfo = $store.userInfo;
import MenuBarTop from './left/menuBarTop.vue'
import MenuBarBottom from './left/menuBarBottom.vue'


</script>


<style scoped lang="less">
/* left */
.left {
  width: 60px;
  background-color: #E4E4E4;
  display: flex;
  flex-direction: column;

  .occupying-area {
    height:60px;
  }

  .menu-bar-area {
    flex: 1;
    display: flex;
    flex-direction: column;

    .menu-bar-self {
      text-align: center;
      margin-top: 8px;
      margin-bottom: 5px;
    }


  }
}
</style>
