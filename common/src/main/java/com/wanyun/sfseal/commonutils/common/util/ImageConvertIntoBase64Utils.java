package com.wanyun.sfseal.commonutils.common.util;

import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import javax.imageio.ImageIO;

/**
 * @author WanYun
 */
public class ImageConvertIntoBase64Utils {

    /**
     * 将网络图片进行Base64位编码
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param imageUrl 图片的url路径，如http://.....xx.jpg
     * @return
     */
    public static String encodeImageToBase64(URL imageUrl) throws WanYunBusinessException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            BufferedImage bufferedImage = ImageIO.read(imageUrl);
            ImageIO.write(bufferedImage, "jpg", outputStream);
            // 返回Base64编码过的字节数组字符串
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            throw new WanYunBusinessException(e.getMessage());
        }
    }

    /**
     * 将本地图片进行Base64位编码
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param imageFile 图片文件
     * @return
     */
    public static String encodeImageToBase64(File imageFile) throws WanYunBusinessException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            ImageIO.write(bufferedImage, "jpg", outputStream);
            // 返回Base64编码过的字节数组字符串
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            throw new WanYunBusinessException(e.getMessage());
        }
    }

    /**
     * 将本地图片进行Base64位编码
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param imageMultipartFile 图片文件
     * @return
     */
    public static String encodeImageToBase64(MultipartFile imageMultipartFile) throws WanYunBusinessException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            BufferedImage bufferedImage = ImageIO.read(imageMultipartFile.getInputStream());
            ImageIO.write(bufferedImage, "jpg", outputStream);
            // 返回Base64编码过的字节数组字符串
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            throw new WanYunBusinessException(e.getMessage());
        }
    }

    /**
     * 将Base64位编码的图片进行解码，并保存到指定目录
     *
     * @param base64  base64编码的图片信息
     * @param path    图片保存的路径
     * @param imgName 图片名称
     * @return
     */
    public static void decodeBase64ToImage(String base64, String path, String imgName) throws WanYunBusinessException {
        try (FileOutputStream write = new FileOutputStream(new File(path + imgName))) {
            byte[] decoderBytes = Base64.getDecoder().decode(base64);
            write.write(decoderBytes);
        } catch (IOException e) {
            throw new WanYunBusinessException(e.getMessage());
        }
    }
}
