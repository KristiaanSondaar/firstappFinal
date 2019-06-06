package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPostgresDaoImpl extends PostgresBaseDao implements UserDao {

	
	public String findRoleForUser (String name, String pass) throws SQLException {
		Connection conn = super.getConnection();
		String strQuery = "select role from useraccount where username = ? and password = ? ";
		PreparedStatement ps = conn.prepareStatement(strQuery);
		

		
		ps.setString(1,name);
		ps.setString(2, pass);
		ResultSet dbResultSet = ps.executeQuery();

		

		dbResultSet.next();
		String role = dbResultSet.getString("role");
		return role;
		}
}
	
