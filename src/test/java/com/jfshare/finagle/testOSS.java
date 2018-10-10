package com.jfshare.finagle;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jfshare.mvp.server.thirdinterface.WeChatAppletInterface;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testOSS {
	@Autowired
	WeChatAppletInterface weChatAppletInterface;
	
	
    /**
     * 给图片添加水印、可设置水印图片旋转角度
     * @param iconPath 水印图片路径
     * @param srcImgPath 源图片路径
     * @param targerPath 目标图片路径
     * @param degree 水印图片旋转角度
     */ 
	@Test
	public void markImageByIcon() { 
        OutputStream os = null; 
        try { 
            Image srcImg = ImageIO.read(new File("https://imgs.jfshare.com/online_pics/adminMvp/F6FJCHZ5K9P155P3T55MY0532CZJX7T0.png")); 

            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), 
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB); 

            // 得到画笔对象 
            // Graphics g= buffImg.getGraphics(); 
            Graphics2D g = buffImg.createGraphics(); 

            // 设置对线段的锯齿状边缘处理 
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR); 

            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg 
                    .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null); 

            //if (null != degree) { 
                // 设置水印旋转 
                g.rotate(Math.toRadians(-45), 
                        (double) buffImg.getWidth() / 2, (double) buffImg 
                                .getHeight() / 2); 
            //} 

            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度 
            
            byte[] bytes = weChatAppletInterface.createProductQRCode("ze181009151659000991");
            //ImageIcon imgIcon = new ImageIcon(iconPath); 
            ImageIcon imgIcon = new ImageIcon(bytes.toString());
            
            // 得到Image对象。 
            Image img = imgIcon.getImage(); 

            float alpha = 0.5f; // 透明度 
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 
                    alpha)); 

            // 表示水印图片的位置 
            g.drawImage(img, 150, 300, null); 

            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); 

            g.dispose(); 

            os = new FileOutputStream("D:\\hz"); 

            // 生成图片 
            ImageIO.write(buffImg, "JPG", os); 

            System.out.println("图片完成添加Icon印章。。。。。。"); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                if (null != os) 
                    os.close(); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        } 
    } 
} 

