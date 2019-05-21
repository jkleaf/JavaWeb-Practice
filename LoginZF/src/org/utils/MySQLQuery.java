package org.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bean.User;

public class MySQLQuery implements Query{

	@Override
	public int executeSQL(String sql, Object[] params) {
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
	@Override
	public void insert(User user) {//User
		if(!query(user)) {
			String sql="insert into user values (?,?,?,?)";
			List<Object> params=new ArrayList<>();
			params.add("");//
			params.add(user.getAccount());
			params.add(user.getPassword());
			params.add(user.getToken());
			executeSQL(sql, params.toArray());
		}
	}
	
	/*
	 *	User Query 
	 */
	
	public boolean query(User user) {
		String sql="select account,password from user where account=? and password=?";
		List<String> params=new ArrayList<>();
		params.add(user.getAccount());
		params.add(user.getPassword());
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
	
	@Override
	public void delete(String id) {
		String sql="delete from user where id=?";
		executeSQL(sql, new Object[]{id});
	}

	/**/
	@Override
	public int update(Object obj, String id,String[] fieldNames) {
		StringBuilder sql=new StringBuilder("update user set ");
		List<Object> params=new ArrayList<>();
		User user=(User) obj;
		for (String fname : fieldNames) {
			sql.append(fname+"=?,");
		}
		return 0;
	}
	
	@Override
	public int update(String sql,Object[] params) {
		return executeSQL(sql, params);
	}
	
	@Override
	public List queryRows(String sql,Object[] params) {
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
	
	@Override
	public List queryRows(String sql) {
		return queryRows(sql,null);
	}

	@Override
	public Object queryUniqueRow(String sql, Object[] paras) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object queryValue(String sql,Object[] params) {
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
