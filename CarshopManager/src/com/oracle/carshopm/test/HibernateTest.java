package com.oracle.carshopm.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.oracle.carshopm.model.bean.User;

public class HibernateTest {

	public static void main(String[] args) {
		System.out.println("test");
		//使用hibernate提供的api来对数据库做操作了
		Configuration cfg=new Configuration().configure("/hibernate.cfg.xml");//创建一个配置读取对象用来读取hibernate配置文件
		SessionFactory sf=cfg.buildSessionFactory();//使用配置对象创建了一个sessionfactory
		Session session=sf.openSession();//使用hibernate的sesion工厂打开了一个数据库连接
		
		Criteria  c=session.createCriteria(User.class);
		
		c.add(Restrictions.eq("username", "Arsenio"));
		c.add(Restrictions.eq("password", "e1	$429^I`Y4+~8{9])0O+"));
		
		List result=c.list();
		for(Object o:result) {
			System.out.println(o);
		}
		
//		User  user=new User();
//		user.setAge(12);
//		user.setSex(1);
//		user.setUsername("jack");
//		user.setPassword("rose");
//		user.setNickname("jacks");
//		user.setJob("manager");
//		user.setTel("123");
//		user.setJob("Driver");
//		user.setImage("images/1.gif");
//		user.setJianjie("2222");
//		user.setJialing(22);
//		
//		Transaction tr=session.beginTransaction();
//		session.save(user);
//		tr.commit();
	}

}
