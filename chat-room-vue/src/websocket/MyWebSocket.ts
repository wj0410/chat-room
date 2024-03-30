import { Response, ResponseHandler } from "@/model/Response";
import type User from "@/model/User";
import useUserStore from "@/store/user";

class MyWebSocket extends WebSocket {
    constructor(loginUser: User, url: string) {
        super(url)
        loginUser.socket = this
        this.onopen = () => {
            console.log('连接成功', loginUser);
            // 绑定用户信息
            loginUser.bind()
            // 心跳
        }
        this.onerror = (e) => {
            console.log('服务器出错', e);
        }
        this.onclose = (e) => {
            console.log('断开连接', e);
        }
        this.onmessage = (event: MessageEvent): void => {
            // console.log("收到消息", JSON.parse(event.data))
            new ResponseHandler(new Response(event.data)).exec()
        }
    }

}

export default MyWebSocket