package QTips_Sorting_System;

import java.sql.*;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws SQLException 
	{
		Connection con = DriverManager.getConnection(
		        "jdbc:sqlserver://LAPTOP-GT12R9UA\\SQLEXPRESS:1433;database=QTipsSystem;", "user","password");
		
		PreparedStatement addTheme = con.prepareStatement("INSERT INTO Themes VALUES ('grace')");
	    addTheme.executeUpdate();
	    
	    String test = "love";
	    
	    findTheme(test);

	}
	
	public static String findTheme(String s) throws SQLException
	{
		
		Connection con = DriverManager.getConnection(
		        "jdbc:sqlserver://LAPTOP-GT12R9UA\\SQLEXPRESS:1433;database=QTipsSystem;", "user","password");
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
		
		for(int count : counts) {System.out.print(count);}
		
		return s;
	}

}
