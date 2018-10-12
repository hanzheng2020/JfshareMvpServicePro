package com.jfshare.mvp.server.thirdinterface;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
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

/**
 * @author fengxiang
 * @date 2018-08-15
 */
@Component
public class WeChatAppletInterface {
	private final static Logger logger = LoggerFactory.getLogger(WeChatAppletInterface.class);

	@Autowired
	private RestTemplate restTemplate;

	// @Value("${wxApplet.appId}")
	private String appId = "wx4f18b6d1ac07e907";

	// @Value("${wxApplet.appSecret}")
	private String appSecret = "d3411329c6cf685941d413cc015040a4";

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
		// if (expiryTime == null || expiryTime.before(new Date())) {
		String url = String.format(getWeChatAccessTokenFormat, appId, appSecret);
		String result = restTemplate.getForObject(url, String.class);
		accessToken = JSON.parseObject(result).getString("access_token");
		expiryTime = DateUtils.addMinutes(new Date(), 100);
		logger.info("刷新Access Token result:{}", result);
		// }
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
     * @param filePath 图片路径
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
			//ImageIO.write((BufferedImage) itemp, "jpg", f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemp;
	}
	
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
			//ImageIO.write((BufferedImage) itemp, "jpg", f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemp;
	}

}
