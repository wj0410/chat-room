<template>
  <div class="chat-send-tool-left">
    <ul>
      <li>
        <svg class="icon" aria-hidden="true" @click.stop="showEmoji=!showEmoji">
          <use xlink:href="#icon-biaoqing"></use>
        </svg>
        <Emoji :showEmoji="showEmoji"/>
      </li>
      <li>
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-wenjianjia"></use>
        </svg>
      </li>
      <li>
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-xiaoxi1"></use>
        </svg>
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import Emoji from './emoji/emoji'
import {ref} from "vue";
import {MittConstants} from "@/constant/constants";
import {$mitt} from "@/utils/MyCommon";
const showEmoji = ref(false)

$mitt.on(MittConstants.HIDE_EMOJI, (data) => {
  showEmoji.value = false
});

</script>


<style scoped lang="less">
.chat-send-tool-left{
  display: flex;
  flex: 1;

  li {
    display: inline-block;
    cursor: pointer;
    margin: 15px 0 15px 15px;
  }
}
</style>
