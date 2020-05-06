package com.example.java.lab3.kas_ass2;

public class User {
    private static int idCounter = 0;
    private int id, age;
    private String fName, sName, gender, password, date;

    public User() {
        this.id = idCounter++;
    }

    public User(int id, int age, String fName, String sName, String gender, String password, String date) {
        this.id = id;
        this.age = age;
        this.fName = fName;
        this.sName = sName;
        this.gender = gender;
        this.password = password;
        this.date = date;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        User.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
