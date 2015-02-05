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


package com.signageBoard;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

import com.ImageGrid.Gui;
import com.ImageGrid.ImgGridDataType;
import com.ImageGrid.SignageGridDataType;

public class SignageView extends JFrame 
{
	private JLabel[] jLabel;
	private Timer timer;
	public static int gridx, gridy;
	public static Random rand = new Random();
	public static ImgGridDataType IGDT = new ImgGridDataType();
	public static SignageGridDataType SGDT;
	public static int signage_no;

	public SignageView(int gridx, int gridy, int tm, int sn, SignageGridDataType S) 
	{
		SGDT = S;
		signage_no = sn;
		
		this.setTitle("Signage board : " + sn);
		

		Gui.gridx = gridx;
		Gui.gridy = gridy;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);

		JPanel panel = new JPanel(new GridLayout(gridx, gridy, 3, 3));
		panel.setBackground(Color.BLACK);
		
		jLabel = new JLabel[gridx * gridy];
		for(int i = 0; i < gridx * gridy; i++)
		{
			String currentFile = (!AdDataType.AdMap.containsKey(i)) ? "C:\\ImageDB\\default.jpg" : AdDataType.AdMap.get(i);
			ImageIcon image = new ImageIcon(currentFile);
			jLabel[i] = new JLabel(image, JLabel.CENTER);
			jLabel[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			panel.add(jLabel[i]);
			
			IGDT.imgGridMap.put(i, currentFile);
			SGDT.SignageGridMap.put(signage_no, IGDT);
			
			System.out.println("Signage board no : " + signage_no + " grid : " + i + "  " + currentFile);
		}


		timer = new Timer(tm, new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{

				for(int i = 0; i < Gui.gridx * Gui.gridy; i++)
				{
					
					String currentFile = (!AdDataType.AdMap.containsKey(i)) ? "C:\\ImageDB\\default.jpg" : AdDataType.AdMap.get(i);
					jLabel[i].setIcon(new ImageIcon(currentFile));
					
					IGDT.imgGridMap.put(i, currentFile);				
					SGDT.SignageGridMap.put(signage_no, IGDT);
					
					System.out.println("Signage board no : " + signage_no + " grid : " + i + "  " + currentFile);
				}
			}
		});
		timer.start();

		this.getContentPane().add(panel);
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) 
	{
		new SignageView(4,4,2000,8, new SignageGridDataType());
	}
}
