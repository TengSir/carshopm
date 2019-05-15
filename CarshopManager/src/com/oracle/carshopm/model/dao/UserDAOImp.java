package com.oracle.carshopm.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.oracle.carshopm.model.bean.User;

public class UserDAOImp extends BaseDAOImp implements UserDAO {

	@Override
	public boolean add(Object o) {
		User user = (User) o;
		boolean result = false;
		Statement sta = null;
		try {
			sta = getSta();
			int count = sta.executeUpdate("insert into  user(userid,username,password)   values(null,'"
					+ user.getUsername() + "','" + user.getPassword() + "')");
			result = (count > 0) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(Object id) {
		// TODO Auto-generated method stub
		return false;
	}
//
//	@Override
//	public boolean update(Object o) {
//		User u=(User)o;
//		PreparedStatement  preSta=null;
//		int result=0;
//		try {
//			  preSta=getPreSta("update user set username=? ,nickname=?,sex=?,age=?,image=?,job=?,jialing=?,email=?,tel=?,jianjie=? where userid=?");
//			  preSta.setString(1, u.getUsername());
//			  preSta.setString(2, u.getNickname());
//			  preSta.setInt(3, u.getSex());;
//			  preSta.setInt(4, u.getAge());;
//			  preSta.setString(5, u.getImage());
//			  preSta.setString(6, u.getJob());
//			  preSta.setInt(7, u.getJialing());
//			  preSta.setString(8, u.getEmail());
//			  preSta.setString(9, u.getTel());
//			  preSta.setString(10, u.getJianjie());
//			  preSta.setInt(11, u.getUserid());
//			  result=preSta.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result>0?true:false;
//	}
//
//	@Override
//	public Object list() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User login(String username, String password) {
//		User  user=null;
//		PreparedStatement  sta=null;
//		ResultSet rs=null;
//		try {
//			  sta=getPreSta("select *  from user where username=? and password=?");
//			sta.setString(1, username);
//			sta.setString(2, password);
//			rs=sta.executeQuery();
//			if(rs.next()) {
//				user=new User();
//				user.setUserid(rs.getInt("userid"));
//				user.setUsername(rs.getString("username"));
//				user.setPassword(rs.getString("password"));
//				if(rs.getString("image")!=null)
//				{
//				user.setAge(rs.getInt("age"));
//				user.setImage(rs.getString("image"));
//				user.setNickname(rs.getString("nickname"));
//				user.setSex(rs.getInt("sex"));
//				user.setJob(rs.getString("job"));
//				user.setJialing(rs.getInt("jialing"));
//				user.setEmail(rs.getString("email"));
//				user.setTel(rs.getString("tel"));
//				user.setJianjie(rs.getString("jianjie"));
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return user;
//	}
//
//	@Override
//	public User getUserInfoByUserId(int userid) {
//		User  user=null;
//		PreparedStatement  sta=null;
//		ResultSet rs=null;
//		try {
//			  sta=getPreSta("select *  from user where userid=?");
//			sta.setInt(1, userid);
//			rs=sta.executeQuery();
//			if(rs.next()) {
//				user=new User();
//				user.setUserid(rs.getInt("userid"));
//				user.setUsername(rs.getString("username"));
//				user.setPassword(rs.getString("password"));
//				if(rs.getString("image")!=null)
//				{
//					user.setAge(rs.getInt("age"));
//					user.setImage(rs.getString("image"));
//					user.setNickname(rs.getString("nickname"));
//					user.setSex(rs.getInt("sex"));
//					user.setJob(rs.getString("job"));
//					user.setJialing(rs.getInt("jialing"));
//					user.setEmail(rs.getString("email"));
//					user.setTel(rs.getString("tel"));
//					user.setJianjie(rs.getString("jianjie"));
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return user;	}

	@Override
	public boolean checkUserExsit(String username) {
		boolean result = false;
		Statement sta = null;
		try {
			sta = getSta();
			ResultSet rs = sta.executeQuery("select  count(userid)  from  user where username='" + username + "'");
			rs.next();
			result = (rs.getInt(1) > 0) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<User> listUsers(int page,int count) {
		ArrayList<User> us = new ArrayList<>();
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			sta = getPreSta("select *  from user order by userid desc limit ?,?");
			sta.setInt(1, (page-1)*count);
			sta.setInt(2, count);
			rs = sta.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAge(rs.getInt("age"));
				user.setImage("<img src='"+rs.getString("image")+"' style='width:30px;height:30px'/>");
				user.setNickname(rs.getString("nickname"));
				user.setSex(rs.getInt("sex"));
				user.setJob(rs.getString("job"));
				user.setJialing(rs.getInt("jialing"));
				user.setEmail(rs.getString("email"));
				user.setTel(rs.getString("tel"));
				user.setJianjie(rs.getString("jianjie"));
				us.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return us;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserInfoByUserId(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllCount() {
		int count=0;
		Statement sta = null;
		try {
			sta = getSta();
			ResultSet rs = sta.executeQuery("select  count(userid)  from  user ");
			rs.next();
			count=rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public boolean deletUserById(int userid) {
		boolean result = false;
		Statement sta = null;
		try {
			sta = getSta();
			int count = sta.executeUpdate("delete from user where userid="+userid);
			result = (count > 0) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}