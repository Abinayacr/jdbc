package com.quinnox.jdbc;
import java.util.Scanner;
import java.sql.*;

public class StudentDb {
	private static String fail;
	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ps;
	 
	StudentDb()
	{
		con=null;
	}
	
	public Connection getConnection()
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","acr","redhat");
		}
		catch(Exception e)
		{
			System.out.println("error"+e);
		}
		return con;
	}
	
	public void insertStudent(String fname, String lname, String course, String result) 
	{
		try {
			con = getConnection();
		    ps = con.prepareStatement("insert into studentdb values"
		    		+ "(student_seq.NEXTVAL,?,?,?,?)");
		    ps.setString(1, fname);
		    ps.setString(2, lname);
		    ps.setString(3, course);
		    ps.setString(4, result);
		   
		    int i = ps.executeUpdate();
		    if (i != 0) {
		        System.out.println("Inserted");
		    } else {
		        System.out.println("not Inserted");
		    }
		    con.close();
		}catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public int getStudent() 
	{
		try {
			   con = getConnection();
			   st = con.createStatement();
			   rs = st.executeQuery("select * from studentdb order by rollno");
			   System.out.println("Rollno\t Fname\t Lname\t Course\t Result");
			   System.out.println("-----------------------------------------------------------");
			  while (rs.next()) 
			  {
			    System.out.print(rs.getInt(1)+"\t");
			    System.out.print(rs.getString(2)+"\t\t");
			    System.out.print(rs.getString(3)+"\t\t");
			    System.out.print(rs.getString(4)+"\t\t");
			    System.out.println(rs.getString(5));
			  }
			  System.out.println("-----------------------------------------------------------");
			  rs.close();
			  con.close();
			  } 
			  catch (Exception e) 
			  {
			  System.out.println("Error in fetching data" + e);
			  }
		return 0;
	}
	
	public void searchStudent(int rollno)
	{
		try {
		con=getConnection();
		ps = con.prepareStatement("select * from studentdb where rollno=?");
		ps.setInt(1, rollno);
		ResultSet rs = ps.executeQuery();
		  while (rs.next()) {
		    int rollno1 = rs.getInt("rollno");
		    String fname = rs.getString("fname");
		    String lname = rs.getString("lname");
		    String course = rs.getString("course");
		    String result = rs.getString("result");
		    System.out.println("Student found! The details are:");
		    System.out.println("RollNo : " + rollno1);
		    System.out.println("FName : " + fname);
		    System.out.println("LName : " + lname);
		    System.out.println("Course : " + course);
		    System.out.println("Result : " + result);
		    System.out.println();
		  }
		}catch (Exception e) {
	    e.printStackTrace();
	}
}
	
	public void deleteStudent(int rollno) 
	{
		try {
			con = getConnection();
		    ps = con.prepareStatement("delete from studentdb where rollno=?");
			ps.setInt(1, rollno);
		    int i = ps.executeUpdate();
		    if (i != 0) {
		        System.out.println("Student deleted");
		    } else {
		        System.out.println("Student Doesn't Exist");
		    }
		    con.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public void failedStudent(String fname)
	{
		try{
		con= getConnection();
		std = con.createStatement();
		rs=std.executeQuery("select fname from studentdb where result='fail'");
		System.out.println("Name:");
		System.out.println("-----------------------------------------------------------");
		ResultSet rs = null;
	    String result = null;
		while(rs.next())
		{
			System.out.println(rs.getString(2));
		}
		rs.close();
		con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
		public static void main(String[] args) {
		StudentDb std=new StudentDb();
		int rollno = 0;
		//String fname;
		
		System.out.println("************** AMCEC **************");
		System.out.println("----------- Student Management System ----------");
		while(true)
		{
		System.out.println("Press 1 for New Student \t Press 2 to Display Students \t  Press 3 to search Students");
		System.out.println("Press 4 for Delete Student \t Press 5 for Failed Students");
		System.out.println("Press 6 for Exit"); 
			
		Scanner s = new Scanner(System.in);
		int option=s.nextInt();
		
		switch(option)
		{
		case 1: System.out.println("Student FName, Lname, Course & Result :");
				String fname=s.next();
		        String lname=s.next();
		        String course=s.next();
		        String result1=s.next();
		        std.insertStudent(fname, lname, course, result1);
		        break;
		case 2: std.getStudent();
				break;
		case 3: System.out.println("Enter Rollno to be searched:");
        		rollno=s.nextInt();
        		std.searchStudent(rollno);
        		break;
		case 4: System.out.println("Enter Rollno  to be Deleted:");
				rollno=s.nextInt();
				std.deleteStudent(rollno);
				break;
		case 5: System.out.println("Student FName:");
				//String fname;
				std.failedStudent(fname);
				break;
		
		case 6: System.out.println("Program Terminated");
 				System.exit(0);
 				break;
		
		default: System.out.println("Invalid Selection");
		         break;
		}
		}
	}
}