package io.github.wj0410.chatroom.common.util;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wangjie
 * @date 2023/11/6
 */
public class ImageUtil {
    private final static String[] formats = {"image/jpeg", "image/jpg", "image/png", "image/gif"};

    /**
     * 缩放图片
     *
     * @param icon
     * @return
     */
    public static ImageIcon getScaledIcon(ImageIcon icon, int maxWidth, int maxHeight) {
        // 获取插入图片的原始尺寸
        int originalWidth = icon.getIconWidth();
        int originalHeight = icon.getIconHeight();
        // 判断原始尺寸是否超过200x200
        boolean isOverMaxSize = originalWidth > maxWidth || originalHeight > maxHeight;
        if (isOverMaxSize) {
            // 计算缩放比例
            // 超过最大尺寸，进行缩放
            // 计算缩放比例
            double widthRatio = (double) maxWidth / originalWidth;
            double heightRatio = (double) maxHeight / originalHeight;
            double scaleRatio = Math.min(widthRatio, heightRatio);

            // 根据缩放比例调整图片尺寸
            int scaledWidth = (int) (originalWidth * scaleRatio);
            int scaledHeight = (int) (originalHeight * scaleRatio);
            Image scaledImage = icon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }
        return icon;
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

    /**
     * 获取图片类型
     *
     * @param image
     * @return
     */
    public static String getImageFormatName(Image image) {
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
                    writer.write(null, new IIOImage(ImageUtil.toBufferedImage(image), null, null), writeParam);
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
        return formatName;
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
}
