import io.github.wj0410.chatroom.client.ui.swing.LoginUI;
import io.github.wj0410.chatroom.server.ui.AbstractServerUI;
import io.github.wj0410.chatroom.server.ui.swing.SwingUI;

import java.util.Random;

/**
 * @author wangjie
 * @date 2023/10/27
 */
public class Test {
    public static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static void main(String[] args) {
        // 服务端启动
        AbstractServerUI serverUI = new SwingUI();
        serverUI.run();
        // 客户端启动
        LoginUI loginUI = new LoginUI();
        loginUI.getAddress().setText("127.0.0.1:5678");
        loginUI.account.setText(randomAccount());
        loginUI.doLogin();
    }

    private static String randomAccount() {
        int length = 10;
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(rnd.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
