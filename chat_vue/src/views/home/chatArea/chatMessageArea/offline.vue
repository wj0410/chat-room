<template>
  <div class="offline" v-if="offlineShow">
    <svg class="warn icon" aria-hidden="true">
      <use xlink:href="#icon-gantanhao"></use>
    </svg>
    <span>当前网络不可用，需检查你的网络设置</span>
  </div>
</template>

<script lang="ts" setup>
import {ref} from "vue";
import {$mitt} from "@/utils/MyCommon";
import {setAlive} from "@/utils/offline";
import {MittConstants} from "../../../../constant/constants";
const offlineShow = ref(false);
setAlive()
// 订阅消息
$mitt.on(MittConstants.SHOW_OFFLINE, (data:boolean) => {
  offlineShow.value = data
});
</script>


<style scoped lang="less">
.offline {
  background-color: #F8E8E8;
  font-size: 12px;
  color: #817374;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 5px;
  width: 100%;
  position: absolute;
  height: 30px;
  line-height: 1;

  .warn {
    margin-right: 10px;
  }
  svg{
    width: 18px;
    height: 18px;
  }
}

</style>
