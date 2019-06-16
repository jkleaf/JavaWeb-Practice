package dao;

import bean.User;
import jdbc.MysqlQuery;

public class UserDao {

	public static boolean isLogin(User user) {
		return MysqlQuery.queryUser(user);
	}
	
	public static User getUser(String account) {
		return MysqlQuery.queryUser(account);
	}
	
}
