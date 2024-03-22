<template>
  <div class="chat">
    <middle :itemList="chatUserList" @middleClick="middleClick" />
    <chatView :chatViewProp="chatViewPropSelect" />
  </div>
</template>
<script setup lang="ts">
import middle from "@/components/middle.vue";
import chatView from "./component/chatView.vue";
import type { ChatMiddleProp, ChatViewProp, MessageProp } from "@/constant/Props";
import { chatUserObj, chatPublicObj } from "@/Data";
import { ref } from "vue";

const chatViewPropSelect = ref<ChatViewProp | undefined>()

const chatUserList: Array<ChatMiddleProp> = [chatUserObj, chatPublicObj];
chatViewPropSelect.value = chatUserList[0].chatViewProp;

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