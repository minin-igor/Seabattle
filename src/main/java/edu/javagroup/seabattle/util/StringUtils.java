package edu.javagroup.seabattle.util;

import java.util.Date;

public class StringUtils {

    public static boolean isEmpty(CharSequence string) {
        return string == null || string.length() == 0;
        }

    public static boolean isNotEmpty(CharSequence string) {
        return !isEmpty(string);
    }
}
