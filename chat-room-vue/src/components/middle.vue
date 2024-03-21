<template>
  <div class="middle">
    <div class="middle-search">
      <div><input style="width: 200px" type="text" placeholder="🔍搜索" /></div>
    </div>
    <div class="middle-item">
      <ul>
        <template v-for="(item, index) in itemList" :key="index">
          <li :tabindex="index" v-if="item.type == 'chat'">
            <div class="avatar">
              <img class="avatar" :src="item.avatar" />
              <div class="unread-count">{{ item.unread }}</div>
            </div>
            <div class="chat-content">
              <h3>{{ item.name }}</h3>
              <span>{{ item.news }}</span>
            </div>
          </li>
          <li :tabindex="index" v-else-if="item.type == 'gameCenter'">
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
const props = defineProps({
  itemList: {
    type: Array,
    default: [],
  },
});
</script>
<style lang="scss" scoped>
@import "@/assets/components/middle.scss";
</style>
