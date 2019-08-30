package com.quinnox.jdbc;
import java.sql.*;

public class CallableDemo {
	Connection con;
	CallableStatement cstmt;
//	ResultSet rs;
	
	CallableDemo()
	{
	}
	
	void createConnection()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void addDept() {
		try {
			String query= "{call adjust_salary(100,10) }";
			cstmt = con.prepareCall(query);
			int cnt = cstmt.executeUpdate();
			if(cnt>0) {
				System.out.println("Salary updated");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public static void main(String[] args) {
		CallableDemo cd1 = new CallableDemo();
		cd1.createConnection();
		cd1.addDept();
	}
}
