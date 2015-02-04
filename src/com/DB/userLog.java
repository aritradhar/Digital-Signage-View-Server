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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.Util.ENV;

public class userLog 
{
	public static void updateUserDB(String address, int row, int col, String link) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName(ENV.driver).newInstance();
		Connection conn = DriverManager.getConnection(ENV.url+ENV.dbName,ENV.userName,ENV.password);

		String sql = new String();

		sql = "INSERT INTO usertable (ip_address, link, time, row, col)" +
				"VALUES (?, ?, ?, ?, ?)";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, address);
		ps.setString(2, link);
		ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		ps.setInt(4, row);
		ps.setInt(5, col);


		ps.execute();
		ps.close();
		conn.close();
	}
	
	//test
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException 
	{
		updateUserDB("123", 1, 2, "asdf");
		System.out.println("done...");
	}
}
