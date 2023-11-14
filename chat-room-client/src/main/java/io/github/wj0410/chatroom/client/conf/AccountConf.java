package io.github.wj0410.chatroom.client.conf;

import io.github.wj0410.chatroom.common.model.ClientModel;

import java.util.HashMap;

/**
 * @author wangjie
 * @date 2023/11/2
 */
public class AccountConf {
    public static final HashMap<String, ClientModel> accountMap = new HashMap<>();
    static {
        ClientModel lkw = new ClientModel();
        lkw.setAccount("lkw");
        lkw.setNickName("廖开威");
        ClientModel zh = new ClientModel();
        zh.setAccount("zh");
        zh.setNickName("张恒");
        ClientModel pwj = new ClientModel();
        pwj.setAccount("pwj");
        pwj.setNickName("彭文杰");
        ClientModel wj = new ClientModel();
        wj.setAccount("wj");
        wj.setNickName("王杰");
        ClientModel zhouhui = new ClientModel();
        zhouhui.setAccount("zhouhui");
        zhouhui.setNickName("周慧");

        accountMap.put("lkw", lkw);
        accountMap.put("zh", zh);
        accountMap.put("pwj", pwj);
        accountMap.put("wj", wj);
        accountMap.put("zhouhui", zhouhui);
    }
}
