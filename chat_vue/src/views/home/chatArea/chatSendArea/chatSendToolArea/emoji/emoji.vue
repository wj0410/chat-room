<template>
  <div class="tool-emoji-container" @click.stop="" v-if="showEmoji">
    <div class="emoji-container">
      <div class="emoji-content">
        <SystemEmoji v-if="showSystemEmoji"/>
<!--        <SystemEmoji v-if="showSystemEmoji"/>-->
      </div>
      <div class="emoji-page">
        <span class="emoji-page-dot active"></span>
        <span class="emoji-page-dot"></span>
      </div>
      <div class="emoji-icon" >
        <ul>
          <li class="choose">
            <svg t="1700469337502" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
                 p-id="30613" width="200" height="200">
              <path
                  d="M512 959.1c-114.6 0-229.1-43.6-316.3-130.9-174.3-174.4-174.2-458.1 0.2-632.4C298.7 93 445.6 46.6 589 71.5c14.3 2.5 23.9 16.1 21.4 30.4-2.5 14.3-16.1 23.9-30.4 21.4-126.5-22-256.2 19-347 109.7-153.9 153.8-154 404.2-0.2 558.1 153.8 153.9 404.2 154 558.1 0.2s154-404.2 0.2-558.2c-15.4-15.4-32-29.5-49.6-42.1-11.8-8.5-14.5-24.9-6-36.7 8.5-11.8 24.9-14.5 36.7-6 19.8 14.2 38.7 30.3 56.1 47.7 174.3 174.4 174.2 458.1-0.2 632.5-87.1 87-201.6 130.6-316.1 130.6z"
                  p-id="30614"></path>
              <path d="M312.8 410.7m-42.8 0a42.8 42.8 0 1 0 85.6 0 42.8 42.8 0 1 0-85.6 0Z" p-id="30615"></path>
              <path d="M715.6 410.9m-42.5 0a42.5 42.5 0 1 0 85 0 42.5 42.5 0 1 0-85 0Z" p-id="30616"></path>
              <path d="M281.1 553.2v0.4c0 127.5 103.3 231 230.9 231s231-103.3 231-230.9v-0.4l-461.9-0.1z" fill="#FFA786"
                    p-id="30617"></path>
              <path
                  d="M512 810.9s-0.1 0 0 0c-141.9 0-257.3-115.5-257.2-257.3 0-7.9 3.2-26.2 32.7-26.3h82.4c67.1 0 160.4 0 236.9 0.1l133.6 0.1c28.6 0.1 28.9 26 28.9 26.3-0.1 141.7-115.5 257.1-257.3 257.1zM309 579.8c12.9 100.5 99 178.5 202.9 178.5h0.1c103.9 0 190-77.9 203-178.4-82.1 0-324-0.1-406-0.1z"
                  p-id="30618"></path>
              <path d="M673.4 124.4m-26.3 0a26.3 26.3 0 1 0 52.6 0 26.3 26.3 0 1 0-52.6 0Z" fill="#FFA786"
                    p-id="30619"></path>
            </svg>
          </li>
          <li>
            <svg t="1701050127513" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
                 p-id="1409" width="200" height="200">
              <path
                  d="M512 921.6a64 64 0 0 1-40.32-14.08l-134.826667-106.666667C147.413333 649.813333 69.333333 556.373333 48.64 455.253333a290.986667 290.986667 0 0 1 52.053333-232.533333A279.466667 279.466667 0 0 1 512 183.04a278.4 278.4 0 0 1 188.16-72.32A281.6 281.6 0 0 1 981.333333 391.893333c0 131.2-71.466667 230.186667-293.973333 407.893334l-134.4 106.666666a64 64 0 0 1-40.96 15.146667z m-189.013333-725.333333h-16.213334a190.08 190.08 0 0 0-138.24 77.013333 205.013333 205.013333 0 0 0-36.266666 164.693333c13.226667 64 62.293333 140.16 257.92 295.68L512 830.506667l121.813333-97.28C832 576 896 491.946667 896 391.893333A195.84 195.84 0 0 0 558.933333 256a67.2 67.2 0 0 1-94.08 0 195.413333 195.413333 0 0 0-141.44-59.733333z"
                  fill="#4E4E4E" p-id="1410"></path>
              <path
                  d="M213.333333 480a42.666667 42.666667 0 0 1-42.666666-42.666667 202.026667 202.026667 0 0 1 153.813333-196.693333 42.666667 42.666667 0 1 1 21.333333 82.773333A117.12 117.12 0 0 0 256 437.333333a42.666667 42.666667 0 0 1-42.666667 42.666667z"
                  fill="#FF9813" p-id="1411"></path>
            </svg>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import SystemEmoji from './systemEmoji'
import {defineEmits, defineProps, ref} from "vue";
import {MittConstants} from "@/constant/constants";
import {$mitt} from "@/utils/MyCommon";

const showSystemEmoji = ref(true)
const props = defineProps({
  showEmoji: {
    type: Boolean,
    default: false
  }
})

</script>

<style scoped lang="less">
/* 工具栏 emoji */
.tool-emoji-container {
  position: relative;

  .emoji-container {
    width: 400px;
    height: 432px;
    background-color: #FFFFFF;
    position: absolute;
    right: -188px;
    bottom: 44px;
    border-radius: 10px;
    border: 1px solid #ccc; /* 边框样式 */
    box-shadow: 2px 2px 5px #ddd; /* 阴影样式 */
    display: flex;
    flex-direction: column;
    cursor: auto;

    .emoji-content {
      flex: 1;
      padding: 10px;
      overflow-y: auto;
    }

    .emoji-page {
      height: 20px;
      display: flex;
      justify-content: center;
      align-items: center;

      .emoji-page-dot {
        height: 6px;
        width: 6px;
        margin: 0 5px;
        background-color: #E5E5E5;
        border-radius: 50%;
        display: inline-block;
        transition: background-color 0.6s ease;
      }
      .emoji-page-dot.active {
        background-color: #B2B2B2;
      }
    }

    .emoji-icon {
      border-top: 1px solid #ccc;
      height: 40px;
      display: flex;
      align-items: center;

      svg {
        width: 20px;
        height: 20px;
      }

      li {
        margin: 10px;
        padding: 7px;
        line-height: 0;
        display: inline-block;
      }

      li.choose {
        background-color: #E5E5E5;
        border-radius: 5px;
      }
    }
  }
}

.tool-emoji-container::before {
  content: '';
  position: absolute;
  right: -3px;
  top: -30px;
  border-width: 15px;
  border-style: solid;
  border-color: #FFFFFF transparent transparent transparent;
  transform: translateY(-50%);
  z-index: 2;
}

.tool-emoji-container::after {
  content: '';
  position: absolute;
  right: -3px;
  top: -44px;
  border-width: 15px;
  border-style: solid;
  border-color: rgba(0, 0, 0, 0.3) transparent transparent transparent;
  z-index: 1;
}

</style>
