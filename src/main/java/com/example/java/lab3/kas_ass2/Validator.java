package com.example.java.lab3.kas_ass2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validator {
    public boolean validateJavaDate(String strDate)
    {
        if (strDate.trim().equals("")){
            return true;
        }
        else {
            SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
            sdfrmt.setLenient(false);
            try {
                Date javaDate = sdfrmt.parse(strDate);
                System.out.println("Date is correct");
            } catch (ParseException e) {
                System.out.println("Date is NOT correct");
                return false;
            }

            return true;
        }
    }
    public boolean checkAge(int age) {
        if (age >= 18) {
            System.out.println("Age is correct");
            return true;
        }
        System.out.println("Age is NOT correct");
        return false;
    }

    public boolean checkPassword(String password) {
        int i, upperCase = 0, lowerCase = 0, digit = 0, special = 0, length = 0;

        length = password.length();
        if (length >= 8) {
            for (i = 0; i < length; i++) {
                char symbol = password.charAt(i);
                if (symbol >= 'A' && symbol <= 'Z') {
                    upperCase++;
                }
                if (symbol >= 'a' && symbol <= 'z') {
                    lowerCase++;
                }
                if (symbol >= '0' && symbol <= '9') {
                    digit++;
                }
                if (symbol == '@' || symbol == '$' || symbol == '!' || symbol == '^') {
                    special++;
                }
            }
            if (upperCase >= 1 && lowerCase >= 1 && digit >= 1 && special >= 1) {
                System.out.println("Password is correct");
                return true;
            }
        }
        System.out.println("Password is NOT correct");
        return false;
    }

    public boolean checkUser(User user) {
        // If the first check returns an error, then it will not go to the next check
        if (checkAge(user.getAge()) &&
            checkPassword(user.getPassword()) &&
            validateJavaDate(user.getDate())
        ) {
            return true;
        }
        return false;
    }
}
