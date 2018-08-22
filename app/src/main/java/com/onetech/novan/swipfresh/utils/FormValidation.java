package com.onetech.novan.swipfresh.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidation {

    // validating email by pattern
    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password by lenght min 7
    public static boolean isValidPassword(String pass) {
        if (pass.isEmpty() || pass.equals("") || pass == null || pass.length() < 7 ) {
            return false;
        }
        return true;
    }

    // validating not empty
    public static boolean isNotEmpty(String str) {
        str = str.trim();
        if (str.isEmpty() || str.length() == 0 || str.equals("") || str == null) {
            return false;
        }
        return true;
    }

    // compare value
    public static boolean isNotMatch(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        return false;
    }
}
