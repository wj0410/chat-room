<template>
  <div class="chat-list">
    <ContextMenu v-if="showMenu" :menuProps="menuProps" @clickMenuItem="clickMenuItem"/>
    <ul>
      <template v-for="chatMode in chatModelList" key="chatMode.chatClient.clientId">
        <li v-if="chatMode.show"
            :class="{'li-self': isSelected(chatMode),'li-right-click':isRightClick(chatMode)}"
            @click="selectItem(chatMode)"
            @contextmenu.prevent="showContextMenu($event,chatMode)">
          <div class="avatar">
            <img class="avatar" :class="{ 'offline': !chatMode.online }"
                 :src="chatMode.chatClient.avatar?chatMode.chatClient.avatar:WEB_FILE_URL+'/images/default.png'"/>
            <div v-if="chatMode.unreadCount > 0 && chatMode.notice" class="unread-count">
              {{ chatMode.unreadCount }}
            </div>
            <div v-if="chatMode.unreadCount > 0 && !chatMode.notice" class="unread-notice"></div>
          </div>
          <div class="info">
            <h3>{{ chatMode.chatClient.nickName }}</h3>
            <div>
              <span>{{ getLatestMessageSummary(chatMode) }}</span>
              <svg v-if="!chatMode.notice" class="icon" aria-hidden="true">
                <use xlink:href="#icon-xiaoximiandarao"></use>
              </svg>
            </div>
          </div>
        </li>
      </template>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import {MessageContainer} from "../../../model/MessageContainer";
import ContextMenu from "@/components/ContextMenu";

const WEB_FILE_URL = import.meta.env.VITE_WEB_FILE_URL
import {MittConstants} from "../../../constant/constants";
import {computed, defineProps, onMounted, reactive, ref} from "vue";
import {$mitt} from "@/utils/MyCommon.ts";
import ChatModel from "../../../model/ChatModel";
import {Message} from "../../../model/Message";
import {ChatType, MessageContainerType, MessageType} from "../../../constant/Enum";
import {NormalMessage} from "../../../model/NormalMessage";
import {getChatClientIdByMessage, publicChatRoomChatModel} from "../../../utils/message";

const showMenu = ref(false)
const rightClickChatModel = ref(null)

const computedMenuList = () => {
  const readMenuObj = {
    name: rightClickChatModel.value && rightClickChatModel.value.unreadCount > 0 ? '标为已读' : '标为未读',
    callback: () => {
      setUnreadCountStatus(rightClickChatModel.value)
    },
  }
  const noticeMenuObj = {
    name: rightClickChatModel.value && rightClickChatModel.value.notice ? '消息免打扰' : '允许消息通知',
    callback: () => {
      setNoticeStatus(rightClickChatModel.value)
    },
    underline: true
  }
  const hideChatModelMenuObj = {
    name: '不显示',
    callback: () => {
      hideChatModel(rightClickChatModel.value)
    },
    underline: true
  }
  const deleteChatModelMenuObj = {
    name: '删除',
    callback: () => {
      deleteChatModel(rightClickChatModel.value)
    }
  }
  const menuList = [];
  // 如果是聊天室，不展示不显示和删除按钮
  if (rightClickChatModel.value && rightClickChatModel.value.chatClient.clientId === publicChatRoomChatModel.chatClient.clientId) {
    noticeMenuObj.underline = false
    menuList.push(readMenuObj, noticeMenuObj)
  } else {
    noticeMenuObj.underline = true
    menuList.push(readMenuObj, noticeMenuObj, hideChatModelMenuObj, deleteChatModelMenuObj)
  }
  return menuList;
}

let menuProps = reactive({
  menuList: computedMenuList(),
  x: 0,
  y: 0
})

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

const emit = defineEmits(["setSelectedChatModel", "setChatModelList"])

const updateChatModelList = (chatModelList: Array<ChatModel>) => {
  emit("setChatModelList", chatModelList)
};

// 点击聊天列表项
const selectItem = (chatModel: ChatModel) => {
  if (isSelected(chatModel)) {
    return;
  }
  // 更新父组件的selectedChatModel
  updateSelectedChatModel(chatModel)
  // 将未读消息设置为0
  resetUnreadCount(chatModel.chatClient.clientId)
  // 滚动条到最后
  $mitt.emit(MittConstants.SCROLL_TO_BOTTOM);
};

const resetUnreadCount = (clientId: string) => {
  props.chatModelList.forEach(chatModel => {
    if (chatModel.chatClient.clientId === clientId) {
      chatModel.unreadCount = 0
      updateChatModelList(props.chatModelList)
      return
    }
  })
};

const updateSelectedChatModel = (chatModel: ChatModel) => {
  emit("setSelectedChatModel", chatModel)
};

const isSelected = (chatModel: ChatModel) => {
  return props.selectedChatModel && chatModel.chatClient.clientId === props.selectedChatModel.chatClient.clientId;
};
const isRightClick = (chatModel: ChatModel) => {
  return rightClickChatModel.value && chatModel.chatClient.clientId === rightClickChatModel.value.chatClient.clientId;
};

