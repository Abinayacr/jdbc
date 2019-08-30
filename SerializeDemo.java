package com.quinnox.jdbc;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeDemo 
{
	public static void main(String[] args)
	{
		Employee e=new Employee();
		e.name="Micheal Khan";
		e.address="Castle street, Bangalore";
		e.SSN=11122333;
		e.number=101;
		try
		{
			FileOutputStream fileOut= new FileOutputStream("d:/abc/employee.ser");
			ObjectOutputStream out= new ObjectOutputStream(fileOut);
			out.writeObject(e);
			out.close();
			fileOut.close();
			System.out.println("serialized data is saved in ---");
		}catch(IOException i)
		{
			i.printStackTrace();
		}
	}
}
