package io.github.wj0410.chatroom.client.util;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.ui.swing.model.OnlineModel;
import io.github.wj0410.chatroom.common.constant.CommonConstants;
import io.github.wj0410.chatroom.common.enums.ChatType;
import io.github.wj0410.chatroom.common.message.BindMessage;
import io.github.wj0410.chatroom.common.message.NormalMessage;
import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.common.model.MessageContainer;
import io.github.wj0410.chatroom.common.util.ImageUtil;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.github.wj0410.chatroom.common.util.SwingUIUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.imageio.*;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
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
     * @param clientModel
     * @param bindMessage
     */
    public static void sendBindMessage(ClientModel clientModel, BindMessage bindMessage) {
        String bindMessageJsonStr = MessageUtil.createBindMessageJsonStr(bindMessage);
        clientModel.writeAndFlush(bindMessageJsonStr);
        log.info("客户端向服务端发送绑定ChannelId请求，{}", bindMessageJsonStr);
    }

    /**
     * 给服务端发送普通消息
     *
     * @param clientModel
     * @param msg
     * @param chatType
     * @param targetClientList
     * @return
     */
    public static NormalMessage sendNormalMessage(ClientModel clientModel, List<MessageContainer> msg, ChatType chatType, List<String> targetClientList) {
        NormalMessage message = new NormalMessage();
        message.setChatType(chatType);
        message.setMsg(msg);
        message.setFromAccount(ClientHolder.clientInfo.getAccount());
        message.setFromClientId(ClientHolder.clientInfo.getClientId());
        message.setFromUserName(ClientHolder.clientInfo.getUserName());
        message.setTargetClientIds(targetClientList);
        String normalMessageJsonStr = MessageUtil.createNormalMessageJsonStr(message);
        clientModel.writeAndFlush(normalMessageJsonStr);
        return message;
    }

    public static List<MessageContainer> processSendTextPane(JTextPane textPane) {
        StyledDocument doc = textPane.getStyledDocument();
        ElementIterator iterator = new ElementIterator(doc);
        Element element;
        List<MessageContainer> containerList = new ArrayList<>();
        while ((element = iterator.next()) != null) {
            if (!element.getName().equals("content") && !element.getName().equals("icon")) {
                continue;
            }
            AttributeSet attributes = element.getAttributes();
            Object obj = attributes.getAttribute(StyleConstants.IconAttribute);
            // 发送的图片
            if (obj instanceof Icon) {
                Icon icon = (Icon) obj;
                if (icon instanceof ImageIcon) {
                    ImageIcon imageIcon = (ImageIcon) icon;
                    // 先缩放图片
                    Image image = ImageUtil.getScaledIcon(imageIcon, SwingUIUtil.IMAGE_MAX_WIDTH,SwingUIUtil.IMAGE_MAX_HEIGHT).getImage();
                    // 压缩图片
                    byte[] imageData = ImageUtil.compressImage(image, ImageUtil.getImageFormatName(image), 0.9f);
                    containerList.add(new MessageContainer(imageData));
                }
            } else {
                try {
                    String text = doc.getText(element.getStartOffset(), element.getEndOffset() - element.getStartOffset()).trim();
                    if (StringUtils.isNotBlank(text) && !text.contains(CommonConstants.PLACE_HOLDER_IMAGE)) {
                        containerList.add(new MessageContainer(text));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return containerList;
    }


}
