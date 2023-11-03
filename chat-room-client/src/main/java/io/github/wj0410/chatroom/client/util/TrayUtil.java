package io.github.wj0410.chatroom.client.util;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.tray.MacChatTrayIcon;
import io.github.wj0410.chatroom.client.tray.WinChatTrayIcon;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author wangjie
 * @date 2023/11/2
 */
public class TrayUtil {

    /**
     * 绘制系统托盘图标
     */
    public static void noticeTray(boolean unread, String account) {
        SystemTray systemTray = SystemTray.getSystemTray();
        // 1.先移除旧的TrayIcon
        TrayIcon[] trayIcons = systemTray.getTrayIcons();
        for (int i = 0; i < trayIcons.length; i++) {
            systemTray.remove(trayIcons[i]);
        }
        try {
            systemTray.add(getNewTrayIcon(unread, account));
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成新的TrayIcon
     *
     * @param unread
     * @param account
     * @return
     */
    private static TrayIcon getNewTrayIcon(boolean unread, String account) {
        TrayIcon icon;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            WinChatTrayIcon trayIcon = new WinChatTrayIcon(unread);
            icon = new TrayIcon(trayIcon.getImage(), account);
        } else {
            MacChatTrayIcon trayIcon = new MacChatTrayIcon(unread);
            icon = new TrayIcon(trayIcon.getImage(), account);
        }
        // 添加鼠标事件监听器
        icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // 将窗口置于最前方
                    if (ClientHolder.chatRoomUI.getChatJFrame().getState() == Frame.ICONIFIED) {
                        ClientHolder.chatRoomUI.getChatJFrame().setExtendedState(Frame.NORMAL);
                    }
                    ClientHolder.chatRoomUI.getChatJFrame().toFront();
                    ClientHolder.chatRoomUI.getChatJFrame().requestFocus();
                    ClientHolder.chatRoomUI.getChatJFrame().setAlwaysOnTop(true);
                    noticeTray(false, account);
                }
            }
        });
        return icon;
    }

    /**
     * 关闭系统托盘
     */
    public static void closeTray() {
        SystemTray systemTray = SystemTray.getSystemTray();
        TrayIcon[] trayIcons = systemTray.getTrayIcons();
        for (int i = 0; i < trayIcons.length; i++) {
            systemTray.remove(trayIcons[i]);
        }
    }
}
