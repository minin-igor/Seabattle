package edu.javagroup.seabattle.util;

public class IpAddressUtils {

    public static boolean isIpAddress(String string) {
        if (StringUtils.isNotEmpty(string) && string.contains(".")) {
            String[] array = string.split("\\.");
            if (array.length == 4) {
                return (NumberUtils.isInteger(array[0]) && Integer.parseInt(array[0]) <= 255 && Integer.parseInt(array[0]) > 0)
                        && (NumberUtils.isInteger(array[1]) && Integer.parseInt(array[1]) <= 255 && Integer.parseInt(array[1]) >= 0)
                        && (NumberUtils.isInteger(array[2]) && Integer.parseInt(array[2]) <= 255 && Integer.parseInt(array[2]) >= 0)
                        && (NumberUtils.isInteger(array[3]) && Integer.parseInt(array[3]) <= 255 && Integer.parseInt(array[3]) >= 0);
            } else return false;

        } else return false;
    }
}

