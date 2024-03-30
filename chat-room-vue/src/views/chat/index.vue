<template>
  <div class="chat">
    <middle :itemList="chatMiddleProp" @middleClick="middleClick" />
    <chatView :chatViewProp="chatViewPropSelect" />
  </div>
</template>
<script setup lang="ts">
import middle from "@/components/middle.vue";
import chatView from "./component/chatView.vue";
import type { ChatMiddleProp, ChatViewProp, MessageProp, HeadProp } from "@/constant/Props";
import { ChatType } from "@/constant/Enums";
import { chatUserObj } from "@/Data";
import { ref } from "vue";
import useUserStore from "@/store/user";
const userStore = useUserStore()
// 获取历史消息
const getHistoryMessages = (): Array<MessageProp> => {
  return []
}
const buildPublicChat = (): ChatMiddleProp => {
  const publicChat: ChatMiddleProp = {
    type: "chat",
    chatType: ChatType.PUBLIC,
    avatar: "https://img2.baidu.com/it/u=4085937757,1625118201&fm=253&fmt=auto&app=138&f=JPEG?w=749&h=500",
    unread: 0,
    name: "在线聊天室"
  }

  const publicHeadProp: HeadProp = {
    title: publicChat.name,
    userCount: userStore.onlineUserTotal,
    groupUserList: userStore.onlineUserList
  }
  const publicChatViewProp: ChatViewProp = {
    chatType: publicChat.chatType,
    headProp: publicHeadProp,
    MessagePropList: getHistoryMessages()
  }
  publicChat.chatViewProp = publicChatViewProp
  return publicChat
}
const chatMiddleProp: Array<ChatMiddleProp> = [buildPublicChat(), chatUserObj];

const chatViewPropSelect = ref<ChatViewProp | undefined>()
chatViewPropSelect.value = chatMiddleProp[0].chatViewProp;

const middleClick = (item: ChatMiddleProp) => {
  chatViewPropSelect.value = item.chatViewProp;
};

</script>
<style lang="scss" scoped>
.chat {
  display: flex;
  height: 100%;
}
</style>