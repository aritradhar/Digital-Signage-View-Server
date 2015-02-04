//*************************************************************************************
//*********************************************************************************** *
//author Aritra Dhar 																* *
//Research Engineer																  	* *
//Xerox Research Center India													    * *
//Bangalore, India																    * *
//--------------------------------------------------------------------------------- * * 
///////////////////////////////////////////////// 									* *
//The program will do the following:::: // 											* *
///////////////////////////////////////////////// 									* *
//version 1.0 																		* *
//*********************************************************************************** *
//*************************************************************************************
package com.DB;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Util.ENV;

public class CreateDB 
{
	String path;
	File[] files;
	
	public CreateDB()
	{
		
	}
	
	public CreateDB(String path) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		this.path = path;
		File file = new File(path);
		
		if(!file.isDirectory())
		{
			throw new IllegalArgumentException("Wrong path passed");
		}
		
		this.files = file.listFiles();
		
		this.loadToDB();
	}
	
	private void loadToDB() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName(ENV.driver).newInstance();
		Connection conn = DriverManager.getConnection(ENV.url+ENV.dbName,ENV.userName,ENV.password);

		String sql = new String();
		PreparedStatement ps = null;
		for(int i = 0; i < files.length; i++)
		{
			sql = "INSERT INTO advtab (serial_no, link)" +
			        "VALUES (?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ps.setString(2, this.files[i].toString());
			
		}
		conn.close();
	}
	
	public void uploadImg(String link) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName(ENV.driver).newInstance();
		Connection conn = DriverManager.getConnection(ENV.url+ENV.dbName,ENV.userName,ENV.password);
		
		String sql = "SELECT MAX(serial_no) FROM advtab";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		int sr_no = 0;
		
		while(rs.next())
		{
			sr_no = rs.getInt(1) + 1;
		}
		rs.close();
		
		sql = "INSERT INTO advtab (serial_no, link)" +
		        "VALUES (?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, sr_no);
		ps.setString(2, link);
		ps.execute();
		ps.close();
		conn.close();
	}
	
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException 
	{
		//new CreateDB("C:\\ImageDB");
		new CreateDB().uploadImg("AAAAAAAAAAAAAA");
		System.out.println("done..");
	}
}
