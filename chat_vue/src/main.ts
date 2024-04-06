import {createApp} from 'vue'
import App from './App.vue'
import router from './router/'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import {createPinia} from 'pinia'
import '/src/assets/fonts/iconfont'

const app: App<Element> = createApp(App);
app.use(router)
    .use(createPinia())
    .use(ElementPlus, {
        locale: zhCn,
    })
    .mount('#app')
