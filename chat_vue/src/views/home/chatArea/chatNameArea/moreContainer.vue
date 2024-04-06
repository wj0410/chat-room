<template>
  <transition name="slide" appear>
    <div class="more-container no-copy" v-show="showMoreContainer" @click.stop=""
         :class="{'more-container-show': showMoreContainer,'more-container-hide':!showMoreContainer}">
      <ul>
        <li tabindex="0" v-for="client in chatModel.memberList" key="client.account"
            @dblclick="handleDoubleClick(client)">
          <div class="avatar"><img class="avatar"
                                   :src="client.avatar"/>
          </div>
          <div class="nick-name">{{ client.nickName }}</div>
        </li>
      </ul>
    </div>
  </transition>
</template>

<script lang="ts" setup>
import {defineProps, reactive} from "vue";
import {MittConstants} from "@/constant/constants";
import ChatModel, {createChatModel} from "../../../../model/ChatModel";
import {$mitt} from "../../../../utils/MyCommon";
import {ChatType, ClientOrigin, ClientType, MessageContainerType} from "../../../../constant/Enum";
import {NormalMessage} from "../../../../model/NormalMessage";
import ClientModel from "../../../../model/ClientModel";

const props = defineProps({
  showMoreContainer: {
    type: Boolean,
    default: false
  },
  chatModel: {
    type: Object,
    default: []
  }
})

const handleDoubleClick = (client: ClientModel) => {
  // 新增chatModel
  let chatModel = createChatModel(client,[],[client],true)
  $mitt.emit(MittConstants.ADD_CHAT_MODEL, chatModel);
  $mitt.emit(MittConstants.SHOW_MORE_CONTAINER, false);
}

</script>

<style scoped lang="less">
@keyframes slideInFromRight {
  from {
    transform: translateX(100%);
  }
  to {
    transform: translateX(0);
  }
}

@keyframes slideOutToRight {
  from {
    transform: translateX(0);
  }
  to {
    transform: translateX(100%);
  }
}

.more-container {
  max-height: 720px;
  height: 720px;
  width: 235px;
  padding: 15px;
  background-color: #FFFFFF;
  position: absolute;
  right: 0;
  top: 60px;
  z-index: 1;
  border-top: 1px solid #ccc;
  border-left: 1px solid #EBEBEB;

  ul {
    display: flex;
    flex-wrap: wrap;
    border-bottom: 1px solid #ccc;
    padding: 10px;
  }

  li {
    padding: 4px;
    margin-bottom: 4px;
    font-size: 12px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    white-space: nowrap; /* 不换行 */
    overflow: hidden; /* 溢出隐藏 */
    text-overflow: ellipsis; /* 文本省略号 */

    .avatar {
      width: 30px !important;
      height: 30px !important;
    }

    .nick-name {
      width: 45px;
      margin-top: 7px;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
      text-align: center;
    }
  }

  li:focus {
    background-color: #e1e1e1;
  }
}

.more-container-show {
  display: block;
  animation: slideInFromRight 0.5s forwards;
}

.more-container-hide {
  display: block;
  animation: slideOutToRight 0.5s forwards;
}

</style>
