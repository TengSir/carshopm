package com.oracle.carshopm.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.oracle.carshopm.model.bean.User;

public interface UserDAO extends BaseDAO {
	public User  login(User user);
	public  User getUserInfoByUserId(int userid);
	public boolean checkUserExsit(String username);
	public boolean deletUserById(int userid);
	public int getAllCount();
	public ArrayList<User>  listUsers(int page,int count);
	public Map<Integer, Integer> tongjiByAge();
	public Map<String, Integer> tongjiBySex();
	public Map<String, Integer> tongjiByJob();
}
