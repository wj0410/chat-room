package io.github.wj0410.chatroom.server.cache;


import io.github.wj0410.chatroom.common.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author anlingyi
 * @date 2020/5/29
 */
public final class UserCache {
    /**
     * key:channelId
     */
    private static final Map<String, User> USER_MAP = new ConcurrentHashMap(32);
    /**
     * key:  username
     * value: channelId
     */
    private static final Map<String, String> USERNAME_MAP = new ConcurrentHashMap(32);

    private UserCache() {

    }

    public static void add(String channelId, User user) {
        USER_MAP.put(channelId, user);
        USERNAME_MAP.put(user.getUsername(), channelId);
    }

    public static User get(String channelId) {
        return USER_MAP.get(channelId);
    }

    public static void remove(String channelId) {
        User user = USER_MAP.get(channelId);
        if (user == null) {
            return;
        }

        USER_MAP.remove(channelId);
        USERNAME_MAP.remove(user.getUsername());
    }

    public static void removeByUsername(String username) {
        String key = getKeyByUsername(username);
        if (key == null) {
            return;
        }
        USER_MAP.remove(key);
        USERNAME_MAP.remove(username);
    }

    public static User getUserByUsername(String username) {
        String key = getKeyByUsername(username);
        if (key == null) {
            return null;
        }
        return USER_MAP.get(key);
    }

    public static String getKeyByUsername(String username) {
        return USERNAME_MAP.get(username);
    }

    public static void clear() {
        USER_MAP.clear();
    }

    public static int size() {
        return USER_MAP.size();
    }

    public static Map<String, String> getUsernameMap() {
        return USERNAME_MAP;
    }

    public static boolean existUsername(String username) {
        return USERNAME_MAP.get(username) != null;
    }

    public static List<User> listUser() {
        return new ArrayList<>(USER_MAP.values());
    }

}
