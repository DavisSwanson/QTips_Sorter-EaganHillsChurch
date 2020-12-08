package QTips_Sorting_System;

import java.sql.*;
import java.util.Arrays;

import javax.swing.JOptionPane;


public class Main {

	public static Connection connect() throws SQLException //Connects to database
	{
		Connection con = DriverManager.getConnection(
		        "jdbc:sqlserver://LAPTOP-GT12R9UA\\SQLEXPRESS:1433;database=QTipsSystem;", "user","password");
		return con;

	}
	
	public static void addQuestion(String question) throws SQLException {//Adds question to Question table
		Connection con = connect();
		String theme = findTheme(question);
		
		for(int i=0; i<question.length(); i++) {//Format string 
			if(Character.isWhitespace(question.charAt(i)));
				if(i%30==0) {
					question = question.substring(0,i) + "\n" + question.substring(i);}
		}
		
		
		//Add to database
		PreparedStatement addQuestion = con.prepareStatement("INSERT INTO Question (theme, contents) VALUES ('"+theme+"','"+question+"')");
		addQuestion.executeUpdate();
	}
	
	public static void editTheme(String oldTheme) throws SQLException, ThemeMissingException {//Edit theme
		
		if(themeExists(oldTheme.toLowerCase())==false) {throw new ThemeMissingException("",null);}//Checks if theme exists
		String newTheme = JOptionPane.showInputDialog(null,"Enter the new theme to replace "+oldTheme);//Gets the theme that will replace
		Connection con = connect();
		//Create SQL statements
		PreparedStatement stmt = con.prepareStatement("UPDATE Question SET theme='misc' WHERE theme='"+oldTheme.toLowerCase()+"'");
		PreparedStatement stmt2 = con.prepareStatement("UPDATE Themes SET theme='"+newTheme.toLowerCase()+"' WHERE theme='"+oldTheme.toLowerCase()+"'");
		//Execute SQL statements
		stmt.executeUpdate();
		stmt2.executeUpdate();
	}
	
	public static boolean themeExists(String theme) throws SQLException{//Checks if theme exists 
		String[] themes = getThemes(); boolean exists=false;
		for(String t : themes) {if(t.equals(theme)){exists=true;}};
		
		return exists;
	}
	
	public static void deleteTheme(String theme) throws SQLException, ThemeMissingException {//Delete theme
		Connection con = connect();
		
		if(themeExists(theme.toLowerCase())==false) {throw new ThemeMissingException("",null);}//Check if theme exists
		
		//Create SQL statements
		PreparedStatement stmt = con.prepareStatement("UPDATE Question SET theme='misc' WHERE theme='"+theme.toLowerCase()+"'");
		PreparedStatement stmt2 = con.prepareStatement("DELETE FROM Themes WHERE theme='"+theme.toLowerCase()+"'");
		
		//Execute SQL statements
		stmt.executeUpdate();
		stmt2.executeUpdate();
	}
	
	public static String[][] getQuestions(String theme) throws SQLException, ThemeMissingException {//Get questions for given theme
		Connection con = connect();
		
		if(themeExists(theme.toLowerCase())==false){throw new ThemeMissingException("",null);};//Check if theme exists
		
		//Create SQL statements
		PreparedStatement stmt = con.prepareStatement("SELECT entryID FROM Question WHERE theme='"+theme.toLowerCase()+"'");
		PreparedStatement stmt2 = con.prepareStatement("SELECT theme, contents FROM Question WHERE theme='"+theme.toLowerCase()+"'");
		
		//Execute SQL statements and store in result sets
		ResultSet rslt = stmt.executeQuery();
		ResultSet rslt2 = stmt2.executeQuery();
		
		
		int rows=0;
	    while(rslt.next()) {rows++;}//Find the number of questions
	    
	    String[][] questions = new String[rows][2];//Create 2-dimensional array of questions and themes
	    
	    int i=0; while(rslt2.next()) {//Get data from Question table and store in 2-dimensional array
	    	questions[i][0]=rslt2.getString("theme");
	    	questions[i][1]=rslt2.getString("contents");
	    	i++;
	    }
	    
	    return questions;//Return array
		
	}
	
	public static void addTheme(String theme) throws SQLException, ThemeMissingException {//Add theme
		Connection con = connect();

		if(themeExists(theme.toLowerCase())==false) {//If theme doesn't exist we add theme to table
			PreparedStatement addTheme = con.prepareStatement("INSERT INTO Themes VALUES ('"+theme.toLowerCase()+"')");
		    addTheme.executeUpdate();
		}
		else {//Else throw exception
			throw new ThemeMissingException("",null);
		}
		
	}
		
		
	public static String[] getThemes() throws SQLException {//Get an array of themes as strings
		Connection con = connect();
		PreparedStatement stmt = con.prepareStatement("SELECT theme FROM Themes");
		PreparedStatement stmt2 = con.prepareStatement("SELECT theme FROM Themes");
		//Create and execute statements
		ResultSet rslt = stmt.executeQuery();
		ResultSet rslt2 = stmt2.executeQuery();
		    
		int size=0;//Find number of themes
	    while(rslt2.next()) {size++;}
	    
	    int i=0; String[] themes = new String[size];//Create array with length=# of themes
	    
	    while(rslt.next()) //Store themes in array
	    {
		    themes[i]=(rslt.getString("theme")).replaceAll(" ", "");
		    i++;
	    }
	    
	    return themes;//Return array
	}
	
	public static String findTheme(String s) throws SQLException//Finds theme
	{
		String[] themes = getThemes();//Gets array of themes
		
	    String[] question = null;
		question=s.split(" ");
		int[] counts=new int[themes.length];
		Arrays.fill(counts, 0);
		//Splits string into individual words and store words in an array
		
		for(String word: question)//Checks for number of occurences of theme keywords in a string
		{
			for(int j=0;j<themes.length;j++) {
				if(word.equals(themes[j])) {
					counts[j]+=1;
				}
			}
		}
		
		int high_index=-1, high_count=0;
		
		for(int i=0; i<counts.length;i++) {//Sets high index to index of theme for question
			if(counts[i]>high_count) {
				high_index=i;
			}
		}
		
		if(high_index==-1) {s="misc";}//If theme not found sets theme to misc
		else {s=themes[high_index];}
		
		return s;//return theme
	}

}
