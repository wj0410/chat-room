<template>
  <div class="chat">
    <middle :middleList="chatMiddleListRef" @middleClick="middleClick" />
    <chatView :chatViewProp="chatViewPropSelect" :chatMiddleProp="chatMiddlePropSelect" />
  </div>
</template>
<script setup lang="ts">
import middle from "@/components/middle.vue";
import chatView from "./component/chatView.vue";
import { ChatMiddleProp, ChatMiddlePropBuilder } from "@/prop/ChatMiddleProp";
import ChatViewProp from "@/prop/ChatViewProp";
import MessageProp from "@/prop/MessageProp";
import { chatUserMiddle, msg1, msg2 } from "@/Data";
import { ref } from "vue";
import useUserStore from "@/store/user";
import { useChatMiddleCache, useMsgCache } from "@/cache/userCache";
import { ChatType } from "@/constant/Enums";
const { getChatMiddle, addChatMiddle, removeChatMiddle } = useChatMiddleCache()
const { getMsg, addMsg } = useMsgCache()
const userStore = useUserStore()
// 1.构建middleList
// 1.1先构建在线聊天室middleProp
const chatMiddleListRef = ref<Array<ChatMiddleProp>>()
chatMiddleListRef.value = [ChatMiddlePropBuilder.buildOnlineChatRoom()]
// 1.2从缓存获取其他middleProp
removeChatMiddle(chatUserMiddle)
addChatMiddle(chatUserMiddle)
chatMiddleListRef.value.push(...getChatMiddle())
// 2.构建chatView
const chatMiddlePropSelect = ref<ChatMiddleProp>()
const chatViewPropSelect = ref<ChatViewProp>()

addMsg(chatUserMiddle.id, msg1)
addMsg(chatUserMiddle.id, msg2)
const getChatViewPropByChatMiddle = (chatMiddle: ChatMiddleProp): ChatViewProp => {
  let msg: Array<MessageProp> = [];
  if (chatMiddle.chatType === ChatType.PUBLIC) {
    // 获取在线聊天室历史消息
    msg = []
  } else {
    msg = getMsg(chatMiddle.id)
  }
  return new ChatViewProp(chatMiddle.chatType, msg)
};

chatMiddlePropSelect.value = chatMiddleListRef.value[0]
chatViewPropSelect.value = getChatViewPropByChatMiddle(chatMiddlePropSelect.value)

const middleClick = (item: ChatMiddleProp) => {
  chatMiddlePropSelect.value = item;
  chatViewPropSelect.value = getChatViewPropByChatMiddle(item)
};

</script>
<style lang="scss" scoped>
.chat {
  display: flex;
  height: 100%;
}
</style>