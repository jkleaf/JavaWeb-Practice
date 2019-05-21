package org.utils;

import java.util.List;

import org.bean.User;

public interface Query {//·ºÐÍ
	
	public int executeSQL(String sql,Object[] params);
		
	public void insert(User user);
	
	public void delete(String id);
	
	public int update(Object obj,String id,String[] fieldNames);
	
	public int update(String sql,Object[] params);
	
	public List queryRows(String sql,Object[] params);
	
	public List queryRows(String sql);
	
	public Object queryUniqueRow(String sql,Object[] paras);
	
	public Object queryValue(String sql,Object[] params);
	
}
