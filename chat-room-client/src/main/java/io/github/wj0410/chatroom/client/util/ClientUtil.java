package io.github.wj0410.chatroom.client.util;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.ui.swing.model.OnlineModel;
import io.github.wj0410.chatroom.common.constant.CommonConstants;
import io.github.wj0410.chatroom.common.enums.ChatType;
import io.github.wj0410.chatroom.common.message.BindMessage;
import io.github.wj0410.chatroom.common.message.NormalMessage;
import io.github.wj0410.chatroom.common.model.MessageContainer;
import io.github.wj0410.chatroom.common.util.MessageUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wangjie
 * @date 2023/10/26
 */
@Slf4j
public class ClientUtil {
    private final static String[] formats = {"image/jpeg", "image/jpg", "image/png", "image/gif"};

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
     * @param ctx
     * @param msg
     * @param chatType
     * @param targetClientList
     * @return
     */
    public static NormalMessage sendNormalMessage(ChannelHandlerContext ctx, List<MessageContainer> msg, ChatType chatType, List<String> targetClientList) {
        NormalMessage message = new NormalMessage();
        message.setChatType(chatType);
        message.setMsg(msg);
        message.setFromAccount(ClientHolder.clientInfo.getAccount());
        message.setFromClientId(ClientHolder.clientInfo.getClientId());
        message.setFromUserName(ClientHolder.clientInfo.getUserName());
        message.setTargetClientIds(targetClientList);
        String normalMessageJsonStr = MessageUtil.createNormalMessageJsonStr(message);
        ctx.writeAndFlush(MessageUtil.convert2ByteBuf(normalMessageJsonStr));
        return message;
    }


    public static List<MessageContainer> processTextPane(JTextPane textPane) {
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

            if (obj instanceof Icon) {
                Icon icon = (Icon) obj;
                if (icon instanceof ImageIcon) {
                    ImageIcon imageIcon = (ImageIcon) icon;
                    Image image = imageIcon.getImage();
                    // 压缩图片
                    byte[] imageData = compressImage(image, "PNG", 0.9f);
                    containerList.add(new MessageContainer(null, imageData));
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                    try {
//                        String formatName = getImageFormatName(image);
//                        if (formatName != null) {
//                            ImageIO.write(toBufferedImage(image), formatName, baos);
//                            byte[] imageData = baos.toByteArray();
//                            containerList.add(new MessageContainer(null, imageData));
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            } else {
                try {
                    String text = doc.getText(element.getStartOffset(), element.getEndOffset() - element.getStartOffset()).trim();
                    if (StringUtils.isNotBlank(text) && !text.contains(CommonConstants.PLACE_HOLDER_IMAGE)) {
                        containerList.add(new MessageContainer(text, null));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return containerList;
    }

    private static BufferedImage toBufferedImage(Image img) {
        // 如果图像是 BufferedImage，则直接返回
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        // 否则，使用 Graphics2D 将其绘制到新的 BufferedImage 中
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bimage.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return bimage;
    }

    /**
     * 获取图片类型
     *
     * @param image
     * @return
     */
    private static String getImageFormatName(Image image) {
        String formatName = null;
        // 获取 ImageWriter，并将其存储在集合中
        List<ImageWriter> writerList = new ArrayList<>();
        // 标准图像格式的 MIME 类型
        for (String format : formats) {
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByMIMEType(format);
            while (writers.hasNext()) {
                writerList.add(writers.next());
            }
        }
        // 遍历 ImageWriter 集合，尝试写入图像
        for (ImageWriter writer : writerList) {
            ImageWriteParam writeParam = writer.getDefaultWriteParam();
            if (writeParam != null) {
                try {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    writer.setOutput(new MemoryCacheImageOutputStream(outputStream));
                    writer.write(null, new IIOImage(toBufferedImage(image), null, null), writeParam);
                    // 设置输出目标后再获取格式名称
                    formatName = writer.getOriginatingProvider().getFormatNames()[0];
                    break;  // 找到支持的格式后终止循环
                } catch (IOException e) {
                    // 出错时继续尝试下一个格式
                } finally {
                    writer.dispose();  // 释放资源
                }
            }
        }
        if (formatName == null) {
            log.error("Unsupported image format");
        }
        return formatName;
    }

    /**
     * 压缩图片
     *
     * @param image              要压缩的图片
     * @param formatName         压缩后的格式，例如JPEG、PNG等）
     * @param compressionQuality 压缩质量（0.0 - 1.0之间的浮点数）。
     * @return
     * @throws IOException
     */
    public static byte[] compressImage(Image image, String formatName, float compressionQuality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageWriter writer = ImageIO.getImageWritersByFormatName(formatName).next();
        ImageWriteParam params = writer.getDefaultWriteParam();

        // 设置压缩质量
        params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        params.setCompressionQuality(compressionQuality);

        // 将压缩后的图片写入输出流
        writer.setOutput(new MemoryCacheImageOutputStream(baos));
        try {
            writer.write(null, new IIOImage(toBufferedImage(image), null, null), params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回压缩后的字节数组
        return baos.toByteArray();
    }
}
