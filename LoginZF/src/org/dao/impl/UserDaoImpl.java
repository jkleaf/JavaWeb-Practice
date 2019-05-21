package org.dao.impl;

import java.io.IOException;
import java.util.List;

import org.bean.User;
import org.dao.UserDao;
import org.utils.MySQLQuery;
import org.web.getInfo.UserInterface;

public class UserDaoImpl implements UserDao{
	
	private UserInterface ui;
	
	public UserDaoImpl() {
		ui=new UserInterface();
	}

	@Override
	public int isLogin(User user) {
//		List<Object> resList=new MySQLQuery().queryRows("select account,password from user"
//				+ " where account=? and password=?",new Object[] {account,password});
//		return !resList.isEmpty();
//		insert2UserTable(user);
		String account=user.getAccount();
		String password=user.getPassword();
//		user.setToken(ui.makeToken());
		try {
			return ui.Login(account, password);
		} catch (IOException e) {
			return -1;
		}
	}
	
	public String getIndexUrl(String account) {
		return ui.getAccessUrlHead() + "xs_main.aspx?xh=" + account;
	}
	
	public void insert2UserTable(User user) {
//		if(!isExists(user)) {
//			new MySQLQuery().insert(user);
//		}
		new MySQLQuery().insert(user);
	}
	
	private boolean isExists(User user) {
		String account=user.getAccount();
		String password=user.getPassword();
		List<Object> resList=new MySQLQuery().queryRows("select account,password from user"
				+ " where account=? and password=?",new Object[] {account,password});
		return !resList.isEmpty();
	}

}
