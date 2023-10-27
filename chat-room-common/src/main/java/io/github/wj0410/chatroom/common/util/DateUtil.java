package io.github.wj0410.chatroom.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjie
 * @date 2023/10/27
 */
public class DateUtil {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String convertTimestampToString(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        return sdf.format(date);
    }
}
