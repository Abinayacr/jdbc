package com.quinnox.jdbc;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class UpdateDemo {
	private static Scanner s;

	public static void main(String[] args) {
		Connection con;
		PreparedStatement pstmt;
		ResultSet rs;
		int cnt=0;
		String sqlUpdate;
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
			sqlUpdate= " UPDATE employees " + " SET job_id=? " + " where employee_id=? ";
			pstmt=con.prepareStatement(sqlUpdate);
			s = new Scanner(System.in);
			System.out.println("enter employee id:");
			int eid=s.nextInt();

			s.nextLine();
			System.out.println("enter new jo:");
			String strm=s.nextLine();
			pstmt.setString(1,strm);
			pstmt.setInt(2,eid);
			int rowAffected=pstmt.executeUpdate();
			System.out.println("row affected"+rowAffected);
			strm="Sales Head";
			eid=1370;
			pstmt.setString(1, strm);
			pstmt.setInt(2, eid);
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

			