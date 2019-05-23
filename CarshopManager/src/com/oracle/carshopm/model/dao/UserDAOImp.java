package com.oracle.carshopm.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.oracle.carshopm.model.bean.User;

@Component
public class UserDAOImp implements UserDAO {
	public UserDAOImp() {
		System.out.println("初始化了userdao对象");
	}

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public boolean add(Object o) {
		try {
			sessionFactory.getCurrentSession().save(o);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	@Override
	public boolean delete(Object id) {
		try {
			sessionFactory.getCurrentSession().delete(id);
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	@Override
	public boolean checkUserExsit(String username) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.eq("username", username));

		return c.uniqueResult() == null ? false : true;
	}

	
	@Override
	public boolean update(Object o) {
		try {
			sessionFactory.getCurrentSession().update(o);
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	@Override
	public ArrayList<User> listUsers(int page, int count) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(User.class);
		c.setFirstResult((page - 1) * count);
		c.setMaxResults(count);
		ArrayList<User> users = (ArrayList<User>) c.list();
		for (User u : users) {
			u.setImage("<img src='" + u.getImage() + "'  style='width:30px;height:30px'/>");
		}
		return users;
	}

	@Override
	public User login(User user) {
		Query q = sessionFactory.getCurrentSession().createQuery("from User where username=? and password=?");
		q.setString(0, user.getUsername());
		q.setString(1, user.getPassword());
		return (User) q.uniqueResult();
	}

	@Override
	public User getUserInfoByUserId(int userid) {
		return (User) sessionFactory.getCurrentSession().get(User.class, userid);
	}

	@Override
	public Object list() {
		return null;
	}

	@Override
	public int getAllCount() {
		Query q = sessionFactory.getCurrentSession().createSQLQuery("select count(userid) from user");
		Object result = q.uniqueResult();
		return Integer.parseInt(result.toString());
	}

	
	@Override
	public boolean deletUserById(int userid) {
		User u = new User();
		u.setUserid(userid);
		try {
			sessionFactory.getCurrentSession().delete(u);
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	@Override
	public Map<Integer, Integer> tongjiByAge() {
		Map<Integer, Integer> tongji = new HashMap<>();
		Query q = sessionFactory.getCurrentSession().createSQLQuery("select count(age),age from user group by age");
		List result = q.list();
		for (Object o : result) {
			Object[] oo = (Object[]) o;
			tongji.put(Integer.parseInt(oo[1].toString()), Integer.parseInt(oo[0].toString()));
		}
		return tongji;
	}

	@Override
	public Map<String, Integer> tongjiBySex() {
		Map<String, Integer> tongji = new HashMap<>();
		Query q = sessionFactory.getCurrentSession().createSQLQuery("select count(sex),sex from user group by sex");
		List result = q.list();
		for (Object o : result) {
			Object[] oo = (Object[]) o;
			tongji.put(((Boolean.parseBoolean(oo[1].toString())) ? "男" : "女"), Integer.parseInt(oo[0].toString()));
		}
		return tongji;
	}

	@Override
	public Map<String, Integer> tongjiByJob() {
		Map<String, Integer> tongji = new HashMap<>();
		Query q = sessionFactory.getCurrentSession().createSQLQuery("select count(job),job from user group by job");
		List result = q.list();
		for (Object o : result) {
			Object[] oo = (Object[]) o;
			tongji.put(oo[1].toString(), Integer.parseInt(oo[0].toString()));
		}
		return tongji;
	}
}