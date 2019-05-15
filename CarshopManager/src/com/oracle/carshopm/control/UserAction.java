package com.oracle.carshopm.control;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONPOJOBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.oracle.carshopm.model.bean.User;
import com.oracle.carshopm.model.dao.UserDAO;
import com.oracle.carshopm.model.dao.UserDAOImp;

/**
 * 等同于servlet里面的UserServlet
 * 
 * @author TengSir
 */

public class UserAction extends ActionSupport {
	private File  image;
	private String imageContentType;
	private String imageFilename;
	
	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFilename() {
		return imageFilename;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}

	private String kaptchafield;
	private int page;// 1
	private int rows;// 10

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	private JSONObject array;

	public JSONObject getArray() {
		return array;
	}

	public void setArray(JSONObject array) {
		this.array = array;
	}

	private UserDAO dao;

	public UserAction() {
		dao = new UserDAOImp();
	}

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

	public String upload() {
		System.out.println("upload method");
		try {
			System.out.println(imageContentType);
			System.out.println(imageFilename);
			FileInputStream in = new FileInputStream(image);
			byte[] bs = new byte[1024];
			int leng = -1;
			while ((leng = in.read(bs)) != -1) {
				System.out.println(Arrays.toString(bs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("upload");
		return null;
	}

	/**
	 * 使用分页的方式查询用户列表，返回json格式
	 * 
	 * @return
	 */

	public String listUsers() {
		HashMap<String, Object> datas = new HashMap<>();
		datas.put("total", dao.getAllCount());
		datas.put("rows", dao.listUsers(page, rows));
		array = new JSONObject(datas);
		return SUCCESS;
	}

	public String deleteUserById() {
		boolean result = dao.deletUserById(user.getUserid());
		array = new JSONObject();
		array.put("result", result);
		return SUCCESS;
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
		// 如何在Struts使用servlet原生api对象
		// ActionContext.getContext().getSession()

		// ServletActionContext.getRequest()
		String sysCode = ServletActionContext.getRequest().getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY).toString();
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
