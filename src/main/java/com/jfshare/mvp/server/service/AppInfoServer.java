package com.jfshare.mvp.server.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.dao.AppInfoDao;
import com.jfshare.mvp.server.model.GetUpgradeParamStr;
import com.jfshare.mvp.server.model.TbAppVerinfo;
import com.jfshare.mvp.server.utils.MessageUtil;

@Service
public class AppInfoServer {
	@Autowired
	private AppInfoDao appInfoDao;
	
	/*
	 *  app版本升级
	 */
	public boolean updateAppVerinfo(int appType, String version, String url, String upgradeDesc) throws Exception{
		if (appType == 1 || appType == 3) {
			GetUpgradeParamStr getUpgradeParamStr = new GetUpgradeParamStr();
			getUpgradeParamStr.setAppType(appType);
			getUpgradeParamStr.setVersion(version);
			// 根据前端传递的参数获取数据库对应的信息
			List<TbAppVerinfo> appVerinfo = appInfoDao.getAppVerinfo(getUpgradeParamStr);
			if (appVerinfo != null && appVerinfo.size() > 0) {
				TbAppVerinfo tbAppVerinfo = appVerinfo.get(0);
				String minVersion = tbAppVerinfo.getMinVersion();
				String maxVersion = tbAppVerinfo.getMaxVersion();
				// 普通升级
				if (MessageUtil.compareVersion(version, tbAppVerinfo.getVersion()) > 0
						&& MessageUtil.compareVersion(version, minVersion) > 0
						&& MessageUtil.compareVersion(version, maxVersion) < 0
						|| MessageUtil.compareVersion(version, tbAppVerinfo.getVersion()) == 0) {
					tbAppVerinfo.setVersion(version);
					tbAppVerinfo.setUrl(url);
					tbAppVerinfo.setUpgradeDesc(upgradeDesc);
					tbAppVerinfo.setUpgradeType(Constant.UPGRADE_TYPE_PT);
					tbAppVerinfo.setUpdateTime(new Date());
					int count = appInfoDao.updateAppVerinfo(tbAppVerinfo);
					if(count > 0) {
						return true;
					}
				}

				// 强制升级
				if (MessageUtil.compareVersion(version, minVersion) < 0) {
					String[] str = version.split("\\.");
					System.out.println(str[0]+str[1]+str[0]);
					String versionStr = str[0] + "." + str[1] + "." + str[2];
					tbAppVerinfo.setVersion(version);
					tbAppVerinfo.setMinVersion(versionStr);
					tbAppVerinfo.setUrl(url);
					tbAppVerinfo.setUpgradeDesc(upgradeDesc);
					tbAppVerinfo.setUpgradeType(Constant.UPGRADE_TYPE_QZ);
					tbAppVerinfo.setUpdateTime(new Date());
					int count = appInfoDao.updateAppVerinfo(tbAppVerinfo);
					if(count > 0) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 根据app_type获取表tb_app_verinfo的数据 
	 * appType 范围1 2 3
	 */
	public TbAppVerinfo getAppVerinfo(int appType,String version) {
		GetUpgradeParamStr param = new GetUpgradeParamStr();
		param.setAppType(appType);
		param.setVersion(version);
		List<TbAppVerinfo> tbAppVerinfos = appInfoDao.getAppVerinfo(param);
		if (tbAppVerinfos != null && tbAppVerinfos.size() > 0) {
			TbAppVerinfo info = new TbAppVerinfo();
			TbAppVerinfo up = tbAppVerinfos.get(0);
			info.setId(up.getId());
			info.setAppType(up.getAppType());
			info.setMaxVersion(up.getMaxVersion());
			info.setMinVersion(up.getMinVersion());
			info.setUpgradeType(up.getUpgradeType());
			info.setUpgradeDesc(up.getUpgradeDesc());
			info.setUrl(up.getUrl());
			info.setVersion(up.getVersion());
			info.setCreateTime(up.getCreateTime());
			info.setUpdateTime(up.getUpdateTime());
			return info;
	}
		return null;
	}
}
