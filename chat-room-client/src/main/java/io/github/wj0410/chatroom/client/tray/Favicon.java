package io.github.wj0410.chatroom.client.tray;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author wangjie
 */
public class Favicon {
    private ImageIcon iconImage;

    public Favicon() {
        URL url = getClass().getResource("/images/favicon.jpg");
        if (url != null) {
            iconImage = new ImageIcon(url);
        } else {
            // 处理无法找到ICO文件的情况
            iconImage = null;
        }

    }
    public Image getImage() {
        return iconImage.getImage();
    }
}