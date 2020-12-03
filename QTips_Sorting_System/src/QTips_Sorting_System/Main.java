package QTips_Sorting_System;

import java.sql.*;
import java.util.Arrays;

import javax.swing.JOptionPane;


public class Main {

	public static Connection connect() throws SQLException 
	{
		Connection con = DriverManager.getConnection(
		        "jdbc:sqlserver://LAPTOP-GT12R9UA\\SQLEXPRESS:1433;database=QTipsSystem;", "user","password");
		return con;

	}
	
	public static void addQuestion(String question) throws SQLException {
		Connection con = connect();
		String theme = findTheme(question);
		
		for(int i=0; i<question.length(); i++) {
			if(Character.isWhitespace(question.charAt(i)));
				if(i%30==0) {
					question = question.substring(0,i) + "\n" + question.substring(i);}
		}
		
		
		
		PreparedStatement addQuestion = con.prepareStatement("INSERT INTO Question (theme, contents) VALUES ('"+theme+"','"+question+"')");
		addQuestion.executeUpdate();
	}
	
	public static void editTheme(String oldTheme) throws SQLException, ThemeMissingException {
		
		if(themeExists(oldTheme.toLowerCase())==false) {throw new ThemeMissingException("",null);}
		String newTheme = JOptionPane.showInputDialog(null,"Enter the new theme to replace "+oldTheme);
		Connection con = connect();
		PreparedStatement stmt = con.prepareStatement("UPDATE Question SET theme='misc' WHERE theme='"+oldTheme.toLowerCase()+"'");
		PreparedStatement stmt2 = con.prepareStatement("UPDATE Themes SET theme='"+newTheme.toLowerCase()+"' WHERE theme='"+oldTheme.toLowerCase()+"'");
	
		stmt.execute();
		stmt2.execute();
	}
	
	public static boolean themeExists(String theme) throws SQLException{
		String[] themes = getThemes(); boolean exists=false;
		for(String t : themes) {if(t.equals(theme)){exists=true;}};
		
		return exists;
	}
	
	public static void deleteTheme(String theme) throws SQLException, ThemeMissingException {
		Connection con = connect();
		PreparedStatement stmt = con.prepareStatement("DELETE FROM Themes WHERE theme='"+theme.toLowerCase()+"'");
		PreparedStatement stmt2 = con.prepareStatement("DELETE FROM Question WHERE theme='"+theme.toLowerCase()+"'");
		
		if(themeExists(theme.toLowerCase())==false) {throw new ThemeMissingException("",null);}
		
		stmt2.execute();
		stmt.execute();
	}
	
	public static String[][] getQuestions(String theme) throws SQLException, ThemeMissingException {
		Connection con = connect();
		
		PreparedStatement stmt = con.prepareStatement("SELECT entryID FROM Question WHERE theme='"+theme.toLowerCase()+"'");
		PreparedStatement stmt2 = con.prepareStatement("SELECT theme, contents FROM Question WHERE theme='"+theme.toLowerCase()+"'");
		
		ResultSet rslt = stmt.executeQuery();
		ResultSet rslt2 = stmt2.executeQuery();
		
		if(themeExists(theme.toLowerCase())==false){throw new ThemeMissingException("",null);};
		int rows=0;
	    while(rslt.next()) {rows++;}
	    
	    String[][] questions = new String[rows][2];
	    
	    int i=0; while(rslt2.next()) {
	    	questions[i][0]=rslt2.getString("theme");
	    	questions[i][1]=rslt2.getString("contents");
	    	i++;
	    }
	    
	    return questions;
		
	}
	
	public static void addTheme(String theme) throws SQLException, ThemeMissingException {
		Connection con = connect();

		if(themeExists(theme.toLowerCase())==false) {
			PreparedStatement addTheme = con.prepareStatement("INSERT INTO Themes VALUES ('"+theme.toLowerCase()+"')");
		    addTheme.executeUpdate();
		}
		else {
			throw new ThemeMissingException("",null);
		}
		
	}
		
		
	public static String[] getThemes() throws SQLException {
		Connection con = connect();
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
	
	public static String findTheme(String s) throws SQLException
	{
		String[] themes = getThemes();
		
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
