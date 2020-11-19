package QTips_Sorting_System;

import java.sql.*;
import java.util.Arrays;


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
		
		PreparedStatement addQuestion = con.prepareStatement("INSERT INTO Question (theme, contents) VALUES ('"+theme+"','"+question+"')");
		addQuestion.executeUpdate();
	}
	
	public static String[][] getQuestions(String theme) throws SQLException, ThemeMissingException {
		Connection con = connect();
		
		PreparedStatement stmt = con.prepareStatement("SELECT entryID FROM Question WHERE theme='"+theme+"'");
		PreparedStatement stmt2 = con.prepareStatement("SELECT theme, contents FROM Question WHERE theme='"+theme+"'");
		
		ResultSet rslt = stmt.executeQuery();
		ResultSet rslt2 = stmt2.executeQuery();
		
		String[] themes = getThemes(); boolean exists=false;
		for(String t : themes) {if(t.equals(theme)){exists=true;}};
		if(exists==false) {throw new ThemeMissingException("",null);};
		int rows=0;
	    while(rslt.next()) {rows++;}
	    
	    String[][] questions = new String[rows][2];
	    
	    int i=0; while(rslt2.next()) {
	    	questions[i][0]=rslt2.getString("theme");
	    	questions[i][1]=rslt2.getString("contents");
	    }
	    
	    return questions;
		
	}
	
	public static void addTheme(String theme) throws SQLException, ThemeExistsException {
		Connection con = connect();
		String[] themes = getThemes();
		boolean add=true;
		for(String t : themes) {
			if(t.equals(theme)) {
				throw new ThemeExistsException("Error", null);
			}
		}
		if(add==true) {
			System.out.print("test");
			PreparedStatement addTheme = con.prepareStatement("INSERT INTO Themes VALUES ('"+theme+"')");
		    addTheme.executeUpdate();
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
