<template>
  <textarea ref="sendTextarea" v-model="data.sendText" maxlength="1000" autofocus
            @paste="handlePaste" @keydown="keydown"
            @keyup.ctrl.enter="handleCtrlEnter"></textarea>
  <div class="send-area-button-container">
    <el-button type="success" @click="handleSending">发送(S)</el-button>
  </div>
</template>

<script lang="ts" setup>
import {defineProps, reactive, ref} from "vue";
import {$store, $mitt} from '@/utils/MyCommon.ts'
import WebSocketManager from '@/websocket/WebSocketManager.ts'
import {ChatType, MessageContainerType, MessageType} from "@/constant/Enum";
import {MyWebSocket} from "@/websocket/MyWebSocket";
import {MittConstants} from "@/constant/constants";
import {NormalMessage} from "../../../../model/NormalMessage";
import {ClientType} from "../../../../constant/Enum";
import {Message} from "../../../../model/Message";

const props = defineProps({
  chatModel: {
    type: Object,
    default: null
  }
})
const sendTextarea = ref(null);
const imageUrl = ref("")
const userInfo: UserInfo = $store.userInfo
const data = reactive({
  sendText: ''
})
let webSocket = new WebSocketManager().getWebSocket(userInfo);
const handleSending = () => {
  if (!data.sendText || data.sendText.trim() === '') {
    return
  }
  let chatType
  let targetClientIds = []
  if (props.chatModel.chatClient.clientType === ClientType.PUBLIC) {
    chatType = ChatType.PUBLIC
  } else if (props.chatModel.chatClient.clientType === ClientType.PERSONAL) {
    chatType = ChatType.PRIVATE
    targetClientIds = [props.chatModel.chatClient.clientId]
  } else if (props.chatModel.chatClient.clientType === ClientType.GROUP) {
    chatType = ChatType.GROUP
  }
  let normalMsg = new NormalMessage({
    chatType: chatType,
    fromClientId: userInfo.clientId,
    fromAccount: userInfo.account,
    fromNickName: userInfo.nickName,
    fromClientAvatar: userInfo.avatar,
    msgList: [{type: MessageContainerType.TEXT, text: data.sendText}],
    targetClientIds:targetClientIds
  }).buildMessage();


  if (webSocket.readyState !== 1) {
    // 有可能重新连接后，webSocket对象已经丢失了，需要重新获取
    webSocket = new WebSocketManager().getWebSocket(userInfo);
  }
  send(webSocket, JSON.stringify(normalMsg))
  data.sendText = ''

  // 私聊消息，且不是自己给自己发送消息，回显自己的消息
  if (props.chatModel.chatClient.clientType === ClientType.PERSONAL) {
    if(userInfo.clientId !== normalMsg.data.targetClientIds[0]){
      $mitt.emit(MittConstants.NORMAL_MESSAGE,normalMsg.getRealityMessage())
    }
  }
}

const send = (webSocket: MyWebSocket, msg: string) => {
  if (webSocket.readyState === 1) {
    webSocket.send(msg);
  }
}

const keydown = (event) => {
  if (event.key === 'Enter' && !event.ctrlKey) {
    //说明是发送事件
    event.preventDefault(); // 阻止默认的换行行为
    handleSending()
  }
}

const handleCtrlEnter = (event) => {
  const input = event.target;
  const start = input.selectionStart;
  const end = input.selectionEnd;
  const value = data.sendText;
  event.preventDefault(); // 阻止默认的换行行为
  data.sendText = value.substring(0, start) + '\n' + value.substring(end);
  input.selectionStart = input.selectionEnd = start + 1; // 将光标移至新的一行
}

const handlePaste = (event) => {
  const items = (event.clipboardData || event.originalEvent.clipboardData).items;
  for (const item of items) {
    if (item.kind === 'file' && item.type.includes('image')) {
      const file = item.getAsFile();
      // 处理图片文件：例如上传、显示预览等操作
      processImage(file);
    }
  }
}

const processImage = (imageFile) => {
  // 这里只是将图片转成 base64 编码，实际应用中可能需要上传到服务器或进行其他处理
  const reader = new FileReader();
  reader.readAsDataURL(imageFile);
  reader.onload = () => {
    console.log(reader.result);
  };
}

// 追加内容
$mitt.on(MittConstants.APPEND_SEND_TEXT, (msg) => {
  const textarea = sendTextarea.value; // 获取文本框的引用
  const startPos = textarea.selectionStart; // 获取当前光标的位置
  const endPos = textarea.selectionEnd;
  const textBefore = data.sendText.substring(0, startPos);
  const textAfter = data.sendText.substring(endPos);
  data.sendText = textBefore + msg + textAfter; // 在光标后插入消息
  const newCursorPos = startPos + msg.length; // 计算新光标位置
  textarea.value = data.sendText; // 先将文本框的值设置为最新的值
  textarea.selectionStart = newCursorPos; // 设置光标位置为新位置
  textarea.selectionEnd = newCursorPos;
  textarea.focus(); // 聚焦文本框
});
</script>


<style scoped lang="less">
textarea {
  flex: 1;
  padding: 0 15px;
  border: 0;
  resize: none;
  outline: none;
  background-color: #f3f3f3;
}

.send-area-button-container {
  display: flex;
  justify-content: flex-end;
  margin: 10px;
}

</style>
