package com.oracle.carshopm.control;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
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
		String path=ServletActionContext.getRequest().getRealPath("upload");//用request获取服务器上的upload目录绝对地址
		String lastFileName=UUID.randomUUID()+imageFileName.substring(imageFileName.lastIndexOf("."),	 imageFileName.length());
		File  dest=new File(path,lastFileName);//新建一个文件对象，准备将上传的文件存储到这个文件位置上
		try {
			FileUtils.copyFile(image, dest);//用apache的fileupload组件里面的文件帮助类直接讲上传的文件拷贝到我们想放置的文件位置上
			System.out.println("upload sucess:"+ "upload/"+lastFileName);
			array = new JSONObject();
			array.put("url", "upload/"+lastFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
//		System.out.println("upload method");
//		try {
//			System.out.println(imageContentType);
//			System.out.println(imageFilename);
//			FileInputStream in = new FileInputStream(image);
//			byte[] bs = new byte[1024];
//			int leng = -1;
//			while ((leng = in.read(bs)) != -1) {
//				System.out.println(Arrays.toString(bs));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("upload");
//		return null;
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
		datas.put("total", dao.getAllCount());
		datas.put("rows", dao.listUsers(page, rows));
		array = new JSONObject(datas);
		return SUCCESS;
	}
	/**
	 * 添加用户信息的ajax方法
	 * @return
	 */
	public String addUser() {
		boolean result=dao.add(user);
		array = new JSONObject();
		array.put("result", result);
		return SUCCESS;
	}

	public String deleteUserById() {
		boolean result = dao.deletUserById(user.getUserid());
		array = new JSONObject();
		array.put("result", result);
		return SUCCESS;
	}
	/**
	 * 根据用户id返回json格式的用户对象的ajax反方
	 * @return
	 */
	public String getUserByUserId() {
		User user= dao.getUserInfoByUserId(this.user.getUserid());
		array =JSONObject.parseObject(JSONObject.toJSONString(user));
		return SUCCESS;
	}
	
	public String loadProgress() {
		File  dir=new File("/Users/tengsir/workspace/java/JavaEE/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/work/Catalina/localhost/ROOT/");
		File[]  fs=dir.listFiles();
		long maxTime=fs[0].lastModified();
		File newFile=null;
		
		for(File f:fs) {
			if(f.lastModified()>maxTime) {
				maxTime=f.lastModified();
				newFile=f;
			}
		}
		double  fullSize=size;
		double  nowSize=newFile.length();
		array = new JSONObject();
		array.put("progress",(int)(nowSize/fullSize*100)+"");
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
