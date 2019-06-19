package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bean.TreeImage;
import bean.User;
import dao.TreeImageDao;
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
		
//		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
//		System.out.println(sdf.format(1000));
	
//		String date="20190616224834";
//		System.out.println(date);
//		System.out.println(date.substring(0,4)+date.substring(5,7)+date.substring(8,10));
		
		List<TreeImage> treeImages=TreeImageDao.getImages("20190617", "20190618");
		for (TreeImage treeImage : treeImages) {
			System.out.println(treeImage.getId());
			System.out.println(treeImage.getLongitude());
			System.out.println(treeImage.getLatitude());
			System.out.println(treeImage.getU_account());
			System.out.println(treeImage.getRecord_date());
		}
	
	}
	
}
