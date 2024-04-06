<template>
  <div class="new-message" v-if="newMessageShow" @click="newMessageShowClick">
    <svg t="1700206635508" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="2462" width="200" height="200">
      <path
          d="M500.408996 544.70045c3.684558 3.684558 8.674063 5.757121 13.893853 5.757121 5.21979 0 10.209295-2.072564 13.893853-5.757121L717.567616 355.406297c7.676162-7.676162 7.676162-20.111544 0-27.787706-7.676162-7.676162-20.111544-7.676162-27.787706 0L514.302849 503.018891 333.682759 322.398801c-7.676162-7.676162-20.111544-7.676162-27.787707 0-7.676162 7.676162-7.676162 20.111544 0 27.787706l194.513944 194.513943z m189.370914-59.874063L514.302849 660.303448 333.682759 479.606597c-7.676162-7.676162-20.111544-7.676162-27.787707 0-7.676162 7.676162-7.676162 20.111544 0 27.787706l194.513944 194.513943c3.684558 3.684558 8.674063 5.757121 13.893853 5.757121 5.21979 0 10.209295-2.072564 13.893853-5.757121l189.370914-189.370915c4.989505-4.989505 6.908546-12.205097 5.066267-18.96012-1.842279-6.755022-7.138831-12.051574-13.893853-13.893853-6.755022-1.765517-13.970615 0.153523-18.96012 5.143029z m0 0"
          fill="#03C160" p-id="2463"></path>
    </svg>
<!--    <svg class="icon" aria-hidden="true">-->
<!--      <use xlink:href="#icon-xiangxia"></use>-->
<!--    </svg>-->

    <span>{{ count }}</span>条新消息
  </div>
</template>

<script lang="ts" setup>
import {ref} from "vue";
import {$mitt, $moment} from "@/utils/MyCommon.ts";
import {MittConstants} from "../../../../constant/constants";

const newMessageShow = ref(false);
const count = ref(0);
// 订阅消息
$mitt.on(MittConstants.SHOW_NEW_MESSAGE, (data: { show: true, count: number }) => {
  newMessageShow.value = data.show
  if(data.count){
    count.value = data.count
  }
});
const newMessageShowClick = () => {
  // 滚动条滚到最下方
  $mitt.emit(MittConstants.SCROLL_TO_BOTTOM);
}
</script>


<style scoped lang="less">
.new-message {
  line-height: 0;
  height: 25px;
  background: #ffffff;
  width: 110px;
  font-size: 13px;
  border-top-right-radius: 25px;
  border-bottom-right-radius: 25px;
  border-top-left-radius: 25px;
  border-bottom-left-radius: 25px;
  position: absolute;
  right: 10px;
  bottom: 10px;
  color: #03C160;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
}
.new-message svg{
  height: 23px;
  width: 23px;
}
</style>
