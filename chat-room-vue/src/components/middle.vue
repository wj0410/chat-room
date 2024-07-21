<template>
  <div class="middle no-copy" :style="{ width: middleWidth + 'px', position: 'relative' }">
    <div class="line" @mousedown="mousedown"></div>
    <div class="middle-search">
      <input type="text" placeholder="🔍搜索" />
    </div>
    <div class="middle-item">
      <ul>
        <template v-for="(item, index) in  middleList " :key="index">
          <li :tabindex="index" v-if="item.type==='chat'"
            @click="clickItem(item)">
            <div class="avatar">
              <img class="avatar" :src="item.avatar" />
              <div class="unread-count" v-if="item.unread && item.unread > 0">{{ item.unread }}</div>
            </div>
            <div class="chat-content">
              <h3>{{ item.title }}</h3>
              <span>{{ item.news }}</span>
            </div>
          </li>
          <li :tabindex="index" v-else-if="item.type==='gameCenter'"
            @click="clickItem(item)">
            <div class="avatar">
              <img class="avatar" :src="item.avatar" />
            </div>
            <div class="gameCenter-content">
              <div class="title">{{ item.title }}</div>
            </div>
          </li>
        </template>
      </ul>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { IMiddleProp } from "@/prop/interfaces/IProps";

import { onMounted, ref } from 'vue'
const props = defineProps({
  middleList: {
    type: Array<IMiddleProp>,
    default: [],
  },
});
const emit = defineEmits(["middleClick"])
const clickItem = (item: IMiddleProp) => {
  emit("middleClick", item)
};
const middleWidth = ref(240)
const dragSwitch = ref(false)
// 点击拖拽时x轴位置
const startX = ref(0);
// middle点击时的宽度
const dragWidth = ref(0);
onMounted(() => {
  document.documentElement.addEventListener('mousemove', mousemove);
  document.documentElement.addEventListener('mouseup', mouseup);
})
const mousedown = (event: any) => {
  event.preventDefault();
  dragSwitch.value = true
  const middleWidth: any = document.querySelector('.middle')
  startX.value = event.clientX || event.touches[0].clientX;
  dragWidth.value = middleWidth.offsetWidth;
}
const mousemove = (event: any) => {
  if (!dragSwitch.value) {
    return
  }
  const clientX = event.clientX || event.touches[0].clientX;
  const offsetX = clientX - startX.value;

  let newDragWidth = dragWidth.value + offsetX;
  // 添加最小宽度和最大宽度限制
  if (newDragWidth < 200) {
    newDragWidth = 200;
  } else if (newDragWidth > 300) {
    newDragWidth = 300;
  }
  middleWidth.value = newDragWidth
}
const mouseup = () => {
  dragSwitch.value = false
}

</script>
<style lang="scss" scoped>
@import "@/assets/components/middle.scss";

.line {
  height: 100%;
  width: 5px;
  position: absolute;
  right: 0;
  cursor: col-resize;
  border-right: 1px solid #ccc;
}
</style>@/props/Props