package com.jfshare.mvp.server.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.model.Captcha;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

@Service
public class UserService {
	//生成客服指引需要的二维码
	public Captcha getQRCode(String id) {
		Captcha captcha = new Captcha();

		ByteArrayOutputStream out = null;
		try {
			id = new String(id.getBytes("UTF-8"), "ISO-8859-1");
			out = QRCode.from(id).to(ImageType.PNG).withSize(265, 265).stream();
			byte[] retBytes = out.toByteArray();
			captcha.setId(id);
			captcha.setCaptchaBytes(retBytes);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return captcha;
	}
}
