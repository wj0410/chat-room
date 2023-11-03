package io.github.wj0410.chatroom.client.tray;


import java.awt.*;
import java.awt.image.BufferedImage;

public class ChatTrayIcon {
    private final BufferedImage iconImage;

    public ChatTrayIcon(boolean unread) {
        // 创建一个64x64像素的BufferedImage对象
        iconImage = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        // 绘制图标和数字
        drawIcon(unread);
    }

    private void drawIcon(boolean unread) {
        Graphics2D g2d = iconImage.createGraphics();

        // 绘制微信图案
        Image wechatImage = new Favicon().getImage();
        g2d.drawImage(wechatImage, 0, 0, null);

        if (unread) {
            // 绘制未读
            g2d.setColor(Color.RED);
            // 红点的半径
            int radius = 10;
            // 将坐标调整到右上角
            int x = 64 - radius - 12;
            // 将坐标调整到垂直居中
            int y = 4 + (radius / 2);
            g2d.fillOval(x, y, radius * 2, radius * 2);
        }

        g2d.dispose();
    }

    public BufferedImage getImage() {
        return iconImage;
    }

}