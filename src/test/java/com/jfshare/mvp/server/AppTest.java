package com.jfshare.mvp.server;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alipay.api.domain.ClaimProgress;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.service.ProductService;
import com.jfshare.mvp.server.thirdinterface.AliPayInterface;
import com.jfshare.mvp.server.thirdinterface.WeChatAppletInterface;
import com.jfshare.mvp.server.thirdinterface.WeChatPayInterface;
import com.jfshare.mvp.server.utils.OSSUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
	@Autowired
	WeChatPayInterface wxpay;

	@Autowired
	AliPayInterface alipay;

	@Autowired
	WeChatAppletInterface weChatAppletInterface;
	
	@Autowired
	ProductService productService;
	

	@Test
	public void testUtils() {
		/*
		 * TbDirectPayProduct bean = new TbDirectPayProduct(); bean.setId(1);
		 * bean.setCreatedTime(new Date()); bean.setProductDesc("test");
		 * System.out.println(JSON.toJSONString(ConvertBeanToMapUtils.convertBeanToMap(
		 * bean)));
		 */

		// wxpay.createPrepayId("test", "123", 1, "127.0.0.1");
		// alipay.createPaySign();

		weChatAppletInterface.createProductQRCode("test");

	}

	/**
	 * 给图片添加水印、可设置水印图片旋转角度
	 * 
	 * @param iconPath
	 *            水印图片路径
	 * @param srcImgPath
	 *            源图片路径
	 * @param targerPath
	 *            目标图片路径
	 * @param degree
	 *            水印图片旋转角度
	 */
	@Test
	public void markImageByIcon() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		FileOutputStream fout = null;
		try {
			TbProduct product = productService.getProductOne("ze171113182605000723");
			String proImgStr = null;
			if(product.getImgKey().contains(",")) {
				String[] imgStr = product.getImgKey().split(",");
				proImgStr = imgStr[0];
			}else {
				proImgStr = product.getImgKey();
			}
			String productName = product.getProductName();
			String price = product.getCurPrice();
			URL url = new URL(proImgStr);
			//Image srcImg_2 = ImageIO.read(url);
			Image srcImg_2 = weChatAppletInterface.resizeByUrl(url, 1334, 750, true);
			int width_2 = srcImg_2.getWidth(null);
			int height_2 = srcImg_2.getHeight(null);
			Image srcImg = ImageIO.read(new File("D:\\hz\\222.jpg"));

			int width = srcImg.getWidth(null);
			int height = srcImg.getHeight(null);

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);

			// 得到画笔对象
			Graphics2D g = buffImg.createGraphics();

			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,
					0, null);



			byte[] bytes = weChatAppletInterface.createProductQRCode("ze181009151659000991");

			/*ImageIcon imgIcon = new ImageIcon(bytes);

			// 得到Image对象。
			Image img = imgIcon.getImage();*/
			
			//设置水印大小
			Image img = weChatAppletInterface.resize(bytes, 150, 150, true);

			int width_1 = img.getWidth(null);
			int height_1 = img.getHeight(null);

			float alpha = 1f; // 透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			g.setColor(new Color(220,20,60));
			Font font = new Font("微软雅黑", Font.PLAIN, 35);
			g.setFont(font);
			int x = width - width_1;
			int y = height - height_1;
			
			//设置文字水印文字
			// 表示水印图片的位置
			g.drawImage(srcImg_2, width - width_2, 0,width_2, 667,null);
			g.drawImage(img, x, y, width_1, height_1, null);
			System.out.println(height/2+100);
			System.out.println(height/2+100);
			System.out.println(height/2+100);
			g.drawString(productName,150,height/2+100);
			g.drawString("价格：" + price + "元",150,height/2+180);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

			g.dispose();

			//os = new FileOutputStream("D:\\hz\\789.jpg");

			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
			InputStream is = new ByteArrayInputStream(os.toByteArray());
			OSSUtils.uploadFile2OssForTemp(is,"123.jpg");
			URL imgUrl = OSSUtils.getUrlFromTempDir("123.jpg");
			
			//保存数据库
			productService.addProductUrl("ze171113182605000723", imgUrl.toString());
			System.out.println(imgUrl);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os) {
					os.close();
				}
				if (null != fout) {
					fout.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
