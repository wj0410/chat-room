<template>
  <div class="leftMenu">
    <div class="occupying-area"></div>
    <div class="menu-bar-area">
      <div class="menu-bar-self">
        <img class="avatar" :src="refLoginUser?.avatar" />
      </div>
      <div class="menu-bar-top">
        <ul>
          <li @click="jump('chat')">
            <svg
              t="1701672952346"
              class="icon"
              viewBox="0 0 1024 1024"
              version="1.1"
              xmlns="http://www.w3.org/2000/svg"
              p-id="1442"
              width="200"
              height="200"
            >
              <path
                d="M641.28 901.8368c-3.5328 0-17.7152 14.336-46.08 50.2784-38.912 46.7456-60.2112 71.8848-84.992 71.8848s-46.08-25.1392-81.5104-71.8848c-28.3136-35.8912-42.496-50.2784-46.08-50.2784C155.9552 858.7264 0 679.1168 0 456.2944 0 201.216 230.2976 0 513.7408 0 797.184 0 1027.4816 201.216 1024 456.2944c0 222.7712-152.32 402.432-382.6688 445.5424zM307.2 537.6a51.2 51.2 0 1 0 0-102.4 51.2 51.2 0 0 0 0 102.4z m204.8 0a51.2 51.2 0 1 0 0-102.4 51.2 51.2 0 0 0 0 102.4z m204.8 0a51.2 51.2 0 1 0 0-102.4 51.2 51.2 0 0 0 0 102.4z"
                fill="#17A915"
                p-id="1443"
              ></path>
            </svg>
          </li>
          <li @click="jump('gameCenter')">
            <svg
              t="1701672968734"
              class="icon"
              viewBox="0 0 1024 1024"
              version="1.1"
              xmlns="http://www.w3.org/2000/svg"
              p-id="1580"
              width="200"
              height="200"
            >
              <path
                d="M512 597.333333a277.333333 277.333333 0 1 1 277.333333-277.333333 277.333333 277.333333 0 0 1-277.333333 277.333333z m0-469.333333a192 192 0 1 0 192 192 192 192 0 0 0-192-192z"
                fill="#4E4E4E"
                p-id="1581"
              ></path>
              <path
                d="M512 469.333333a149.333333 149.333333 0 0 1-105.6-42.666666 42.666667 42.666667 0 0 1 60.373333-60.373334 65.706667 65.706667 0 0 0 90.453334 0A42.666667 42.666667 0 0 1 618.666667 426.666667a149.333333 149.333333 0 0 1-106.666667 42.666666z"
                fill="#FF9813"
                p-id="1582"
              ></path>
              <path
                d="M832 981.333333H192a128 128 0 0 1-125.013333-155.733333A397.866667 397.866667 0 0 1 457.6 512h108.8a397.866667 397.866667 0 0 1 390.826667 313.6A128 128 0 0 1 832 981.333333zM457.6 597.333333A313.6 313.6 0 0 0 149.333333 844.16 42.666667 42.666667 0 0 0 192 896h640a42.666667 42.666667 0 0 0 42.666667-51.84A313.6 313.6 0 0 0 566.4 597.333333z"
                fill="#4E4E4E"
                p-id="1583"
              ></path>
            </svg>
          </li>
        </ul>
      </div>
      <div class="menu-bar-bottom">
        <ul>
          <li>
            <svg
              t="1700472341210"
              class="icon"
              viewBox="0 0 1024 1024"
              version="1.1"
              xmlns="http://www.w3.org/2000/svg"
              p-id="1699"
              width="200"
              height="200"
            >
              <path
                d="M0 0h1024v1024H0z"
                fill="#000000"
                fill-opacity="0"
                p-id="1700"
              ></path>
              <path
                d="M853.333333 757.333333v64H170.666667v-64h682.666666z m0-277.333333v64H170.666667v-64h682.666666z m0-277.333333v64H170.666667v-64h682.666666z"
                fill="#333231"
                p-id="1701"
              ></path>
            </svg>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import type User from "@/model/User";
import useUserStore from "@/store/user";
import { ref } from "vue";
import { useRouter } from "vue-router";
const refLoginUser = ref<User | undefined>(undefined);
refLoginUser.value = useUserStore().loginUser;
const router = useRouter();
const jump = (path: string) => {
  router.push(path);
};
</script>
<style lang="scss" scoped>
@import "@/assets/components/leftMenu.scss";
</style>
