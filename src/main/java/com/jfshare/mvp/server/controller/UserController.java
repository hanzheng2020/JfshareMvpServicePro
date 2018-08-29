package com.jfshare.mvp.server.controller;

import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.model.Captcha;
import com.jfshare.mvp.server.model.TbJvjindouRule;
import com.jfshare.mvp.server.model.TbLevelInfo;
import com.jfshare.mvp.server.model.TbSystemInformationExample;
import com.jfshare.mvp.server.service.JvjindouRuleService;
import com.jfshare.mvp.server.service.LevelInfoService;
import com.jfshare.mvp.server.service.SystemInformationService;
import com.jfshare.mvp.server.service.UserService;
import com.jfshare.mvp.server.utils.ConvertBeanToMapUtils;
import com.jfshare.mvp.server.utils.JedisClusterUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户管理
 * @author fengxiang
 * @date 2018-07-19
 */
@Api(value="api", tags="个人中心模块相关API")
@RestController
@RequestMapping("/user")
public class UserController {

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@Autowired
	private JvjindouRuleService jvjindouRuleService;
	@Autowired
	private UserService userService;
	@Autowired
	private LevelInfoService levelInfoService;
	@Autowired
	private SystemInformationService systemInformationService;
	@Autowired
	private JedisClusterUtils jedisCluster;
	
	@ApiOperation(value="查询用户", notes="用id来查询用户")
	@GetMapping
	public ResultConstant getUser(@RequestParam(value="id", required=true) 
	 							  String id) {
		id.split(",");
		return ResultConstant.ofSuccess();
	}
	
	@PostMapping
	public void postUser() {
		
	}
	
	@PutMapping
	public void putUser() {
		
	}
	
	@DeleteMapping
	public void deleteUser() {
		
	}
	
	@ApiOperation(value="后台商品管理", notes="添加设置赠送聚金豆规则")
	@PostMapping("/insertJvjindouRule")
	public ResultConstant insertJvjindouRule(TbJvjindouRule jvjindouRule){
		try {
			jvjindouRuleService.insertJvjindouRule(jvjindouRule);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数有误！");
		}
		return ResultConstant.ofSuccess();
	}
	
	@ApiOperation(value="后台商品管理", notes="查询赠送聚金豆规则")
	@GetMapping("/getJvjindouRule")
	public ResultConstant getJvjindouRule(@RequestParam(value="id", required=true)  Integer id){
		TbJvjindouRule jvjindouRule = jvjindouRuleService.getJvjindouRule(id);
		return ResultConstant.ofSuccess(ConvertBeanToMapUtils.convertBeanToMap(jvjindouRule));
	}
	
	/*@ApiOperation(value="聚金豆规则修改", notes="修改赠送聚金豆规则,givingRule(赠送规则，2，固定，1随机)，randomGivingMin(随机赠送最小数),randomGivingMax(最大数),fixedGiving(固定赠送值)")
	@PutMapping("/updateJvjindouRule")
	public ResultConstant updateJvjindouRule(TbJvjindouRule jvjindouRule){
		try {
			jvjindouRuleService.updateJvjindouRule(jvjindouRule);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数有误！");
		}
		return ResultConstant.ofSuccess();
	}*/
	
	@ApiOperation(value="生成二维码", notes="生成客服指引需要的二维码")
	@PutMapping("/getQRCode")
	public ResultConstant getQRCode(@RequestParam(value="id", required=true)  String id){
		Captcha qrCode = userService.getQRCode(id);
		return ResultConstant.ofSuccess(qrCode);
	}
	
	@ApiOperation(value="查询个人信息", notes="查询登陆人的等级信息 聚金豆 信息 成长点")
	@GetMapping("/queryTbLevelInfo")
	public ResultConstant queryTbLevelInfo(@RequestParam(value="userId",required=true) Integer userId) {
		TbLevelInfo levelInfo = levelInfoService.queryTbLevelInfo(userId);
		return ResultConstant.ofSuccess(levelInfo);
	}
	
	@ApiOperation(value="查询用户积分", notes="更具用户id来查询用户积分")
	@GetMapping("/queryUserAmount")
	public ResultConstant queryUserAmount(@RequestParam(value="userId",required=true) Integer userId) {
		Integer amount = levelInfoService.queryTbLevelUserAmount(userId);
		return ResultConstant.ofSuccess(amount);
	}
	
	@ApiOperation(value="同步赠送聚金豆", notes="查询积分赠送聚金豆，用户当日首次查询积分赠送聚金豆，userId")
	@GetMapping("/synchronizeAmount")
	public ResultConstant synchronizeAmount(@RequestParam(value="userId",required=true) Integer userId) {
		Map<String,Object> amount = levelInfoService.presentJvjindouByuserId(userId);
		return ResultConstant.ofSuccess(amount);
	}
	
	@ApiOperation(value="系统消息查询（移动端）",notes ="系统消息查询,userId:用户id，page:当前页，pageSize：每页条数")
	@GetMapping("/getInformationApp")
	public ResultConstant getInformationApp(
			@RequestParam(value="userId",required=true)Integer userId,
			@RequestParam(value="page",required=true)Integer page,
			@RequestParam(value="pageSize",required=true)Integer pageSize
			) {
		
		Long informationNuber = systemInformationService.countByExample();
		jedisCluster.saveString(userId.toString(), informationNuber.toString());
		PageInfo pageInfo = systemInformationService.getSystemInformationsApp(userId,page, pageSize);
		return ResultConstant.ofSuccess(pageInfo);
		
	}
	
	@ApiOperation(value="未读系统消息数量查询",notes ="userId:用户id")
	@GetMapping("/getInformationNuber")
	public ResultConstant getInformationNuber(
			@RequestParam(value="userId",required=true)Integer userId
			) {
		Long informationNuber = systemInformationService.countByExample();
		String  informationUser = jedisCluster.getString(userId.toString());
		long nuber =informationNuber;
		if(informationUser==null|| "".equals(informationUser)) {
			jedisCluster.saveString(userId.toString(), informationNuber.toString());
		}else {
			 nuber = informationNuber-Long.parseLong(informationUser);
		}
		return ResultConstant.ofSuccess(nuber);
		
	}
	
}
