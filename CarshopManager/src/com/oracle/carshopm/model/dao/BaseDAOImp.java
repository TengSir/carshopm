package com.oracle.carshopm.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class BaseDAOImp implements BaseDAO {
	private static SessionFactory sf;
	private Session session;

	public Session getSession() {
		return session = sf.openSession();
	}

	static {
		Configuration cfg = new Configuration().configure();// 创建一个配置读取对象用来读取hibernate配置文件
		sf = cfg.buildSessionFactory();// 使用配置对象创建了一个sessionfact
	}
}
