package QTips_Sorting_System;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class mainWindow {

	private JFrame frame;
	/**
	 * @wbp.nonvisual location=-187,284
	 */

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		openWindow();
	}
	public static void openWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
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
	public mainWindow() {
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
		
		//Calls addQuestion window
		JButton btnNewButton = new JButton("Add Question");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				addQuestionWindow.openWindow();
			}
		});
		btnNewButton.setBounds(150, 50, 125, 21);
		frame.getContentPane().add(btnNewButton);
		
		//Calls themeWindow
		JButton btnNewButton_1 = new JButton("Edit Themes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				addThemeWindow.openWindow();
			}
		});
		btnNewButton_1.setBounds(150, 98, 125, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		//Calls ViewQuestion window
		JButton btnNewButton_2 = new JButton("View Questions");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				viewQuestionsWindow.openWindow();
			}
		});
		btnNewButton_2.setBounds(150, 149, 125, 21);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("QTips Sorting System");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(100, 10, 221, 21);
		frame.getContentPane().add(lblNewLabel);
	}
}
