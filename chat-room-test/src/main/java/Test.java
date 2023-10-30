import io.github.wj0410.chatroom.client.ui.LoginUI;
import io.github.wj0410.chatroom.server.ui.ServerUI;

import java.util.Random;

/**
 * @author wangjie
 * @date 2023/10/27
 */
public class Test {
    public static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static void main(String[] args) {
        ServerUI serverUI = new ServerUI();
        serverUI.show();
        serverUI.run();
        LoginUI loginUI = new LoginUI();
        loginUI.show();
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
