import io.github.wj0410.chatroom.client.ClientApplication;
import io.github.wj0410.chatroom.client.conf.ClientProperties;
import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.common.util.ConfigUtil;
import io.github.wj0410.chatroom.server.ServerApplication;
import io.github.wj0410.chatroom.server.ui.swing.ServerSwingUI;
import io.github.wj0410.chatroom.websocketserver.WebsocketServerApplication;

/**
 * @author wangjie
 * @date 2023/10/27
 */
public class Test {
    public static void main(String[] args) {
        // 服务端启动
        ServerApplication.startServer(new ServerSwingUI());
        // websocket服务端启动
        WebsocketServerApplication.startServer();
        // swing客户端启动
        ClientApplication.startClient();
        ClientProperties clientProperties = ConfigUtil.loadYaml("client-test.yml", ClientProperties.class);
        ClientHolder.clientProperties = clientProperties;
        ClientHolder.loginUI.getAddress().setText(clientProperties.getServer().getAddress());
        ClientHolder.loginUI.visitorLogin();
    }

}
