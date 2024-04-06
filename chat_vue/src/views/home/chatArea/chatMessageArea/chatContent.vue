<template>
  <ContextMenu v-if="showMenu" :menuProps="menuProps" @clickMenuItem="clickMenuItem"/>
  <div class="chat-content" ref="chatContent" @scroll="handleScroll">
    <template v-for="item in chatModel.chatMessageList">
      <template v-if="item.type === MessageType.NORMAL">
        <div v-for="msgItem in item.data.msgList" :class="normalMessageStyle(item.data)">
          <div class="head-img">
            <img class="avatar" @dblclick="handleDoubleClick(item.data)"
                 @contextmenu.prevent="showContextMenu($event,item.data)"
                 :src="item.data.fromClientAvatar?item.data.fromClientAvatar:WEB_FILE_URL+'/images/default.png'"/>
          </div>
          <div>
            <div v-if="item.data.fromAccount !== userInfo.account" class="nick-name">{{ item.data.fromNickName }}</div>
            <span v-if="msgItem.type===MessageContainerType.TEXT" class="content">{{ msgItem.text }}</span>
            <span v-if="msgItem.type===MessageContainerType.IMAGE" class="content">[图片]</span>
          </div>
        </div>
      </template>
      <div v-if="item.type === MessageType.PROMPT" class="prompt">
        {{ item.data.msg }}
      </div>
    </template>
  </div>
</template>

<script lang="ts" setup>
import ContextMenu from "@/components/ContextMenu";
import {$mitt, $moment} from "@/utils/MyCommon.ts";
import {ref, reactive, onMounted, watch, nextTick, defineProps} from "vue";
import {$store} from '@/utils/MyCommon.ts'
import {MittConstants} from "../../../../constant/constants";
import {NormalMessage} from "../../../../model/NormalMessage";
import {MessageContainerType, MessageType} from "../../../../constant/Enum";
import {Message} from "../../../../model/Message";
import {SyncOnlineMessage} from "../../../../model/SyncOnlineMessage";
import {PromptMessage} from "../../../../model/PromptMessage";
import {publicChatRoomChatModel} from "../../../../utils/message";
import ChatModel, {createChatModel} from "../../../../model/ChatModel";

const WEB_FILE_URL = import.meta.env.VITE_WEB_FILE_URL
const showMenu = ref(false)
const rightClickNormalMessage = ref(null)
const props = defineProps({
  chatModel: {
    type: Object,
    default: null
  }
})
const computedMenuList = () => {
  let atName = '@ '
  if (rightClickNormalMessage.value) {
    atName += rightClickNormalMessage.value.fromNickName
  }
  const atMenuObj = {
    name: atName,
    callback: () => {
      $mitt.emit(MittConstants.APPEND_SEND_TEXT, atName.replace(' ', '')+" ");
    },
  }
  const sendMsgMenuObj = {
    name: '发消息',
    callback: () => {
      handleDoubleClick(rightClickNormalMessage.value)
    },
  }
  const menuList = [sendMsgMenuObj, atMenuObj];
  return menuList;
}
let menuProps = reactive({
  menuList: computedMenuList(),
  x: 0,
  y: 0
})
const showContextMenu = (event, data: NormalMessage) => {
  event.preventDefault(); // 阻止默认的右键菜单
  if (props.chatModel.chatClient.account !== publicChatRoomChatModel.chatClient.clientId
      || data.fromAccount === userInfo.account) {
    return
  }
  menuProps.x = event.clientX;
  menuProps.y = event.clientY;
  showMenu.value = true;
  rightClickNormalMessage.value = data
  menuProps.menuList = computedMenuList()
}
const clickMenuItem = (menu) => {
  menu.callback()
  hideChatListMenu()
};
$mitt.on(MittConstants.HIDE_CHAT_LIST_MENU, () => {
  hideChatListMenu()
});
const hideChatListMenu = () => {
  showMenu.value = false
  rightClickNormalMessage.value = null
}

let userInfo: UserInfo = $store.userInfo;
const chatContent = ref(null);
const unreadCount = ref(0);

onMounted(() => {
  // 初始化时将滚动条显示在最新一条记录
  scrollToBottom();
});

