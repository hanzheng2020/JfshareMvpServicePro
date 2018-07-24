package com.jfshare.mvp.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.model.TbJvjindouRule;
import com.jfshare.mvp.server.service.JvjindouRuleService;

/**
 * 用户管理
 * @author fengxiang
 * @date 2018-07-19
 */
@Api(value="api", tags="个人中心模块相关API")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private JvjindouRuleService jvjindouRuleService;
	
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
	@PostMapping("/insertLevelinfo")
	public ResultConstant insertLevelinfo(TbJvjindouRule jvjindouRule){
		try {
			jvjindouRuleService.insertJvjindouRule(jvjindouRule);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数有误！");
		}
		return ResultConstant.ofSuccess();
	}
	
	@ApiOperation(value="后台商品管理", notes="查询赠送聚金豆规则")
	@GetMapping("/getLevelinfo")
	public ResultConstant getLevelinfo(@RequestParam(value="id", required=true)  Integer id){
		return ResultConstant.ofSuccess();
	}
	
}
