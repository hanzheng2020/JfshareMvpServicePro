package com.jfshare.mvp.server.thirdinterface;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.service.ProductService;
import com.jfshare.mvp.server.utils.OSSUtils;

/**
 * @author fengxiang
 * @date 2018-08-15
 */
@Component
public class WeChatAppletInterface {
	private final static Logger logger = LoggerFactory.getLogger(WeChatAppletInterface.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ProductService productService;

	final static String appId = "wxe71603074adcfb75";
	private String appSecret = "a7f64bee0e512efa895949eee3bc3a22";
	
	private String accessToken;

	private Date expiryTime;

	private static final String getWXACodeUnlimitUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";

	private static final String getWeChatAccessTokenFormat = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

	/**
	 * 获取微信token，每100分钟刷新一次
	 * 
	 * @return
	 */
	public String getAccessToken() {
		if (expiryTime == null || expiryTime.before(new Date())) {
			String url = String.format(getWeChatAccessTokenFormat, appId, appSecret);
			String result = restTemplate.getForObject(url, String.class);
			accessToken = JSON.parseObject(result).getString("access_token");
			expiryTime = DateUtils.addMinutes(new Date(), 100);
			logger.info("刷新Access Token result:{}", result);
		}
		return accessToken;
	}

	public byte[] createProductQRCode(String productId) {
		accessToken = getAccessToken();
		Map<String, Object> multiValueMap = new HashMap<String, Object>();
		multiValueMap.put("scene", productId);
		multiValueMap.put("page", "pages/index/index");// pages/index/index
		multiValueMap.put("is_hyaline", true);
		ResponseEntity<ByteArrayResource> result = restTemplate.postForEntity(getWXACodeUnlimitUrl + accessToken,
				multiValueMap, ByteArrayResource.class);

		return result.getBody().getByteArray();
	}

	 /**
     * 图片缩放
     * @param bytes 字节流
     * @param height 高度
     * @param width 宽度
     * @param bb 比例不对时是否需要补白
     */
	public Image resize(byte[] bytes, int height, int width, boolean bb) {
		Image itemp = null;
		try {
            double ratio = 0; //缩放比例  
            InputStream input = new ByteArrayInputStream(bytes); 
            BufferedImage bi = ImageIO.read(input);
            itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH); 
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue() / bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb) {
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				g.dispose();
				itemp = image;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemp;
	}
	
	 /**
     * 图片缩放
     * @param url 图片路径
     * @param height 高度
     * @param width 宽度
     * @param bb 比例不对时是否需要补白
     */
	public Image resizeByUrl(URL url, int height, int width, boolean bb) {
		Image itemp = null;
		try {
            double ratio = 0; //缩放比例  
            //InputStream input = new ByteArrayInputStream(bytes); 
            BufferedImage bi = ImageIO.read(url);
            itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH); 
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue() / bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb) {
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				g.dispose();
				itemp = image;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemp;
	}
	
	//生成并保存图片水印到数据库
	public void markImageByIcon(String productId) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			TbProduct product = productService.getProductOne(productId);
			String proImgStr = null;
			if(product.getImgKey().contains(",")) {
				String[] imgStr = product.getImgKey().split(",");
				proImgStr = imgStr[0];
			}else {
				proImgStr = product.getImgKey();
			}
			//获取图片水印的相关信息
			String productName = product.getProductName();
			String price = product.getCurPrice();
			URL url = new URL(proImgStr);
			Image srcImg_2 = this.resizeByUrl(url, 1334, 750, true);
			int width_2 = srcImg_2.getWidth(null);
			int height_2 = srcImg_2.getHeight(null);
			//获取原图片的信息
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

			byte[] bytes = this.createProductQRCode(productId);
			
			//设置水印大小
			Image img = this.resize(bytes, 150, 150, true);

			int width_1 = img.getWidth(null);
			int height_1 = img.getHeight(null);

			float alpha = 1f; // 透明度  1 表示不透明
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			g.setColor(new Color(220,20,60));
			Font font = new Font("微软雅黑", Font.PLAIN, 35);
			g.setFont(font);
			int x = width - width_1;
			int y = height - height_1;
			
			// 设置水印图片的位置
			g.drawImage(srcImg_2, width - width_2, 0,width_2, 667,null);
			g.drawImage(img, x, y, width_1, height_1, null);
			// 设置文字水印的位置
			g.drawString(productName,150,height/2+100);
			g.drawString("价格：" + price + "元",150,height/2+180);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

			g.dispose();


			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
			InputStream is = new ByteArrayInputStream(os.toByteArray());
			OSSUtils.uploadFile2OssForTemp(is,"123.jpg");
			URL imgUrl = OSSUtils.getUrlFromTempDir("123.jpg");
			
			//保存数据库
			productService.addProductUrl(productId, imgUrl.toString());
			System.out.println(imgUrl);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os) {
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