// 将滚动条显示在最新一条记录
const scrollToBottom = () => {
  if (chatContent.value) {
    nextTick(() => {
      chatContent.value.scrollTop = chatContent.value.scrollHeight;
      unreadCount.value = 0
    });
  }
};

const handleScroll = () => {
  if (isScrollAtBottom()) {
    $mitt.emit(MittConstants.SHOW_NEW_MESSAGE, {show: false});
  }
};

// 判断滚动条在不在最下面
const isScrollAtBottom = () => {
  if (chatContent.value) {
    const chatContentEl = chatContent.value;
    return chatContentEl.scrollTop + chatContentEl.clientHeight === chatContentEl.scrollHeight;
  }
  console.log('isScrollAtBottom出bug了！');
  // todo 这里不知道为什么有bug，页面停留一会后，接收到normalMessage消息的时候，chatContent就为null了
  return true;
};

// 订阅消息
$mitt.on(MittConstants.SCROLL_TO_BOTTOM, () => {
  scrollToBottom();
});

$mitt.on(MittConstants.NORMAL_MESSAGE, (data: NormalMessage) => {
  if (data.fromAccount === userInfo.account) {
    // 自己发送消息，直接滚动到最下面
    scrollToBottom();
  } else {
    // 其他人发送的消息
    // 如果当前滚动条不在最下方，则提示有新的消息
    if (!isScrollAtBottom()) {
      unreadCount.value = unreadCount.value + 1;
      $mitt.emit(MittConstants.SHOW_NEW_MESSAGE, {show: true, count: unreadCount.value});
    } else {
      // 否则直接滚动到最下面展示最新消息
      scrollToBottom();
    }
  }
});

const strTime = (timestamp: number): string => {
  return $moment.format('YYYY-MM-DD HH:mm:ss')
}

const normalMessageStyle = (normalMessage): string => {
  if (normalMessage.fromAccount === userInfo.account) {
    return 'message outgoing'
  } else {
    return 'message incoming'
  }
}

const handleDoubleClick = (normalMessage: NormalMessage) => {
  // 从聊天室的memberList找
  publicChatRoomChatModel.memberList.forEach(member => {
    if (member.clientId === normalMessage.fromClientId) {
      let chatModel = createChatModel(member, [], [member], true)
      $mitt.emit(MittConstants.ADD_CHAT_MODEL, chatModel);
      return
    }
  })
  // todo 如果聊天室里没有找到，历史消息，用户已经离线了
}
</script>


<style scoped lang="less">
.chat-content {
  padding: 20px;
  display: flex;
  flex-direction: column;
  overflow-y: auto;

  .message {
    position: relative;
    margin: 5px 0;
    max-width: 60%;
    display: flex;
    font-size: 14px;

    .content {
      margin: 0 10px;
      padding: 10px;
      border-radius: 5px;
      position: relative;
      display: inline-block;
      white-space: pre-line;
      word-break: break-all;
      -webkit-user-select: initial !important; /* Chrome 和 Safari */
      -moz-user-select: initial !important; /* Firefox */
      -ms-user-select: initial !important; /* Internet Explorer/Edge */
      user-select: text !important;
    }

    .nick-name {
      margin: 0 10px 3px;
      color: #ADADAD;
      font-size: 12px;
    }
  }

  .incoming {
    align-self: flex-start;

    .content {
      background-color: #ffffff;
    }

    .content::before {
      content: '';
      position: absolute;
      left: -15px;
      border-width: 10px;
      border-style: solid;
      border-color: transparent #ffffff transparent transparent;
      top: 20px;
      transform: translateY(-50%);
    }
  }

  .outgoing {
    align-self: flex-end;
    display: flex;
    flex-direction: row-reverse;

    .content {
      background-color: #95EC69;
    }

    .content::selection {
      background-color: #B3D7FF;
    }

    .content::before {
      content: '';
      position: absolute;
      right: -15px;
      border-width: 10px;
      border-style: solid;
      border-color: transparent transparent transparent #95EC69;
      top: 20px;
      transform: translateY(-50%);
    }

    .head-img {
      float: right;
    }
  }

  .prompt {
    text-align: center;
    font-size: 13px;
    color: #ADADAD;
  }
}

</style>
