package com.quinnox.jdbc;
import java.io.*;
public class DataIOStreamDemo{
	 public static void main(String args[])throws IOException{
	 DataInputStream d = new DataInputStream(new FileInputStream("d:/abc/countries.txt")); 
	DataOutputStream out = new DataOutputStream(new FileOutputStream("d:/abc/test_countries.txt")); 

	String count;
	while((count = d.readLine()) != null){ 
		String u = count.toUpperCase(); 
		System.out.println(u); out.writeBytes(u + " ,"); }
	 	d.close(); 
		out.close();  } } 