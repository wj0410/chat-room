<template>
  <div class="chat-name-area no-copy">
    <div class="chat-name">
      <div>{{ chatModel.chatClient.nickName }}</div>
    </div>
    <div class="chat-name-more">
      <svg class="icon" aria-hidden="true" @click.stop="moreClick()">
        <use xlink:href="#icon-gengduo-xian"></use>
      </svg>
    </div>
    <MoreContainer :showMoreContainer="showMoreContainer"
                   :chatModel="chatModel"/>
  </div>
</template>

<script lang="ts" setup>
import MoreContainer from './chatNameArea/moreContainer'
import {defineProps, reactive, ref} from "vue";
import {$mitt} from "@/utils/MyCommon";
import {MittConstants} from "@/constant/constants";

const props = defineProps({
  chatModel: {
    type: Object,
    default: null
  }
})

const showMoreContainer = ref(false)


$mitt.on(MittConstants.SHOW_MORE_CONTAINER, (data: Boolean) => {
  showMoreContainer.value = data
});

const moreClick = () => {
  // 展示/隐藏 更多面板
  $mitt.emit(MittConstants.SHOW_MORE_CONTAINER, !showMoreContainer.value);
}
</script>


<style scoped lang="less">
/* chat-name-area */
.chat-name-area {
  height: 60px;
  display: flex;
  border-bottom: 1px solid #ccc;
  position: relative;

  .chat-name{
    flex:1;
    display: flex;
    align-items: center;
    margin-left: 25px;
  }

  .chat-name-more {
    display: flex;
    align-items: center;
    margin-right: 16px;
  }

}
</style>