// 获取最新消息摘要
const getLatestMessageSummary = (chatModel: ChatModel) => {
  if (chatModel.chatMessageList && chatModel.chatMessageList.length > 0) {
    let normalMessage = getLastNormalMessage(chatModel.chatMessageList)
    if (normalMessage) {
      let str = ''
      if (normalMessage.chatType === ChatType.PUBLIC) {
        str = normalMessage.fromNickName + "："
      }
      let msgList = normalMessage.msgList;
      let msg = msgList[msgList.length - 1];

      if (msg.type === MessageContainerType.TEXT) {
        str += msg.text
      } else if (msg.type === MessageContainerType.IMAGE) {
        str += '[图片]'
      }
      return str
    } else {
      return ''
    }
  }
  return ''
}

const getLastNormalMessage = (chatMessageList: Array<Message>) => {
  if (chatMessageList.length === 0) {
    return null
  }
  let message = chatMessageList[chatMessageList.length - 1];
  if (message.type === MessageType.NORMAL) {
    return message.data
  } else {
    return getLastNormalMessage(chatMessageList.slice(0, chatMessageList.length - 1))
  }
}

const showContextMenu = (event, chatMode: ChatModel) => {
  event.preventDefault(); // 阻止默认的右键菜单
  menuProps.x = event.clientX;
  menuProps.y = event.clientY;
  showMenu.value = true;
  rightClickChatModel.value = chatMode
  menuProps.menuList = computedMenuList()
}
$mitt.on(MittConstants.HIDE_CHAT_LIST_MENU, () => {
  hideChatListMenu()
});
const clickMenuItem = (menu) => {
  menu.callback()
  hideChatListMenu()
};
const hideChatListMenu = () => {
  showMenu.value = false
  rightClickChatModel.value = null
}

const setUnreadCountStatus = (chatModel: ChatModel) => {
  if (chatModel.unreadCount > 0) {
    chatModel.unreadCount = 0
  } else {
    chatModel.unreadCount = 1
  }
  // 更新父组件的chatModelList
  updateChatModelList(props.chatModelList)
}
const setNoticeStatus = (chatModel: ChatModel) => {
  chatModel.notice = !chatModel.notice
  // 更新父组件的chatModelList
  updateChatModelList(props.chatModelList)
}
// 不显示
const hideChatModel = (chatModel: ChatModel) => {
  chatModel.show = false
  // 更新父组件的chatModelList
  updateChatModelList(props.chatModelList)
  // 如果被选中了，则清空选中
  if (isSelected(chatModel)) {
    updateSelectedChatModel(null)
  }

}
// 删除
const deleteChatModel = (chatModel: ChatModel) => {
  // 将props.chatModelList里的chatModel删除
  for (let i = 0; i < props.chatModelList.length; i++) {
    if (props.chatModelList[i].chatClient.clientId === chatModel.chatClient.clientId) {
      props.chatModelList.splice(i, 1)
      break
    }
  }
  updateChatModelList(props.chatModelList)
  // 如果被选中了，则清空选中
  if (isSelected(chatModel)) {
    updateSelectedChatModel(null)
  }
}
</script>


<style scoped lang="less">

.chat-list {
  flex: 9.25;
  overflow-y: auto;

  .avatar {
    position: relative;

    .unread-count {
      position: absolute;
      top: -10px;
      left: 25px;
      width: 18px;
      height: 18px;
      line-height: 18px;
      text-align: center;
      font-size: 11px;
      color: #ffffff;
      background-color: #FA5151; /* 设置背景颜色 */
      border-radius: 50%; /* 将元素设置为圆形 */
    }

    .unread-notice {
      position: absolute;
      top: -4px;
      left: 32px;
      width: 7px;
      height: 7px;
      background-color: #FA5151; /* 设置背景颜色 */
      border-radius: 50%; /* 将元素设置为圆形 */
    }
  }

  ul {
    list-style: none;
    padding: 0;
    margin: 0;
  }

  li {
    margin: 0;
    display: flex;
    padding: 14px 10px;
    cursor: pointer;
    border: 2px solid #F7F7F7;

    .info {
      line-height: 1;
      font-size: 12px;
      margin-left: 10px;
      display: flex;
      flex-direction: column;

      h3 {
        margin: 0;
        flex: 1;
      }

      div {
        color: #ADADAD;
        width: 210px;
        max-width: 240px;
        min-width: 210px;
        display: flex;
        align-items: flex-end;
        font-size: 13px;

        span {
          flex: 1;
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
        }

        svg {
          width: 13px;
          height: 13px;
        }
      }
    }
  }

  .li-self {
    background-color: #d3d3d3;
    border: 2px solid #d3d3d3;
  }

  .li-right-click {
    border: 2px solid #03A451;
  }
}
</style>
