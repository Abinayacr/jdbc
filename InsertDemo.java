package com.quinnox.jdbc;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class InsertDemo {
	public static void main(String[] args) {
		Connection con;
		Statement stmt;
		int cnt=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
			String str="insert into departments values(31,'sports',204,2500)";
			stmt=con.createStatement();
			int rowcount=stmt.executeUpdate(str);
			if(rowcount>0)
			{
				System.out.println("rec ins succsessfully");
			}
		
		String str1="select count(location_id) from locations";
		ResultSet rs=stmt.executeQuery(str1);
		while(rs.next())
		{
			cnt=rs.getInt(1);
		}
		System.out.println("tot no of rec is:"+cnt);
		con.close();
		}
	catch(Exception ce) 
		{
		System.out.println(ce);
	}
}
}
