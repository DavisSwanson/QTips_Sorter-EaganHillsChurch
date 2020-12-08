package QTips_Sorting_System;
//Custom error for theme missing
public class ThemeMissingException extends Exception{
	public ThemeMissingException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
