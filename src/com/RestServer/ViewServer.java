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

package com.RestServer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.SwingUtilities;

import com.DB.userLog;
import com.ImageGrid.Gui;
import com.ImageGrid.SignageGridDataType;

/**
 * Servlet implementation class ViewServer
 */
@WebServlet("/ViewServer")
public class ViewServer extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
      
	public static final int gridx = 3;
	public static final int gridy = 3;
	public static final int time = 10000;
	
	public static SignageGridDataType SGDT = new SignageGridDataType();
	
    public ViewServer() 
    {
        super();

        final File files[] = new File("C:\\ImageDB").listFiles();
        
       		
		SwingUtilities.invokeLater(new Runnable() 
		{

			@Override
			public void run() 
			{
				new Gui(files, gridx, gridy, time, 4, SGDT);
			}
		});
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/plain");
		
		String response = "GET request not supported";
		res.getOutputStream().write(response.getBytes());
		res.getOutputStream().flush();
		res.getOutputStream().close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("image/jpeg");
		
		int row = Integer.parseInt(request.getParameter("row"));
		int col = Integer.parseInt(request.getParameter("col"));
		
		int pos = row * gridx + col;
		
		String fileName = Gui.IGDT.imgGridMap.get(pos);
		
		String ip = request.getRemoteAddr();
		String port = new Integer(request.getRemotePort()).toString();
		
		try 
		{
			userLog.updateUserDB(ip + "$" + port, row, col, fileName);
		} 
		
		catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		File f = new File(fileName);
		BufferedImage bi = ImageIO.read(f);
		OutputStream out = res.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();
		
		res.getOutputStream().flush();
		res.getOutputStream().close();
		
		/*
		byte[] fileBytes = Files.readAllBytes(new File(fileName).toPath());
		
		res.getOutputStream().write(fileBytes);
		res.getOutputStream().flush();
		res.getOutputStream().close();
		*/
	}

}
