<template>
  <div class="base">
    <div class="box">
      <div class="box-left no-copy no-right-click">
        <leftMenu />
      </div>
      <div class="box-right">
        <RouterView />
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import leftMenu from './leftMenu.vue';
</script>

<style lang="scss" scoped>
.base {
  height: 100vh;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  .box {
    font-family: Arial, sans-serif;
    height: 100vh;
    width: 1096px;
    max-height: 810px;
    min-height: 560px;
    max-width: 1096px;
    min-width: 830px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    overflow: hidden;
    display: flex;
    .box-left {
      width: 60px;
    }
    .box-right {
      flex: 1;
    }
  }
}
</style>
