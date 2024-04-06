<template>
  <div class="chat-send-area">
    <ChatSendToolArea/>
    <Textarea :chatModel="chatModel"/>
<!--    <QuillEditor/>-->

  </div>
</template>

<script lang="ts" setup>
import ChatSendToolArea from './chatSendArea/chatSendToolArea.vue'
import Textarea from './chatSendArea/textarea'
import QuillEditor from './chatSendArea/editor.vue'
import {defineProps} from "vue";

const props = defineProps({
  chatModel: {
    type: Object,
    default: null
  }
})

</script>


<style scoped lang="less">
/* chat-send-area */
.chat-send-area{
  display: flex;
  flex-direction: column;
  flex:0.3;
  max-height: 350px;
  min-height: 110px;
}
</style>
