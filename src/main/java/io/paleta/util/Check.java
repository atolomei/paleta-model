package io.paleta.util;


/**
 * 
 * @author atolomei@novamens.com (Alejandro Tolomei)
 * 
 */
public class Check {

	public static <T> T requireNonNullArgument(T obj, String message) {
        if (obj == null)
            throw new IllegalArgumentException(message);
        return obj;
    }
	
	public static String requireNonNullStringArgument(String obj, String message) {

		if (obj == null)
            throw new IllegalArgumentException(message);
        
        if ("".equals((String) obj)) 
            throw new IllegalArgumentException(message);
        
        return obj;
    }
						
	public static void requireTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalStateException(message);
		}
	}
	
	public static void checkTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalStateException(message);
		}
	}
	public static <T> T requireNonNull(T obj) {
        if (obj == null)
            throw new NullPointerException();
        return obj;
    }

}
