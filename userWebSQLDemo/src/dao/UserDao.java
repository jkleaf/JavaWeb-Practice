package dao;

import java.util.List;

import bean.User;
import utils.MysqlOperation;

public class UserDao {

	public static List<User> queryAllUsers(String all) {
		if(all==null) {
			return MysqlOperation.queryUsers("select * from user", null);
		}
		String sql = "select * from user where concat(id,name,pwd,email,phonenum) like \"%\"?\"%\" ";
		List<User> userList = MysqlOperation.queryUsers(sql, new String[] {all});
		return userList;
//		for (User user : userList) {
//			System.out.println(user.getId() + " " + user.getName() + " " + user.getPwd() + " " + user.getEmail() + " "
//					+ user.getPhonenum());
//		}
	}

	public static List<User> queryUsersById(String id) {
		String sql = "select * from user where id like \"%\"?\"%\" ";
		List<User> userList = MysqlOperation.queryUsers(sql, new String[] { id });
		return userList;
	}

	public static List<User> queryUsersByName(String name) {
		String sql = "select * from user where name like \"%\"?\"%\" ";
		List<User> userList = MysqlOperation.queryUsers(sql, new String[] { name });
		return userList;
	}

	public static List<User> queryUsersByPwd(String pwd) {
		String sql = "select * from user where pwd like \"%\"?\"%\" ";
		List<User> userList = MysqlOperation.queryUsers(sql, new String[] { pwd });
		return userList;
	}

	public static List<User> queryUsersByEmail(String email) {
		String sql = "select * from user where email like \"%\"?\"%\" ";
		List<User> userList = MysqlOperation.queryUsers(sql, new String[] { email });
		return userList;
	}

	public static List<User> queryUsersByPhonenum(String phonenum) {
		String sql = "select * from user where phonenum like \"%\"?\"%\" ";
		List<User> userList = MysqlOperation.queryUsers(sql, new String[] { phonenum });
		return userList;
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
