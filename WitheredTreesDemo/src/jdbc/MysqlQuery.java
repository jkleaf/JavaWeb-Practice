package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.User;

public class MysqlQuery {

	public static int executeSQL(String sql, Object[] params) {
		Connection conn=JDBCUtils.getConnection();
		int count=0;
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			if(params!=null) {
				for(int i=0;i<params.length;i++)
					ps.setObject(i+1, params[i]);
			}
			count=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.Closeall(ps,conn);
		}
		return count;
	}

	/*
	 *	User Insert 
	 */
//	public void insert(User user) {//User
//		if(!query(user)) {
//			String sql="insert into user values (?,?,?,?)";
//			List<Object> params=new ArrayList<>();
//			params.add("");//
//			params.add(user.getAccount());
//			params.add(user.getPassword());
//			executeSQL(sql, params.toArray());
//		}
//	}
	
	/*
	 *	User Query 
	 */
	
	public static boolean queryUser(User user) {
		String sql="select account,password from user where account=? and password=?";
		List<String> params=new ArrayList<>();
		params.add(user.getAccount());//
		params.add(user.getPassword());//
		Connection conn=JDBCUtils.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			if(params!=null) {
				for (int i = 0; i < params.size(); i++) {
					ps.setString(i+1, params.get(i));
				}
			}
			rs=ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.Closeall(ps,rs,conn);
		}
		return false;
	}
	
	public static User queryUser(String account) {
		String sql="select * from user where account=?";
		Connection conn=JDBCUtils.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, account);
			rs=ps.executeQuery();
			if(!rs.next()) {
				return null;
			}else {
				return new User(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.Closeall(ps,rs,conn);
		}
		return null;
	}
	
//	public void delete(String id) {
//		String sql="delete from user where id=?";
//		executeSQL(sql, new Object[]{id});
//	}

	/**/
//	public int update(Object obj, String id,String[] fieldNames) {
//		StringBuilder sql=new StringBuilder("update user set ");
//		List<Object> params=new ArrayList<>();
//		User user=(User) obj;
//		for (String fname : fieldNames) {
//			sql.append(fname+"=?,");
//		}
//		return 0;
//	}
	
//	public int update(String sql,Object[] params) {
//		return executeSQL(sql, params);
//	}
	
	public static List queryRows(String sql,Object[] params) {
		Connection conn=JDBCUtils.getConnection();
		List<Map<String, Object>> resList=new ArrayList<>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			if(params!=null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			rs=ps.executeQuery();
			ResultSetMetaData metaData=rs.getMetaData();
			while(rs.next()) {
				Map<String, Object> columnMap=new HashMap<>();
				for(int i=0;i<metaData.getColumnCount();i++) {
					String columnName=metaData.getColumnLabel(i+1);
					Object columnValue=rs.getObject(i+1);
					columnMap.put(columnName, columnValue);
//					System.out.print(columnName+":"+columnValue+" ");//
				}
//				System.out.println();//
				resList.add(columnMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.Closeall(ps,rs,conn);
		}
		return resList;
	}
	
	public static List queryRows(String sql) {
		return queryRows(sql,null);
	}

	public static Object queryUniqueRow(String sql, Object[] paras) {
		return null;
	}

	public static Object queryValue(String sql,Object[] params) {
		Connection conn=JDBCUtils.getConnection();
		Object value=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			if(params!=null) {
				for (int i = 0; i < params.length; i++) {//select 1 from tablename where col = 'col' limit 1;
					ps.setObject(i+1, params[i]);
				}
			}
			rs=ps.executeQuery();
			while(rs.next()){
				value=rs.getObject(1);//select count(*) from table
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.Closeall(ps,conn);
		}
		return value;
	}

}

