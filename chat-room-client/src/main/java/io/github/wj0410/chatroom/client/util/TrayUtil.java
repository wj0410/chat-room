package io.github.wj0410.chatroom.client.util;

import io.github.wj0410.chatroom.client.tray.ChatTrayIcon;

import java.awt.*;

/**
 * @author wangjie
 * @date 2023/11/2
 */
public class TrayUtil {

    /**
     * 绘制系统托盘图标
     * TODO 这里有bug，无法区分多个图标
     */
    public static void noticeTray(boolean unread){
        SystemTray systemTray = SystemTray.getSystemTray();
        // 1.先移除旧的TrayIcon
        TrayIcon[] trayIcons = SystemTray.getSystemTray().getTrayIcons();
        for (int i = 0; i < trayIcons.length; i++) {
            systemTray.remove(trayIcons[i]);
        }
        // 2.重新生成新的TrayIcon
        ChatTrayIcon trayIcon = new ChatTrayIcon(unread);
        TrayIcon icon = new TrayIcon(trayIcon.getImage());
        try {
            systemTray.add(icon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭系统托盘
     */
    public static void closeTray(){
        SystemTray systemTray = SystemTray.getSystemTray();
        TrayIcon[] trayIcons = SystemTray.getSystemTray().getTrayIcons();
        for (int i = 0; i < trayIcons.length; i++) {
            systemTray.remove(trayIcons[i]);
        }
    }
}
