<template>
  <div class="chat">
    <middle :itemList="chatUserList" />
    <chatView :chatObj="chatObj" />
  </div>
</template>
<script setup lang="ts">
import middle from "@/components/middle.vue";
import chatView from "@/components/chat/chatView.vue";
let chatUserObj = {
  type: "chat",
  avatar:
    "https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
  unread: 5,
  name: "测试1",
  news: "最近消息摘要",
};
let chatUserObj2 = {
  type: "chat",
  avatar: "https://avatars.githubusercontent.com/u/43922975?v=4",
  unread: 1,
  name: "测试2",
  news: "最近消息摘要",
};
let chatUserList = [chatUserObj, chatUserObj2];
let chatObj = {
  id: "...",
  headObj: {
    title: "测试",
    userCount: 3,
    groupUserList: [
      {
        id: 0,
        username: "游客-xxx",
        avatar: "https://avatars.githubusercontent.com/u/43922975?v=4",
        nickName: "游客-xxx",
      },
      {
        id: 1,
        username: "admin",
        avatar: "https://avatars.githubusercontent.com/u/43922975?v=4",
        nickName: "管理员",
      },
      {
        id: 2,
        username: "用户",
        avatar:
          "https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
        nickName: "用户",
      },
    ],
  },
};
</script>
<style lang="scss" scoped>
.chat {
  display: flex;
  height: 100%;
}
</style>
