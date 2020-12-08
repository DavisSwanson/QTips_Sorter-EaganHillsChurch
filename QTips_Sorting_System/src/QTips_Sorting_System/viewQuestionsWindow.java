package QTips_Sorting_System;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.sql.*;


public class viewQuestionsWindow {

	private JFrame frame;
	private JTextField textField;
	private static JTable table = new JTable();

	
	/**
	 * Launch the application.
	 */
	public static void openWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewQuestionsWindow window = new viewQuestionsWindow();
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
	

	public viewQuestionsWindow() {
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
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				mainWindow.openWindow();
			}
		});
		btnNewButton.setBounds(308, 172, 97, 21);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(147, 62, 116, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(117, 220, 189, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(111,91,187,105);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
				     int row = table.rowAtPoint(evt.getPoint());
				     int col = table.columnAtPoint(evt.getPoint());
				     if (row >= 0 && col >= 0) {
				          JOptionPane.showMessageDialog(null, table.getValueAt(row,col));
				     	}
					}
				});
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] questions = {{null,null}};
				String[] header = {"Theme","Contents"};
				String theme=textField.getText();
				try{questions = Main.getQuestions(theme.toLowerCase()); lblNewLabel_1.setText("Displaying questions about "+theme);}
				catch(SQLException ex) {lblNewLabel_1.setText(ex.toString());}
				catch(ThemeMissingException ex) {lblNewLabel_1.setText("That theme has not been added.");};
				
				DefaultTableModel model = new DefaultTableModel(questions,header);
				table.setModel(model);
			}
		});
		
		
		btnNewButton_1.setBounds(273, 61, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Search Questions By Theme");
		lblNewLabel.setBounds(131, 30, 175, 28);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("View Misc");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] questions = {{null,null}};
				String[] header = {"Theme","Contents"};
				String theme="misc";
				try{questions = Main.getQuestions(theme.toLowerCase()); lblNewLabel_1.setText("Displaying questions about "+theme);}
				catch(SQLException ex) {lblNewLabel_1.setText(ex.toString());}
				catch(ThemeMissingException ex) {lblNewLabel_1.setText("That theme has not been added.");};
				
				DefaultTableModel model = new DefaultTableModel(questions,header);
				table.setModel(model);
			}
		});
		btnNewButton_2.setBounds(308, 91, 97, 21);
		frame.getContentPane().add(btnNewButton_2);
	
		
		
	}
	
}
