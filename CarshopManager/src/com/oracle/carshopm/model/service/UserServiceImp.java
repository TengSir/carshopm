package com.oracle.carshopm.model.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.carshopm.model.bean.User;
import com.oracle.carshopm.model.dao.UserDAO;

@Component
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	private UserDAO dao;

	public UserDAO getDao() {
		return dao;
	}

	public void setDao(UserDAO dao) {
		this.dao = dao;
	}

	public UserServiceImp() {
	}

	@Override
	public User login(User user) {

		return dao.login(user);
	}

	@Override
	public User getUserInfoByUserId(int userid) {
		return dao.getUserInfoByUserId(userid);
	}

	@Override
	public boolean checkUserExsit(String username) {
		return dao.checkUserExsit(username);
	}

	@Override
	public boolean deletUserById(int userid) {
		return dao.deletUserById(userid);
	}

	@Override
	public int getAllCount() {
		return dao.getAllCount();
	}

	@Override
	public ArrayList<User> listUsers(int page, int count) {
		return dao.listUsers(page, count);
	}

	@Override
	public Map<Integer, Integer> tongjiByAge() {
		return dao.tongjiByAge();
	}

	@Override
	public Map<String, Integer> tongjiBySex() {
		return dao.tongjiBySex();
	}

	@Override
	public Map<String, Integer> tongjiByJob() {
		return dao.tongjiByJob();
	}

	@Override
	public boolean add(User user) {
		return dao.add(user);
	}

}
