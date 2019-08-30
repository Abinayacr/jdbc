package com.quinnox.jdbc;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DeleteDemo {
	public static void main(String[] args) {
		Connection con;
		Statement stmt;
		ResultSet rs;
		int cnt=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
			String sql="Delete from locations where city='Tokyo'";
			System.out.println("");
			stmt=con.createStatement();
			cnt=stmt.executeUpdate(sql);
			if(cnt>0) {
				System.out.println("rec deleted");
			}
			con.close();
		}
		catch(Exception ce) {
			System.out.println(ce);
		}
	}				
}