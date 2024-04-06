import {HeartBeat} from "./HeartBeat";

export class MyWebSocket extends WebSocket {
    public heartBeat: HeartBeat;

    // 创建心跳
    public createHeartBeat(): void {
        this.heartBeat = new HeartBeat(this)
    }

    // 销毁心跳
    public destroyHeartBeat() {
        if (this.heartBeat) {
            this.heartBeat.destroy();
        }
    }
}