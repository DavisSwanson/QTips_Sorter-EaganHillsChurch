package QTips_Sorting_System;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.sql.*;


public class addThemeWindow {

	private JFrame frame;
	private JTextField textField;
	

	/**
	 * Launch the application.
	 */
	public static void openWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addThemeWindow window = new addThemeWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public addThemeWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(148, 113, 144, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(148, 142, 162, 21);
		frame.getContentPane().add(lblNewLabel_1);
		
		//Calls addTheme in main class for theme in text area
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String theme = textField.getText();
				try{Main.addTheme(theme); lblNewLabel_1.setText("Theme Added");}
				catch(SQLException ex) {lblNewLabel_1.setText(ex.toString());}
				catch(ThemeMissingException ex) {lblNewLabel_1.setText("Theme has already\n been added");}
			}
		});
		btnNewButton.setBounds(320, 81, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		//Calls deleteTheme in main class for theme in text area
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String theme=textField.getText();
				if(theme.equals("misc")) {
					lblNewLabel_1.setText("Cannot delete misc theme");
				}
				else {
					try{Main.deleteTheme(theme.toLowerCase()); lblNewLabel_1.setText("Theme Deleted");}
					catch(SQLException ex) {lblNewLabel_1.setText(ex.toString());System.out.print(ex);}
					catch(ThemeMissingException ex) {lblNewLabel_1.setText("That theme doesn't exist");}
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Add New Theme");
		lblNewLabel.setBounds(171, 76, 121, 30);
		frame.getContentPane().add(lblNewLabel);
		
		//Return to main window
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				mainWindow.openWindow();
			}
		});
		btnNewButton_1.setBounds(320, 220, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		
		btnNewButton_2.setBounds(320, 142, 85, 21);
		frame.getContentPane().add(btnNewButton_2);
		
		//Replace theme with new theme by calling editTheme in main class
		JButton btnNewButton_3 = new JButton("Replace");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String theme=textField.getText();
				try{Main.editTheme(theme.toLowerCase()); lblNewLabel_1.setText("Theme Replaced");}
				catch(SQLException ex) {lblNewLabel_1.setText(ex.toString());System.out.print(ex);}
				catch(ThemeMissingException ex) {lblNewLabel_1.setText("That theme doesn't exist");}
			}
		});
		btnNewButton_3.setBounds(320, 112, 85, 21);
		frame.getContentPane().add(btnNewButton_3);
		
		

	}
}
