package com.oracle.carshopm.model.dao;

public interface BaseDAO {// 提取父接口(父类应该干的事情，把一些公共的资源声明在这里)
//	public String dirverClass = "com.mysql.jdbc.Driver";
//	public String url = "jdbc:mysql://localhost:3306/carshop?useUnicode=true&characterEncoding=UTF-8";
//	public String username = "root";
//	public String password = "";
	

	// 添加
	public abstract boolean add(Object o);

	public abstract boolean delete(Object id);

	public abstract boolean update(Object o);

	public abstract Object list();

}
