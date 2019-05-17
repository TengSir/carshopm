package com.oracle.carshopm.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.oracle.carshopm.model.bean.User;

public class HibernateTest {

	public static void main(String[] args) {
		//使用hibernate提供的api来对数据库做操作了
		Configuration cfg=new Configuration().configure();//创建一个配置读取对象用来读取hibernate配置文件
		SessionFactory sf=cfg.buildSessionFactory();//使用配置对象创建了一个sessionfactory
		Session session=sf.openSession();//使用hibernate的sesion工厂打开了一个数据库连接
		
		User  user=new User();
		user.setAge(12);
		user.setSex(1);
		user.setUsername("jack");
		user.setPassword("rose");
		user.setNickname("jacks");
		user.setJob("manager");
		user.setTel("123");
		user.setJob("Driver");
		user.setImage("images/1.gif");
		user.setJianjie("2222");
		user.setJialing(22);
		
		Transaction tr=session.beginTransaction();
		session.save(user);
		tr.commit();
	}

}
