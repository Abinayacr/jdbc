package com.quinnox.jdbc;
import java.util.Scanner;
import java.sql.*;

public class ChiefMinister {
	 Connection con;
	 Statement st;
	 ResultSet res;
	 PreparedStatement ps;
	ChiefMinister()
	{
		
	}
	
	public Connection getConnection()
	{
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");
			   System.out.println("Connecting to database...");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","monisha","redhat");  
		}
		catch(Exception e)
		{
			System.out.println("Error in nconnecting"+e);
		}
		return con;
	}
	
	public void insertCheifMinister(String cname, String qualification,String state,String enddate,String party) {
		try {
			con = getConnection();
		    ps = con.prepareStatement("insert into ChiefMinister values"
		    		+ "(ChiefMinister_seq.NEXTVAL,?,?,?,?,?)");
		   
		    ps.setString(1, cname);
		    ps.setString(2, qualification);
		    ps.setString(3, state);
		    ps.setString(4, enddate);
		    ps.setString(5, party);
		   
		    int i = ps.executeUpdate();
		    if (i != 0) {
		        System.out.println("Inserted");
		    } else {
		        System.out.println("not Inserted");
		    }
		    con.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		}
	
	public void getChiefMinister() 
	{
		try {
			   con = getConnection();
			   st = con.createStatement();
			   res = st.executeQuery("select * from ChiefMinister order by cid");
			   System.out.println("Cid\t Cname\t Qualification\t State\t  EndDate\t Party");
			   System.out.println("-----------------------------------------------------------");
			  while (res.next()) 
			  {
			    System.out.print(res.getInt(1)+"\t");
			    System.out.print(res.getString(2)+"\t\t");
			    System.out.print(res.getString(3)+"\t\t");
			    System.out.print(res.getInt(4)+"\t");
			    System.out.print(res.getString(5)+"\t\t");
			    System.out.print(res.getString(6)+"\t\t");
			    
			  }
			  System.out.println("-----------------------------------------------------------");
			  res.close();
			  con.close();
			  } 
			  catch (Exception e) 
			  {
			  System.out.println("Error in fetching data" + e);
			  }
	}
	
	public void updateChiefMinister(int cid,String enddate,String party) 
	{

		try {
			 con = getConnection();
		    ps = con.prepareStatement("update ChiefMinister"
		    		+ " set enddate=?+ set party=?+ where cid=?");
		    ps.setString(1, enddate);
		    ps.setString(2,party);
		    int i = ps.executeUpdate();
		    if (i != 0) {
		        System.out.println("EndDate and Party updated");
		    } else {
		        System.out.println("not updated");
		    }
		    con.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	
	public void deleteChiefMinister(int cid) 
	{
		try {
			con = getConnection();
		    ps = con.prepareStatement("delete from ChiefMinister where cid=?");
		    ps.setInt(1, cid);
		    int i = ps.executeUpdate();
		    if (i != 0) {
		        System.out.println("ChiefMinister deleted");
		    } else {
		        System.out.println("ChiefMInister Doesn't Exist");
		    }
		    con.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ChiefMinister c=new ChiefMinister();
		String cname,qualification,state,enddate,party;
		int cid;
		
		System.out.println("*******************************************************************");
		System.out.println("----------- ChiefMinister Management System ----------");
		while(true)
		{
		System.out.println("Press 1 for New ChiefMinister \t Press 2 to Display ChiefMinister");
		System.out.println("Press 3 for Update ChiefMinister \t Press 4 to Delete ChiefMinister");
		System.out.println("Press 5 for Exit"); 
			
		Scanner s=new Scanner(System.in);
		int option=s.nextInt();
		
		switch(option)
		{
		case 1: System.out.println("Enter ChiefMinister Name,Qualification,State,EndDate & Party:");
		        cname=s.next();
		        qualification=s.next();
		        state=s.next();  
		        enddate=s.next();
		        party=s.next();
			c.insertCheifMinister(cname,qualification,state,enddate,party); 
			break;
		case 2: c.getChiefMinister();
				break;
		case 3: System.out.println("Enter ChiefMinister Id & EndDate,Party to be Updated:");
        		cid=s.nextInt();
        		enddate=s.next();
        		party=s.next();
			c.updateChiefMinister(cid,enddate,party);
			break;
		case 4: System.out.println("Enter ChiefMinister Id  to be Deleted:");
			cid=s.nextInt();
			c.deleteChiefMinister(cid);
			break;
		case 5: System.out.println("Program Terminated");
		 	System.exit(0);
		
		default: System.out.println("Invalid Selection");
		          break;
		}
		
		
		}
	}

}
