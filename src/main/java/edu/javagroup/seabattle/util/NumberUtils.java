package edu.javagroup.seabattle.util;


public class NumberUtils {

    public static boolean isInteger(String string) {

        try {
            Integer.parseInt(string);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
}
