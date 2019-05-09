package com.oracle.carshopm.control;

import javax.sound.midi.Soundbank;

import com.opensymphony.xwork2.ActionSupport;
import com.oracle.carshopm.model.User;

/**
 * 等同于servlet里面的UserServlet
 * @author TengSir
 */
public class UserAction extends ActionSupport{
	private User user;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String register() {
		System.out.println("registermethod");
		return null;
	}
	public String forgetPassword() {
		System.out.println("forgetPassword");
		return null;
	}
	public String updatepassword() {
		System.out.println("updatepassword");
		return null;
	}
	public void validateLogin() {
		System.out.println("进入到验证方法了");
		if(user.getUsername().length()==0) {
			this.addFieldError("usernameError", "用户名不能为空！");
			System.out.println("判读到用户名为空了");
		}else {
			if(user.getPassword().length()==0) {
				this.addFieldError("passwordError", "密码不能为空！");
				System.out.println("判读到密码为空了");
			}
		}
		System.out.println("");
	}
	public String login() {
		System.out.println("进入到登陆方法了");
		if(user.getUsername().equals("admin")&&user.getPassword().equals("123")) {
			System.out.println("login success");
			return "success";
		}else {
			System.out.println("login fail");
			return "fail";
		}
//		System.out.println("处理登陆查询数据库");
//		return "";
	}


}
