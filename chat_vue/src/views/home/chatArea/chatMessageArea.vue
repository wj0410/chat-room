<template>
  <div class="chat-message-area">
    <ChatContent :chatModel="chatModel"/>
    <NewMessage/>
    <Offline/>
  </div>
</template>

<script lang="ts" setup>
import ChatContent from './chatMessageArea/chatContent'
import NewMessage from './chatMessageArea/newMessage'
import Offline from './chatMessageArea/offline'
import {defineProps} from "vue";

const props = defineProps({
  chatModel: {
    type: Object,
    default: null
  }
})

</script>


<style scoped lang="less">
/* chat-message-area */
.chat-message-area {
  flex: 0.7;
  min-height: 400px;
  position: relative;
  overflow-y: auto;
  display: flex;
  flex-direction: column;
}
</style>
