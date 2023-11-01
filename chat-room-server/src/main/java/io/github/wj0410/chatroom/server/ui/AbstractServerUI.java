package io.github.wj0410.chatroom.server.ui;

import io.github.wj0410.chatroom.server.holder.ServerHolder;
import io.github.wj0410.chatroom.server.netty.NettyServer;

/**
 * @author wangjie
 * @date 2023/11/1
 */
public abstract class AbstractServerUI {

    public AbstractServerUI() {
        ServerHolder.serverUI = this;
    }
    /**
     * 服务端启动
     */
    public void run() {
        runCheck();
        int port = getServerPort();
        if (port > 0 && ServerHolder.nettyServer == null) {
            ServerHolder.nettyServer = NettyServer.getInstance(port);
            try {
                ServerHolder.nettyServer.start();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    /**
     * 服务端停止
     */
    public void shutdown() {
        stopCheck();
        if (ServerHolder.nettyServer != null) {
            ServerHolder.nettyServer.shutDown();
        }
        afterStop();

    }
    /**
     * 展示UI
     */
    public abstract void show();
    /**
     * 获取服务启动端口
     *
     * @return
     */
    public abstract int getServerPort();
    /**
     * 启动前检查
     */
    public abstract void runCheck();
    /**
     * 服务启动成功
     */
    public abstract void runSuccess();
    /**
     * 服务启动失败
     */
    public abstract void runFailed();
    /**
     * 刷新客户端在线列表
     */
    public abstract void flushClientOnlineList();
    /**
     * 控制台输出日志
     *
     * @param data 日志内容
     */
    public abstract void printConsole(String data);
    /**
     * 停止前检查
     */
    public abstract void stopCheck();
    /**
     * 服务停止后
     */
    public abstract void afterStop();
}
