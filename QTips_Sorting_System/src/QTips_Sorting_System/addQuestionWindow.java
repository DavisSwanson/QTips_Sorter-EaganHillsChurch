package QTips_Sorting_System;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JTextArea;

public class addQuestionWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void openWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addQuestionWindow window = new addQuestionWindow();
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
	public addQuestionWindow() {
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
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(135, 158, 156, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Add Question");
		lblNewLabel.setBounds(175, 37, 73, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(135, 73, 149, 78);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String question = textArea.getText();
				try{Main.addQuestion(question); lblNewLabel_1.setText("Question Added");}
				catch(SQLException ex) {lblNewLabel_1.setText(ex.toString());}
			}
		});
		btnNewButton.setBounds(320, 100, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
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
