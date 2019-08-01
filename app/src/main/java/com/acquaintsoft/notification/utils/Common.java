package com.acquaintsoft.notification.utils;

public class Common {
    public static String getDigits(int digit) {
        if (digit > 9) {
            return "" + digit;
        } else {
            return "0" + digit;
        }

    }
}