package org.dao;

import org.bean.User;

public interface UserDao {
	
//	void add(User user);
//	
//	void find(String id);
//	
//	void find(String username,String password);
	
	public int isLogin(User user);

	public void insert2UserTable(User user);
}
