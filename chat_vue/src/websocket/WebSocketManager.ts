import {Message} from "../model/Message";
import {NormalMessage} from "../model/NormalMessage";
import {SyncOnlineMessage} from "../model/SyncOnlineMessage";
import {PromptMessage} from "../model/PromptMessage";
import {$mitt, $store} from "../utils/MyCommon";
import {PromptType} from "../constant/Enum";
import {MyWebSocket} from "./MyWebSocket";
import {hideOffline, showOffline} from "../utils/offline";
import {HeartBeatConstants, MittConstants} from "../constant/constants";

const WEB_SOCKET_URL = import.meta.env.VITE_WEB_SOCKET_URL

class WebSocketManager {
    private retryMaxCount: number = 10;
    private sockets: MyWebSocket
    private retryCounts: number = 0;

    private createWebSocket(userInfo: UserInfo): MyWebSocket {
        let clientId = userInfo.account
        this.setClientId(userInfo, clientId)
        const url: string = `${WEB_SOCKET_URL}?clientId=${clientId}&account=${userInfo.account}&nickName=${userInfo.nickName}&avatar=${userInfo.avatar}`;
        const socket: MyWebSocket = new MyWebSocket(url);
        socket.onopen = () => {
            console.log('连接成功', new Date());
            this.resetRetryCount()
            hideOffline();
            // 创建心跳对象
            socket.createHeartBeat();
            // 发送心跳
            socket.heartBeat.sendHeartBeat()
        }

        socket.onerror = () => {
            console.log('服务器出错', new Date());
        }

        socket.onclose = () => {
            console.log('服务器关闭', new Date());
            // 显示离线
            showOffline();
            this.closeWebSocket()
            // 重连
            this.retryConnect(userInfo)
        }

        socket.onmessage = (event: MessageEvent): void => {
            if (event.data === HeartBeatConstants.HEART_BEAT_SUCCESS) {
                // 收到心跳，清除超时时间
                socket.heartBeat.clearTimeout()
                return;
            }
            let message: Message = new Message(JSON.parse(event.data))
            let realityMessage: NormalMessage | SyncOnlineMessage | PromptMessage = message.getRealityMessage()
            if (realityMessage instanceof SyncOnlineMessage) {
                // 刷新在线列表
                $mitt.emit(MittConstants.SYNC_ONLINE_MESSAGE, realityMessage);
            } else if (realityMessage instanceof PromptMessage) {
                if (realityMessage.promptType === PromptType.WELCOME) {
                    // 在聊天窗口提示欢迎消息
                    $mitt.emit(MittConstants.WELCOME_MESSAGE, realityMessage);
                } else if (realityMessage.promptType === PromptType.LEAVE) {
                    // 在聊天窗口提示离开消息
                    $mitt.emit(MittConstants.LEAVE_MESSAGE, realityMessage);
                }
            } else if (realityMessage instanceof NormalMessage) {
                $mitt.emit(MittConstants.NORMAL_MESSAGE, realityMessage);
            } else {
                console.log("未知消息类型：", realityMessage);
            }
        }
        // 将WebSocket对象存储在Map中
        this.sockets = socket
        return socket
    }

    public getWebSocket(user: UserInfo): MyWebSocket | undefined {
        if (this.sockets) {
            return this.sockets
        }
        return this.createWebSocket(user)
    }

    private closeWebSocket(): void {
        const socket = this.sockets
        if (socket) {
            socket.destroyHeartBeat()
            socket.close()
            this.sockets = null
        }
    }

    // 重试
    private retryConnect(userInfo: UserInfo) {
        if (this.getRetryCount() < this.retryMaxCount) {
            this.incrRetryCount()
            // 网络超时或其他原因导致连接关闭后，5秒后自动重新连接
            const sleep = (time) => {
                return new Promise(resolve => setTimeout(resolve, time))
            }
            sleep(HeartBeatConstants.HEARTBEAT_INTERVAL).then(() => {
                console.log('Trying to reconnect WebSocket...');
                this.createWebSocket(userInfo);
            })
        } else {
            console.log("Maximum number of retries, stop retry！")
        }
    }

    private getRetryCount(): number {
        if (this.retryCounts) {
            return this.retryCounts
        }
        return 0
    }

    private incrRetryCount() {
        this.retryCounts = this.getRetryCount() + 1
    }

    private resetRetryCount() {
        this.retryCounts = 0
    }

    private setClientId(userInfo: UserInfo, clientId: string) {
        userInfo.clientId = clientId
        localStorage.setItem('userInfo', JSON.stringify(userInfo))
    }
}

export default WebSocketManager