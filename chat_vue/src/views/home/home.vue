<template>
  <div class="container" @click="handleClick" @contextmenu.prevent="prevent($event)">
    <Left/>
    <ChatListArea :chatModelList="chatModelList" @setChatModelList="setChatModelList"
                  :selectedChatModel="chatModel" @setSelectedChatModel="setSelectedChatModel"/>
    <ChatArea :chatModel="chatModel"/>
  </div>
</template>

<script setup lang="ts">
import Left from './left'
import ChatListArea from './chatListArea'
import ChatArea from './chatArea'
import WebSocketManager from '@/websocket/WebSocketManager.ts'
import {$store} from '@/utils/MyCommon.ts'
import {$mitt} from "@/utils/MyCommon";
import {MittConstants} from "@/constant/constants";
import {onBeforeUnmount, onMounted, reactive, ref, watch} from "vue";
import ChatModel, {createChatModel} from "../../model/ChatModel";
import {SyncOnlineMessage} from "../../model/SyncOnlineMessage";
import {MessageType} from "../../constant/Enum";
import {NormalMessage} from "../../model/NormalMessage";
import {getChatClientIdByMessage, publicChatRoomChatModel} from "../../utils/message";
import {Message} from "../../model/Message";
import {PromptMessage} from "../../model/PromptMessage";
import {getChatModelListData, setChatModelListData} from "../../utils/data";

let userInfo: UserInfo = $store.userInfo;

new WebSocketManager().getWebSocket(userInfo)

let chatModelListData = getChatModelListData();

if (!chatModelListData) {
  chatModelListData = [publicChatRoomChatModel]
  setChatModelListData(chatModelListData)
}
let chatModelList: Array<ChatModel> = reactive(chatModelListData)

let chatModel = ref(null)

watch(chatModelList, (newChatModelList: Array<ChatModel>) => {
  let data = []
  for (let i = 0; i < newChatModelList.length; i++) {
    // 排除掉chatMessageList里状态不为  NormalMessage的数据
    let tempChatMessageList = []
    newChatModelList[i].chatMessageList.forEach(message => {
      if (message.type === MessageType.NORMAL) {
        tempChatMessageList.push(message)
      }
    })
    let tempModel = createChatModel(
        newChatModelList[i].chatClient,
        tempChatMessageList,
        newChatModelList[i].memberList,
        newChatModelList[i].online,
        newChatModelList[i].unreadCount,
        newChatModelList[i].notice,
        newChatModelList[i].show,
    )
    data.push(tempModel)
  }
  setChatModelListData(data)
}, {deep: true})

const handleClick = () => {
  // 关闭表情面板
  $mitt.emit(MittConstants.HIDE_EMOJI)
  // 关闭更多面板
  $mitt.emit(MittConstants.SHOW_MORE_CONTAINER, false);
  // 关闭聊天列表右键菜单
  $mitt.emit(MittConstants.HIDE_CHAT_LIST_MENU);

}
const setSelectedChatModel = (data: ChatModel) => {
  chatModel.value = data
}
const setChatModelList = (data: Array<ChatModel>) => {
  chatModelList = data
}

// 新增chatModel
$mitt.on(MittConstants.ADD_CHAT_MODEL, (data: ChatModel) => {
  // 校验是否已存在
  for (let i = 0; i < chatModelList.length; i++) {
    if (chatModelList[i].chatClient.clientId === data.chatClient.clientId) {
      // 设置显示
      chatModelList[i].show = true
      // 选中
      chatModel.value = chatModelList[i]
      // 滚动条到最后
      $mitt.emit(MittConstants.SCROLL_TO_BOTTOM);
      return
    }
  }
  // 不在列表中，则添加
  chatModelList.push(data)
  // 选中
  chatModel.value = data
  // 滚动条到最后
  $mitt.emit(MittConstants.SCROLL_TO_BOTTOM);
});


const increaseUnreadCount = (clientId: string) => {
  chatModelList.forEach(chatModel => {
    if (chatModel.chatClient.clientId === clientId) {
      chatModel.unreadCount = chatModel.unreadCount + 1
      return
    }
  })
};

