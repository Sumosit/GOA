package com.example.java.lab3.kas_ass2;


import com.example.java.lab3.kas_ass1.Point;
import com.example.java.lab3.kas_ass1.Shape;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class start {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Validator validator = new Validator();

        while (true) {
            User user = new User();
            System.out.println("\nCreare new user");
            System.out.print("First name: ");
            user.setfName(in.nextLine());
            System.out.print("Second name: ");
            user.setsName(in.nextLine());
            System.out.print("Age: ");
            user.setAge(Integer.parseInt(in.nextLine()));
            System.out.print("Gender: ");
            user.setGender(in.nextLine());
            System.out.print("Password: ");
            user.setPassword(in.nextLine());
            System.out.print("Date (dd/mm/yy): ");
            user.setDate(in.nextLine());

            if (validator.checkUser(user)) {
                System.out.println("User created");
            } else {
                System.out.println("User is NOT created");
            }
        }
    }
}
