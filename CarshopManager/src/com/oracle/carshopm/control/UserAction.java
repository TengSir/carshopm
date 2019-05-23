package com.oracle.carshopm.control;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.oracle.carshopm.model.bean.User;
import com.oracle.carshopm.model.service.UserService;

/**
 * 等同于servlet里面的UserServlet
 * 
 * @author TengSir
 */
@Component("userAction")
public class UserAction extends ActionSupport {
	public UserAction() {
		System.out.println("实例化了userAction");
	}
	private File image;
	private String imageContentType;
	private String imageFileName;
	private long size;

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

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

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	private String verifyCode;
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

	private JSONObject jsonObj;

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}

	@Autowired
	private UserService service;




	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String upload() {
		String path = ServletActionContext.getRequest().getRealPath("upload");// 用request获取服务器上的upload目录绝对地址
		String lastFileName = UUID.randomUUID()
				+ imageFileName.substring(imageFileName.lastIndexOf("."), imageFileName.length());
		File dest = new File(path, lastFileName);// 新建一个文件对象，准备将上传的文件存储到这个文件位置上
		try {
			FileUtils.copyFile(image, dest);// 用apache的fileupload组件里面的文件帮助类直接讲上传的文件拷贝到我们想放置的文件位置上
			jsonObj = new JSONObject();
			jsonObj.put("url", "upload/" + lastFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void uploadFileSize() {
//		System.out.println("即将上传的文件大小是"+size);
	}

	/**
	 * 使用分页的方式查询用户列表，返回json格式
	 * 
	 * @return
	 */

	public String listUsers() {
		HashMap<String, Object> datas = new HashMap<>();
		datas.put("total", service.getAllCount());
		datas.put("rows", service.listUsers(page, rows));
		jsonObj = new JSONObject(datas);
		return SUCCESS;
	}

	/**
	 * 添加用户信息的ajax方法
	 * 
	 * @return
	 */
	public String addUser() {
		boolean result = service.add(user);
		jsonObj = new JSONObject();
		jsonObj.put("result", result);
		return SUCCESS;
	}

	public String deleteUserById() {
		boolean result = service.deletUserById(user.getUserid());
		jsonObj = new JSONObject();
		jsonObj.put("result", result);
		return SUCCESS;
	}

	/**
	 * 根据用户id返回json格式的用户对象的ajax反方
	 * 
	 * @return
	 */
	public String getUserByUserId() {
		User user = service.getUserInfoByUserId(this.user.getUserid());
		jsonObj = JSONObject.parseObject(JSONObject.toJSONString(user));
		return SUCCESS;
	}

	/**
	 * ajax请求过来获得统计图上的根据年龄统计的数据
	 * 
	 * @return
	 */
	public String getTongjiResultByAge() {
		Map<Integer, Integer> result = service.tongjiByAge();
		jsonObj = new JSONObject();
		JSONArray data = new JSONArray();
		for (Integer key : result.keySet()) {
			JSONObject obj = new JSONObject();
			obj.put("value", result.get(key));
			obj.put("name", key);
			data.add(obj);
		}
		jsonObj.put("data", data);
		return SUCCESS;
	}

	/**
	 * ajax请求过来获得统计图上的根据性别统计的数据
	 * 
	 * @return
	 */
	public String getTongjiResultBySex() {
		Map<String, Integer> result = service.tongjiBySex();
		jsonObj = new JSONObject();
		JSONArray data = new JSONArray();
		for (String key : result.keySet()) {
			JSONObject obj = new JSONObject();
			obj.put("value", result.get(key));
			obj.put("name", key);
			data.add(obj);
		}
		jsonObj.put("data", data);
		return SUCCESS;
	}

	/**
	 * ajax请求过来获得统计图上的根据职业统计的数据
	 * 
	 * @return
	 */
	public String getTongjiResultByJob() {
		Map<String, Integer> result = service.tongjiByJob();
		jsonObj = new JSONObject();
		JSONArray data = new JSONArray();
		for (String key : result.keySet()) {
			JSONObject obj = new JSONObject();
			obj.put("value", result.get(key));
			obj.put("name", key);
			data.add(obj);
		}
		jsonObj.put("data", data);
		return SUCCESS;
	}
	/**
	 * ajax请求过来教研验证码的方法
	 * 
	 * @return
	 */
	public String checkCode() {
		//获得google生成的存储在session中的验证码
		String sysCode = ServletActionContext.getRequest().getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY).toString();
		jsonObj=new JSONObject();
		jsonObj.put("result", verifyCode.equalsIgnoreCase(sysCode)?true:false);
		return SUCCESS;
	}

	public String loadProgress() {
		File dir = new File(
				"/Users/tengsir/workspace/java/JavaEE/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/work/Catalina/localhost/ROOT/");
		File[] fs = dir.listFiles();
		long maxTime = fs[0].lastModified();
		File newFile = null;

		for (File f : fs) {
			if (f.lastModified() > maxTime) {
				maxTime = f.lastModified();
				newFile = f;
			}
		}
		double fullSize = size;
		double nowSize = newFile.length();
		jsonObj = new JSONObject();
		jsonObj.put("progress", (int) (nowSize / fullSize * 100) + "");
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
		User u=service.login(user);
		jsonObj=new JSONObject();
		if (u!=null) {
			ServletActionContext.getRequest().getSession().setAttribute("user", u);
			jsonObj.put("result", "success");
		} else {
			jsonObj.put("result", "fail");
		}
		return "success";
	}

}
