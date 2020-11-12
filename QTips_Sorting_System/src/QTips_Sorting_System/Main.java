package QTips_Sorting_System;

import java.sql.*;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class Main {

	public static Connection connect() throws SQLException 
	{
		Connection con = DriverManager.getConnection(
		        "jdbc:sqlserver://LAPTOP-GT12R9UA\\SQLEXPRESS:1433;database=QTipsSystem;", "user","password");
		//String question = JOptionPane.showInputDialog(null, "Enter the students question");
	    //addTheme(con);
	    
	    //String theme=findTheme(question, con);
	    //JOptionPane.showMessageDialog(null, "The theme is "+theme);
		
		return con;

	}
	
	public static void addTheme(Connection con) throws SQLException {
		String[] themes = getThemes(con);
		boolean cont=true;
		String theme="";
		while(cont) {
			cont=false;
			theme = JOptionPane.showInputDialog(null,"Enter a theme to add");
			for(String t : themes) {
				if(t.equals(theme)) {
					JOptionPane.showMessageDialog(null, "That theme has already been added.\n");
					cont=true;
				}
			}
		}
		
		PreparedStatement addTheme = con.prepareStatement("INSERT INTO Themes VALUES ('"+theme+"')");
	    addTheme.executeUpdate();
		
	}
	
	public static String[] getThemes(Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("SELECT theme FROM Themes");
		PreparedStatement stmt2 = con.prepareStatement("SELECT theme FROM Themes");
		
		ResultSet rslt = stmt.executeQuery();
		ResultSet rslt2 = stmt2.executeQuery();
		    
		int size=0;
	    while(rslt2.next()) {size++;}
	    
	    int i=0; String[] themes = new String[size];
	    
	    while(rslt.next()) 
	    {
		    themes[i]=(rslt.getString("theme")).replaceAll(" ", "");
		    i++;
	    }
	    
	    return themes;
	}
	
	public static String findTheme(String s, Connection con) throws SQLException
	{
		
		String[] themes = getThemes(con);
		
	    String[] question = null;
		question=s.split(" ");
		int[] counts=new int[themes.length];
		Arrays.fill(counts, 0);
		
		for(String word: question)
		{
			for(int j=0;j<themes.length;j++) {
				if(word.equals(themes[j])) {
					counts[j]+=1;
				}
			}
		}
		
		int high_index=-1, high_count=0;
		
		for(int i=0; i<counts.length;i++) {
			if(counts[i]>high_count) {
				high_index=i;
			}
		}
		
		if(high_index==-1) {s="misc";}
		else {s=themes[high_index];}
		
		return s;
	}

}
