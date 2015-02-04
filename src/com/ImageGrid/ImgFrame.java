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


package com.ImageGrid;

import java.io.File;

import javax.swing.SwingUtilities;

public class ImgFrame 
{
	public static void main(String[] args) 
	{

		final File files[] = new File("C:\\ImageDB").listFiles();
		final int gridx = 4;
		final int gridy = 4;
		
		SwingUtilities.invokeLater(new Runnable() 
		{

			@Override
			public void run() 
			{
				new Gui(files, gridx, gridy, 1000);
			}
		});

	}
}
