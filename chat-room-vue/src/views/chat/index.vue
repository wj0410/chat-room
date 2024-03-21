<template>
  <div class="chat">
    <middle :itemList="list" />
    <chatView />
  </div>
</template>
<script setup lang="ts">
import middle from "@/components/middle.vue";
import chatView from "@/components/chat/chatView.vue";
let chatObj = {
  type:'chat',
  avatar:'https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',
  unread:5,
  name:'测试',
  news:'最近消息摘要'
}
let gameObj = {
  type:'gameCenter',
  avatar:'https://avatars.githubusercontent.com/u/43922975?v=4',
  title:'斗地主'
}
let list = [chatObj,chatObj,chatObj,chatObj,chatObj,chatObj,chatObj,chatObj,chatObj,chatObj,chatObj,chatObj,chatObj,chatObj,chatObj,chatObj]
</script>
<style lang="scss" scoped>
  .chat{
    display: flex;
    height: 100%;
  }
</style>
