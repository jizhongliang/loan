package com.hwc.framework;

/**
 * Created by  on 2017/12/14.
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class testPng {

    public static void main(String[] args) throws IOException {
        byte[] data = graphicsGeneration("上上签");

        OutputStream outputStream = new FileOutputStream("D:\\test.png");
        outputStream.write(data);
        outputStream.close();
    }

    public static byte[] graphicsGeneration(String name) {
        byte[] b = null;
        int fontSize = 60; // 字体大小
        int imageX = 60; // 字的x轴
        int imageY = 75; // 字的y轴
        int imageWidth = 300;// 图片的宽度
        int imageHeight = 150;// 图片的高度
        Font font = new Font("楷体", Font.CENTER_BASELINE, fontSize);

        BufferedImage result = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = result.createGraphics();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 0));
        graphics.fillRect(0, 0, imageWidth, imageHeight);// 填充整个屏幕
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1));
        graphics.setColor(Color.BLACK);
        graphics.setFont(font);
        graphics.drawString(name, imageX, imageY);
        // 将签名图像放入这个文件夹中去,并调整大小
        graphics.drawImage(result.getScaledInstance(result.getWidth(), result.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(result, "png", out);
            b = out.toByteArray();
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
}