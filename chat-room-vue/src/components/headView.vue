<template>
  <div class="head no-copy">
    <div class="title">
      {{ headObj.title }}
      <div v-if="headObj.userCount" class="userCount">
        （{{ headObj.userCount }}）
      </div>
    </div>
    <div class="more" v-if="headObj.groupUserList">
      <svg
        @click="moreClick"
        t="1700459172354"
        class="icon"
        viewBox="0 0 1024 1024"
        version="1.1"
        xmlns="http://www.w3.org/2000/svg"
        p-id="8224"
        width="200"
        height="200"
      >
        <path
          d="M0 0h1024v1024H0z"
          fill="#000000"
          fill-opacity="0"
          p-id="8225"
        ></path>
        <path
          d="M234.666667 448a64 64 0 1 1 0 128 64 64 0 0 1 0-128z m277.333333 0a64 64 0 1 1 0 128 64 64 0 0 1 0-128z m277.333333 0a64 64 0 1 1 0 128 64 64 0 0 1 0-128z"
          fill="#333231"
          p-id="8226"
        ></path>
      </svg>
    </div>
    <transition name="slide" appear>
      <div
        class="more-container"
        v-show="refMoreShow"
        :class="{
          'more-container-show': refMoreShow,
          'more-container-hide': !refMoreShow,
        }"
      >
        <div class="middle-search">
          <div>
            <input style="width: 200px" type="text" placeholder="🔍搜索" />
          </div>
        </div>
        <ul>
          <li
            :tabindex="index"
            v-for="(item, index) in headObj.groupUserList"
            key="index"
          >
            <div class="avatar">
              <img class="avatar" :src="item.avatar" />
            </div>
            <div class="nick-name">{{ item.nickName }}</div>
          </li>
        </ul>
      </div>
    </transition>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
const props = defineProps({
  headObj: {  
    type: Object,
    default: {},
  },
});
console.log(props.headObj)
const refMoreShow = ref(false)
const moreClick = () => {
  refMoreShow.value = !refMoreShow.value;
};
</script>

<style lang="scss" scoped>
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
.more-container-show {
  display: block;
  animation: slideInFromRight 0.5s forwards;
}
.more-container-hide {
  display: block;
  animation: slideOutToRight 0.5s forwards;
}
.head {
  height: 60px;
  display: flex;
  border-bottom: 1px solid #ccc;
  position: relative;
  width: 100%;
  .title {
    flex: 1;
    display: flex;
    align-items: center;
    margin-left: 25px;
    .userCount {
      line-height: 0;
    }
  }
  .more {
    display: flex;
    align-items: center;
    margin-right: 16px;
  }
  .more-container {
    width: 260px;
    height: 750px;
    top: 60px;
    right: 0;
    background-color: #ffffff;
    border-top: 1px solid #ccc;
    position: absolute;
    z-index: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
    // display: none;
    .middle-search {
      height: 60px;
      background-color: #ffffff;
      display: flex;
      align-items: center;
      justify-content: center;
      input {
        background-color: #eaeaea;
        border: 0 solid #eaeaea;
        padding: 5px;
      }
    }
    ul {
      display: flex;
      flex-wrap: wrap;
      border-bottom: 1px solid #ccc;
      width: 240px;
      max-height: 689px;
      overflow-y: auto;
    }
    li {
      margin-bottom: 4px;
      font-size: 12px;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      white-space: nowrap; /* 不换行 */
      overflow: hidden; /* 溢出隐藏 */
      text-overflow: ellipsis; /* 文本省略号 */
      padding: 5px;
      .nick-name {
        width: 50px;
        margin-top: 7px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        text-align: center;
      }
      .avatar {
        width: 30px !important;
        height: 30px !important;
      }
    }

    li:focus {
      background-color: #e1e1e1;
    }
  }
}
</style>
