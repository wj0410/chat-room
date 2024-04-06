<template>
  <div class = "editor" >
    <quill-editor
        style="font-size:14px"
        contentType="html"
        v-model:content="message"
        :options="options"
        @textChange="textChange"

    />
  </div>
  <div class="send-area-button-container">
    <el-button type="success" @click="handleSending">发送(S)</el-button>
  </div>
</template>

<script lang="ts" setup>
import { QuillEditor } from "@vueup/vue-quill";
import "@vueup/vue-quill/dist/vue-quill.snow.css";
// import { uploadFile } from "@/api/menu";
import { openFile } from "@/utils/index"
import {onMounted, ref, watch} from "vue";
import {computed} from "@vue/reactivity";
import {ChatType, MessageContainerType, MessageType} from "../../../../constant/Enum";
import {$store} from "@/utils/MyCommon";
import WebSocketManager from "../../../../websocket/WebSocketManager";
import {MyWebSocket} from "../../../../websocket/MyWebSocket";
const userInfo: UserInfo = $store.userInfo

const message = ref('')

const options = ref({
  theme: "snow",
  bounds: document.body,
  debug: "warn",
  modules: {
    // 工具栏配置
    toolbar: {
      container: [],
      handlers:{
        image:() => {
          openFile( async (e) =>{
            const file = e.target.files[0]
            console.log("file",file)
            // let form = new FormData()
            // form.append("file", file);
            // const { data } = await uploadFile(form);
            // const url = data?.url;
            // const filePath = import.meta.env.VITE_APP_BASE_API + "/file/download?url=" + url;
            // content.value += `<img src='${filePath}' />`
          },'.jpeg , .png , .jpg')
        }
      }
    },
  },
  placeholder: "",
  readOnly: false,
});


const textChange = (a, b) => {
  // console.log("message",message);
  // console.log(a,b);

};


</script>


<style scoped lang="less">
.editor {
  flex: 1;
  padding: 0 15px;
  border: 0;
  resize: none;
  outline: none;
  background-color: #f3f3f3;
}
.send-area-button-container {
  display: flex;
  justify-content: flex-end;
  margin: 10px;
}
</style>
