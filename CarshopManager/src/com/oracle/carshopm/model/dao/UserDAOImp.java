package com.oracle.carshopm.model.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.oracle.carshopm.model.bean.User;

public class UserDAOImp extends BaseDAOImp implements UserDAO {

	@Override
	public boolean add(Object o) {
		Session s = getSession();
		Transaction tr = s.beginTransaction();
		try {
			s.save(o);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			return false;

		}
	}

	@Override
	public boolean delete(Object id) {
		Session s = getSession();
		Transaction tr = s.beginTransaction();
		try {
			s.delete(id);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			return false;

		}
	}

	@Override
	public boolean checkUserExsit(String username) {
		return false;
	}

	@Override
	public boolean update(Object o) {
		Session s = getSession();
		Transaction tr = s.beginTransaction();
		try {
			s.update(o);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			return false;

		}
	}

	@Override
	public ArrayList<User> listUsers(int page, int count) {
		Criteria c = getSession().createCriteria(User.class);
		c.setFirstResult((page - 1) * count);
		c.setMaxResults(count);
		ArrayList<User> users = (ArrayList<User>) c.list();
		for(User u:users) {
			u.setImage("<img src='"+u.getImage()+"'  style='width:30px;height:30px'/>");
		}
		return users;
	}

	@Override
	public User login(String username, String password) {
		return null;
	}

	@Override
	public User getUserInfoByUserId(int userid) {
		return (User) getSession().get(User.class, userid);
	}

	@Override
	public Object list() {
		return null;
	}

	@Override
	public int getAllCount() {
		Query  q=getSession().createSQLQuery("select count(userid) from user");
		Object result=q.uniqueResult();
		return Integer.parseInt(result.toString());
	}

	@Override
	public boolean deletUserById(int userid) {
		User u = new User();
		u.setUserid(userid);
		Session s = getSession();
		Transaction tr = s.beginTransaction();
		try {
			s.delete(u);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			return false;

		}
	}
}