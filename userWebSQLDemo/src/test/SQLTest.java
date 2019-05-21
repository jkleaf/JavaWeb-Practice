package test;

import java.util.List;

import bean.User;
import dao.UserDao;
import utils.MysqlOperation;

public class SQLTest {

	public static void main(String[] args) {
//		queryAllUsers();
//		System.out.println(isLogin("sv53v", "514535"));
//		System.out.println(isLogin("scsac", "3s1c2"));
//		System.out.println(insertUser(new User("6","d31v","12vd31","dvji@12v","112131211")));
//		System.out.println(deleteUser("6"));
//		System.out.println(updateUser(new String[] {"dc1","c5d","51d@123","16455215","6"}));
		queryUsersById();
	}

	public static void queryUsersById() {
		List<User> userList = UserDao.queryUsersById("1");
		for (User user : userList) {
			System.out.println(user.getId() + " " + user.getName() + " " + user.getPwd() + " " + user.getEmail() + " "
					+ user.getPhonenum());
		}
	}
	
	public static void queryAllUsers() {
		String sql = "select * from user";
		List<User> userList = MysqlOperation.queryUsers(sql, null);
		for (User user : userList) {
			System.out.println(user.getId() + " " + user.getName() + " " + user.getPwd() + " " + user.getEmail() + " "
					+ user.getPhonenum());
		}
	}

	public static boolean isLogin(String name, String pwd) {
		String sql = "select * from user where name=? and pwd=?";
		List<User> users = MysqlOperation.queryUsers(sql, new String[] { name, pwd });
		return users != null && !users.isEmpty();
	}

	public static int insertUser(User user) {
		return MysqlOperation.insertUser(user);
	}
		
	public static int deleteUser(String id) {
		return MysqlOperation.deleteUser(id);
	}
	
	public static int updateUser(String[] params) {
		return MysqlOperation.updateUser(params);
	}
	
}
