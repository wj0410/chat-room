package io.github.wj0410.chatroom.server.ui.console;

import io.github.wj0410.chatroom.server.ui.AbstractServerUI;
import lombok.extern.slf4j.Slf4j;

/**
 * 控制台启动
 *
 * @author wangjie
 * @date 2023/11/1
 */
@Slf4j
public class Console extends AbstractServerUI {

    @Override
    public void show() {
        log.info("控制台模式启动服务...");
    }

    @Override
    public int getServerPort() {
        return 5678;
    }

    @Override
    public void runCheck() {
        if (getServerPort() <= 0) {
            log.info("端口设置不正确！");
        }
    }

    @Override
    public void runSuccess() {
        this.printConsole("Server：启动Netty服务端成功，端口号:" + getServerPort());
    }

    @Override
    public void runFailed() {
        this.printConsole("Netty服务启动失败！");
    }

    @Override
    public void flushClientOnlineList() {
    }

    @Override
    public void printConsole(String data) {
        log.info(data);
    }

    @Override
    public void stopCheck() {
    }

    @Override
    public void afterStop() {
    }
}
