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

@SuppressWarnings("serial")
public class Gui extends JFrame {

	private JLabel[] jLabel;
	private Timer timer;
	public static File[] files;
	public static int gridx, gridy;
	public static Random rand = new Random();
	public static ImgGridDataType IGDT = new ImgGridDataType();
	public static SignageGridDataType SGDT;
	public static int signage_no;

	public Gui(File[] files, int gridx, int gridy, int tm, int sn, SignageGridDataType S) 
	{
		SGDT = S;
		signage_no = sn;
		
		this.setTitle("Signage board : " + sn);
		
		Gui.files = files;

		Gui.gridx = gridx;
		Gui.gridy = gridy;
		
		int len = files.length;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);

		JPanel panel = new JPanel(new GridLayout(gridx, gridy, 3, 3));
		panel.setBackground(Color.BLACK);
		
		jLabel = new JLabel[gridx * gridy];
		for(int i = 0; i < gridx * gridy; i++)
		{
			String currentFile = Gui.files[rand.nextInt(len - 1)].toString();
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
					//if(i%2 == 0)
						//continue;
					
					String currentFile = Gui.files[rand.nextInt(Gui.files.length - 1)].toString();
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
}
