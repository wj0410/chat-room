<template>
  <div class="chat-area no-copy">
    <template v-if="chatModel">
      <ChatNameArea :chatModel="chatModel"/>
      <ChatMessageArea :chatModel="chatModel"/>
      <ChatSendArea :chatModel="chatModel"/>
    </template>
    <div class="chat-area-empty" v-else>
      <svg class="icon" aria-hidden="true">
        <use xlink:href="#icon-weixin"></use>
      </svg>
    </div>
  </div>
</template>

<script lang="ts" setup>
import ChatNameArea from './chatArea/chatNameArea'
import ChatMessageArea from './chatArea/chatMessageArea'
import ChatSendArea from './chatArea/chatSendArea'
import {defineProps, onMounted, ref} from "vue";


const props = defineProps({
  chatModel: {
    type: Object,
    default: null
  }
})


</script>

<style scoped lang="less">
/* chat-area */
.chat-area {
  flex: 1;
  min-width: 510px;
  background-color: #F3F3F3;
  display: flex;
  flex-direction: column;

  .chat-area-empty {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    background: #ffffff;

    svg {
      width: 100px;
      height: 100px;
    }
  }
}
</style>
