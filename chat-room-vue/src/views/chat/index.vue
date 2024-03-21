<template>
  <div class="chat">
    <middle :itemList="chatUserList" />
    <chatView :chatViewProp="chatViewProp" />
  </div>
</template>
<script setup lang="ts">
import middle from "@/components/middle.vue";
import chatView from "@/components/chat/chatView.vue";
import type { ChatMiddleProp, ChatViewProp } from "@/constant/Props";
import useUserStore from "@/store/user";

const chatUserObj: ChatMiddleProp = {
  type: "chat",
  avatar:
    "https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
  unread: 5,
  name: "测试1",
  news: "最近消息摘要",
};
const chatUserObj2: ChatMiddleProp = {
  type: "chat",
  avatar: "https://avatars.githubusercontent.com/u/43922975?v=4",
  unread: 1,
  name: "测试2",
  news: "最近消息摘要",
};
const chatUserList: Array<ChatMiddleProp> = [chatUserObj, chatUserObj2];
const chatViewProp: ChatViewProp = {
  headProp: {
    title: "测试",
    userCount: 3,
    groupUserList: useUserStore().onlineUserList,
  },
};
</script>
<style lang="scss" scoped>
.chat {
  display: flex;
  height: 100%;
}
</style>