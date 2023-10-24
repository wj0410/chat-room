package io.github.wj0410.chatroom.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjie
 * @date 2023/10/23
 */
public class ChatUtils {

    public static String getRemotePort(String remoteAddress) {
        return remoteAddress.split(":")[1];
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }


}
