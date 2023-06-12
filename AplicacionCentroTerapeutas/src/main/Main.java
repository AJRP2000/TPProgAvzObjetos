package main;

import javax.swing.*;

import clasesVista.JFrameLogIn;

public class Main{
	public static void main(String [] args) {
		JFrame frame = new JFrameLogIn();
		frame.setSize(800,650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
		
	}
}


