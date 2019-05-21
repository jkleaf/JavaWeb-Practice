package org.test;

import org.utils.JDBCUtils;

public class Test {
	
	public static void main(String[] args) {
		System.out.println(JDBCUtils.getConnection());
	}
}
