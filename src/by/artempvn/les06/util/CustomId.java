package by.artempvn.les06.util;

public class CustomId {
	private static long id;
	
	public static long takeId() {
		return id++;
	}
}
