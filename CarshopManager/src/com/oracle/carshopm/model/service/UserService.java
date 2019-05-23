package com.oracle.carshopm.model.service;

import java.util.ArrayList;
import java.util.Map;

import com.oracle.carshopm.model.bean.User;

public interface UserService {
	public User login(User user);

	public User getUserInfoByUserId(int userid);

	public boolean checkUserExsit(String username);

	public boolean deletUserById(int userid);
	public boolean add(User user);

	public int getAllCount();

	public ArrayList<User> listUsers(int page, int count);

	public Map<Integer, Integer> tongjiByAge();

	public Map<String, Integer> tongjiBySex();

	public Map<String, Integer> tongjiByJob();
}
