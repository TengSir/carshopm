package com.oracle.carshopm.control;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.oracle.carshopm.model.User;
/**
 * 等同于servlet里面的UserServlet
 * 
 * @author TengSir
 */

public class UserAction extends ActionSupport{
	private String kaptchafield;

	public String getKaptchafield() {
		return kaptchafield;
	}

	public void setKaptchafield(String kaptchafield) {
		this.kaptchafield = kaptchafield;
	}

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

	public String logoff() {
		ServletActionContext.getRequest().getSession().removeAttribute("username");
		return "fail";
	}
	/*
	 * public void validateLogin() { System.out.println("进入到验证方法了"); if
	 * (user.getUsername().length() == 0) { this.addFieldError("usernameError",
	 * "用户名不能为空！"); System.out.println("判读到用户名为空了"); } else { if
	 * (user.getPassword().length() == 0) { this.addFieldError("passwordError",
	 * "密码不能为空！"); System.out.println("判读到密码为空了"); } } System.out.println(""); }
	 */

	public String login() {
		//如何在Struts使用servlet原生api对象 
		//	ActionContext.getContext().getSession()
		
		//ServletActionContext.getRequest()
		String sysCode = ServletActionContext.getRequest().getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY).toString();
		if (user.getPassword().equals("123")) {
			System.out.println("login success");
			 ServletActionContext.getRequest().getSession().setAttribute("username", user.getUsername());
			return "success";
		} else {
			System.out.println("login fail");
			return "fail";
		}
	}

}
