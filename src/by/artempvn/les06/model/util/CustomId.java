package by.artempvn.les06.model.util;

public class CustomId {
	private static long id;
	
	public static long takeId() {
		return id++;
	}
}
