package com.jfshare.mvp.server.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jfshare.mvp.server.constants.ResultConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户管理
 * @author fengxiang
 * @date 2018-07-19
 */
@Api("api")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@ApiOperation(value="查询用户", notes="用id来查询用户")
	@GetMapping
	public ResultConstant getUser(@RequestParam(value="id", required=true) Integer id) {
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
}
