package com.oracle.carshopm.model.dao;

import java.util.ArrayList;

import com.oracle.carshopm.model.bean.User;

public interface UserDAO extends BaseDAO {
	public User  login(String username,String password);
	public  User getUserInfoByUserId(int userid);
	public boolean checkUserExsit(String username);
	public boolean deletUserById(int userid);
	public int getAllCount();
	public ArrayList<User>  listUsers(int page,int count);
}
