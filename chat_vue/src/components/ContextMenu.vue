<template>
  <div class="context-menu" :style="{ top: menuProps.y + 'px', left: menuProps.x + 'px' }"
       @click.stop=""
       @contextmenu.prevent="prevent($event)">
    <!-- 菜单项内容 -->
    <template v-for="menu in menuProps.menuList" :key="menu.name">
      <div class="menu-item" @click="handleItemClick($event,menu)">{{ menu.name }}</div>
      <div class="menu-line" v-if="menu.underline"></div>
    </template>
  </div>
</template>

<script lang="ts" setup>
import {defineProps} from "vue";

const props = defineProps({
  menuProps: {
    type: Object,
    default: null
  }
})

const emit = defineEmits(["clickMenuItem"])

const handleItemClick = (event,menu) => {
  event.stopPropagation();// 阻止事件传播
  emit("clickMenuItem", menu); // 触发父组件传递的回调函数
}

const prevent = (event) => {
  event.preventDefault(); // 阻止默认的右键菜单
}
</script>

<style scoped lang="less">
.context-menu {
  border-radius: 5px;
  padding: 5px;
  font-size: 13px;
  background-color: #E9E8E7;
  border: 1px solid #BDBDBD;
  position: fixed;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  z-index: 1000;

  .menu-item {
    padding: 5px 10px;
    line-height: 1;
    border-radius: 5px;
  }

  .menu-item:hover {
    background: #2994FB;
    color: #ffffff;
  }
  .menu-line{
    height: 1px;
    background-color: #BDBDBD;
    margin:5px 10px
  }

}
</style>