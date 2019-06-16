package test;

import java.text.SimpleDateFormat;

import bean.User;
import dao.UserDao;
import jdbc.JDBCUtils;
import jdbc.MysqlQuery;

public class JDBCTest {

	public static void main(String[] args) {
//		System.out.println(JDBCUtils.getConnection());
		
//		User user=new User();
//		user.setAccount("51513");
//		user.setPassword("56sdv4");
//		System.out.println(UserDao.isLogin(user));
		
//		String account="51513";
//		User user=MysqlQuery.queryUser(account);
//		System.out.println(user.getAccount());
//		System.out.println(user.getUsername());
//		System.out.println(user.getPassword());
//		System.out.println(user.getGender());
//		System.out.println(user.getEmail());
//		System.out.println(user.getTelephone());
		
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(1000));
	}
	
}
