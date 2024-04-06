<template>
  <ul>
    <li v-for="item in emoticons" :key="item" @click="chooseEmoji(item)">
      {{ item }}
    </li>
  </ul>
</template>

<script lang="ts" setup>
import {MittConstants} from "@/constant/constants";
import {$mitt} from "@/utils/MyCommon";

const emoticons = [
  "😀", "😃", "😄", "😁", "😆", "😅", "😉", "😊", "😇", "😍",
  "😘", "😗", "😙", "😚", "😋", "😛", "😜", "🤪", "😝", "🤑",
  "🤗", "🤭", "🤫", "🤔", "🤐", "🤨", "😐", "😑", "😶", "😏",
  "😒", "🙄", "😬", "🤥", "🤢", "🤮", "🤯", "😷", "🥵", "🥶",
  "🥴", "😵", "🤯", "🤠", "🥳", "🥺", "🤤", "🥱", "🤧", "🤡",
  "🤠", "🤕", "🥰", "🥲", "🥺", "🤤", "🥵", "🥶", "🥴", "🥳",
  "🤩", "😎", "🤓", "🧐", "😕", "😟", "🙁", "😮", "😯", "😲",
  "😳", "🥱", "😴", "🤤", "😪", "🥴", "🥵", "🥶", "🤢", "🤮",
  "🤯", "😷", "🥺", "🤧", "🤠", "🤕", "😷", "🤖", "🥸", "🤡",
  "👽", "🤯", "🤑", "🥺", "🧔", "👦", "👨", "👴", "👩", "👵"
]

const chooseEmoji = (item: string) => {
  $mitt.emit(MittConstants.APPEND_SEND_TEXT,item);
  $mitt.emit(MittConstants.HIDE_EMOJI)
}
</script>


<style scoped lang="less">
ul {
  display: flex;
  flex-wrap: wrap;
}

li {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 42px;
  width: 42px;
  border-radius: 5px;
  margin: 1.5px 0;
  line-height: 0;
  font-size: 23px;
}

li:hover {
  background-color: #E5E5E5;
}
</style>
