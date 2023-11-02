import io.github.wj0410.chatroom.client.conf.ClientProperties;
import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.ui.swing.LoginUI;
import io.github.wj0410.chatroom.common.util.ConfigUtil;
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
        // 加载配置文件
        ClientProperties clientProperties = ConfigUtil.loadYaml("application-test.yml", ClientProperties.class);
        ClientHolder.clientProperties = clientProperties;
        LoginUI loginUI = new LoginUI();
        loginUI.getAddress().setText(clientProperties.getServer().getAddress() + ":" + clientProperties.getServer().getPort());
        loginUI.account.setText("wj");
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
