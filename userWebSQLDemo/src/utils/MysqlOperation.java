package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.JDBCUtils;

import bean.User;

public class MysqlOperation {

	public static List<User> queryUsers(String sql, String[] params) {
		List<User> userList = new ArrayList<>();
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setString(i + 1, params[i]);
				}
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setPwd(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPhonenum(rs.getString(5));
				userList.add(user);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.err.println("SQL Exception");
		} finally {
			JDBCUtils.Closeall(rs, ps, conn);
		}
		return userList;
	}

	public static int executeSQL(String sql, Object[] params) {
		Connection conn = JDBCUtils.getConnection();
		int count = 0;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++)
					ps.setObject(i + 1, params[i]);
			}
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.Closeall(ps, conn);
		}
		return count;
	}

	public static int insertUser(User user) {
		String sql = "insert into user values (?,?,?,?,?)";
		List<String> params = new ArrayList<>();
		params.add(user.getId());
		params.add(user.getName());
		params.add(user.getPwd());
		params.add(user.getEmail());
		params.add(user.getPhonenum());
		return executeSQL(sql, params.toArray());
	}

	public static int deleteUser(String id) {
		String sql = "delete from user where id=?";
		return executeSQL(sql, new Object[] { id });
	}

	public static int updateUser(String[] params) {
		String sql = "update user set name=?,pwd=?,email=?,phonenum=? where id=?";
		return executeSQL(sql, params);
	}

}
