<template>
  <div class="chat-list-area no-copy">
    <ChatListSearch/>
    <ChatList :chatModelList="chatModelList" @setChatModelList="setChatModelList"
              :selectedChatModel="selectedChatModel" @setSelectedChatModel="setSelectedChatModel"/>
  </div>
</template>

<script lang="ts" setup>
import ChatListSearch from './chatListArea/chatListSearch.vue'
import ChatList from './chatListArea/chatList.vue'
import {defineProps, reactive} from "vue";
import ClientModel from "@/model/ClientModel";
import {CommonConstants} from "../../constant/constants";
import ChatModel from "../../model/ChatModel";

const props = defineProps({
  chatModelList: {
    type: Array,
    default: []
  },
  selectedChatModel: {
    type: Object,
    default: {}
  }
})
const emit = defineEmits(["setSelectedChatModel","setChatModelList"])
const setSelectedChatModel = (chatModel: ChatModel) => {
  // 更新home组件的selectedChatModel
  emit("setSelectedChatModel", chatModel)
}
const setChatModelList = (chatModelList: Array<ChatModel>) => {
  // 更新home组件的selectedChatModel
  emit("setChatModelList", chatModelList)
}
</script>


<style scoped lang="less">
/* chat-list-area */
.chat-list-area {
  max-width: 300px;
  min-width: 260px;
  border-right: 1px solid #ccc;
  background-color: #F7F7F7;
  display: flex;
  flex-direction: column;
}
</style>
