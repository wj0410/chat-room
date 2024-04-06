<template>
  <div class="chat-send-tool-area">
    <ChatSendToolLeft/>
    <ChatSendToolRight/>
  </div>
</template>

<script lang="ts" setup>
import ChatSendToolLeft from './chatSendToolArea/chatSendToolLeft.vue'
import ChatSendToolRight from './chatSendToolArea/chatSendToolRight.vue'

</script>


<style scoped lang="less">
.chat-send-tool-area{
  border-top: 1px solid #ccc;
  display: flex;
}
</style>
