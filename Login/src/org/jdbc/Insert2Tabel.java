package org.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bean.Register;
import org.bean.Users;

public class Insert2Tabel {
	
	public void insertUsers(Users users) {
		Connection conn=JDBCUtils.getConnection();
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement("insert into users values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, users.getU_id());
			ps.setString(2, users.getU_name());
			ps.setString(3, users.getU_major());
			ps.setDate(4, users.getU_createDay());
			ps.setInt(5, users.getU_age());
			ps.setString(6, users.getU_sex());
			ps.setString(7, users.getU_email());
			ps.setString(8, users.getU_tel());
			ps.setInt(9, users.getU_canb());
			ps.setInt(10, users.getU_hasb());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.Closeall(ps,conn);
		}
	}
	
	public void insertRegister(Register register) {
		Connection conn=JDBCUtils.getConnection();
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement("insert into register values(?,?,?)");
			ps.setString(1, register.getR_id());
			ps.setString(2, register.getR_password());
			ps.setString(3, register.getR_email());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.Closeall(ps,conn);
		}
	}
}