// 更新ChatModel的ChatMessageList
const pushChatModelChatMessageList = (message: Message) => {
  let clientId = getChatClientIdByMessage(message)
  if (clientId) {
    if (!chatModelList.find(chatModel => chatModel.chatClient.clientId === clientId)) {
      // 未找到该聊天客户端，则添加
      // 从聊天室的memberList找
      publicChatRoomChatModel.memberList.forEach(member => {
        if (member.clientId === clientId) {
          let chatModel = createChatModel(member,
              [message],
              [member],
              true,
              1
          )
          chatModelList.push(chatModel)
        }
      })
    } else {
      // chatModelList显示未读消息
      if (message.type === MessageType.NORMAL && (!chatModel.value || clientId !== chatModel.value.chatClient.clientId)) {
        increaseUnreadCount(clientId)
      }
      chatModelList.forEach((item) => {
        // 判断消息要具体放到哪一个chatModel中
        if (item.chatClient.clientId === clientId) {
          item.chatMessageList.push(message)
          return
        }
      })
    }
  }
}

// 更新公共聊天室的在线成员
$mitt.on(MittConstants.SYNC_ONLINE_MESSAGE, (data: SyncOnlineMessage) => {
  publicChatRoomChatModel.memberList = []
  publicChatRoomChatModel.memberList.push(...data.clientOnlineList)
  chatModelList.forEach(chatModel => {
    if (chatModel.chatClient.clientId === publicChatRoomChatModel.chatClient.clientId) {
      chatModel.memberList = publicChatRoomChatModel.memberList
      return
    }
  })
});

$mitt.on(MittConstants.WELCOME_MESSAGE, (data: PromptMessage) => {
  pushChatModelChatMessageList(data.buildMessage())
  // 上线
  let clientId = data.clientId;
  chatModelList.forEach(chatModel => {
    if (chatModel.chatClient.clientId === clientId) {
      chatModel.online = true
      return
    }
  })
});

$mitt.on(MittConstants.NORMAL_MESSAGE, (data: NormalMessage) => {
  pushChatModelChatMessageList(data.buildMessage())
  if (!onfocused.value) {
    // 显示红点
    showNotice()
  }
});

$mitt.on(MittConstants.LEAVE_MESSAGE, (data: PromptMessage) => {
  pushChatModelChatMessageList(data.buildMessage())
  // 下线
  let clientId = data.clientId;
  chatModelList.forEach(chatModel => {
    if (chatModel.chatClient.clientId === clientId) {
      chatModel.online = false
      return
    }
  })
});

// todo
const showNotice = () => {

}
const hideNotice = () => {

}

const onfocused = ref(true)
onMounted(() => {
  document.addEventListener('visibilitychange', handleVisibilityChange);
  // 刷新在线状态
  let onlineList = chatModelList[0].memberList;
  chatModelList.forEach(chatModel => {
    if(chatModel.chatClient.clientId !== publicChatRoomChatModel.chatClient.clientId){
      if (!onlineList.some((item) => item.clientId === chatModel.chatClient.clientId && item.clientId)) {
        chatModel.online = false
      }
    }
  })
});
onBeforeUnmount(() => {
  document.removeEventListener('visibilitychange', handleVisibilityChange);
});
const handleVisibilityChange = () => {
  if (document.visibilityState === 'hidden') {
    // 页面失去了焦点
    onfocused.value = false
  } else {
    // 页面重新获得了焦点
    onfocused.value = true
    // 隐藏红点
    hideNotice()
  }
}
const prevent = (event) => {
  event.preventDefault(); // 阻止默认的右键菜单
}
</script>

<style scoped lang="less">
.container {
  display: flex;
  height: 100vh;
  width: 1096px;
  max-height: 810px;
  max-width: 1096px;
  min-height: 560px;
  min-width: 830px;
  margin: 0 auto;
  border: 1px solid #ccc;
  border-radius: 5px;
  overflow: hidden;
}
</style>
