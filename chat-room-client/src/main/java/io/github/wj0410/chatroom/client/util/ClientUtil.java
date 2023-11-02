package io.github.wj0410.chatroom.client.util;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.ui.swing.model.OnlineModel;
import io.github.wj0410.chatroom.common.message.BindMessage;
import io.github.wj0410.chatroom.common.message.NormalMessage;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author wangjie
 * @date 2023/10/26
 */
@Slf4j
public class ClientUtil {
    /**
     * 格式化展示在线列表
     *
     * @param onlineModel
     * @return
     */
    public static String formatClientAccount(OnlineModel onlineModel) {
        return onlineModel.getUserName();
    }

    /**
     * 给服务端发送绑定消息
     *
     * @param ctx
     * @param bindMessage
     */
    public static void sendBindMessage(ChannelHandlerContext ctx, BindMessage bindMessage) {
        String bindMessageJsonStr = MessageUtil.createBindMessageJsonStr(bindMessage);
        ctx.writeAndFlush(MessageUtil.convert2ByteBuf(bindMessageJsonStr));
        log.info("客户端向服务端发送绑定ChannelId请求，{}", bindMessageJsonStr);
    }

    /**
     * 给服务端发送普通消息
     *
     * @param ctx              客户端与服务端的通道
     * @param msg              消息内容
     * @param targetClientList 为NULL则代表发送给所有客户端
     */
    public static NormalMessage sendNormalMessage(ChannelHandlerContext ctx, String msg, List<String> targetClientList) {
        NormalMessage message = new NormalMessage();
        message.setMsg(msg);
        message.setFromAccount(ClientHolder.clientInfo.getAccount());
        message.setFromClientId(ClientHolder.clientInfo.getClientId());
        message.setFromUserName(ClientHolder.clientInfo.getUserName());
        message.setTargetClientIds(targetClientList);
        String normalMessageJsonStr = MessageUtil.createNormalMessageJsonStr(message);
        ctx.writeAndFlush(MessageUtil.convert2ByteBuf(normalMessageJsonStr));
        return message;
    }


    private static ImageIcon getIcon() {
        // 从文件中读取Image对象
        URL imageUrl = ClientUtil.class.getClassLoader().getResource("images/01.jpg");
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将Image转换为Icon对象
        ImageIcon icon = new ImageIcon(image);
        // 定义目标尺寸
        int targetWidth = 150;
        int targetHeight = 150;
        // 缩放图片
        Image originalImage = icon.getImage();
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }

}
