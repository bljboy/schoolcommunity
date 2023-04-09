package com.bljboy.schoolcommunity.utils;

import android.util.Patterns;

import java.util.regex.Pattern;

public class isValidEmail {
    public static boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

}
