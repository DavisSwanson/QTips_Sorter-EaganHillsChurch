package QTips_Sorting_System;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
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
		lblNewLabel_1.setBounds(148, 142, 206, 21);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String theme = textField.getText();
				try{Main.addTheme(theme); lblNewLabel_1.setText("Theme Added");}
				catch(SQLException ex) {lblNewLabel_1.setText(ex.toString());}
				catch(ThemeExistsException ex) {lblNewLabel_1.setText("Theme has already\n been added");}
			}
		});
		btnNewButton.setBounds(320, 112, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Add New Theme");
		lblNewLabel.setBounds(171, 76, 121, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				mainWindow.openWindow();
			}
		});
		btnNewButton_1.setBounds(320, 172, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		

	}
}
