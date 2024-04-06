import {MyWebSocket} from "./MyWebSocket";
import {HeartBeatConstants} from "../constant/constants";

export class HeartBeat {
    private websocket: MyWebSocket;
    private heartBeatIntervalId;
    private timeoutId;

    constructor(websocket: MyWebSocket) {
        this.websocket = websocket;
    }

    public sendHeartBeat() {
        let websocket = this.websocket
        if (this.heartBeatIntervalId) {
            clearInterval(this.heartBeatIntervalId);
        }
        this.heartBeatIntervalId = setInterval(() => {
            if (websocket.readyState === WebSocket.OPEN) {
                websocket.send(HeartBeatConstants.HEART_BEAT);
                // 设置服务器超时时间
                this.timeoutId = setTimeout(function () {
                    console.log('Server timeout, closing WebSocket connection...');
                    websocket.close();
                }, HeartBeatConstants.SERVER_TIMEOUT);
            }
        }, HeartBeatConstants.HEARTBEAT_INTERVAL);
    }

    public clearTimeout() {
        clearTimeout(this.timeoutId);
    }

    public destroy() {
        if (this.heartBeatIntervalId) {
            clearInterval(this.heartBeatIntervalId)
        }
        if (this.timeoutId) {
            clearTimeout(this.timeoutId);
        }
    }
}