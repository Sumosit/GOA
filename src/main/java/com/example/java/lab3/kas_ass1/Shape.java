package com.example.java.lab3.kas_ass1;


import java.util.ArrayList;
import java.util.List;

public class Shape {
    private List<Point> pList = new ArrayList<Point>();
    private List<Double> dList = new ArrayList<Double>();

    public Shape() {

    }

    public Shape(List<Point> pList) {
        this.pList = pList;
    }

    public List<Point> getpList() {
        return pList;
    }

    public void setpList(List<Point> pList) {
        this.pList = pList;
    }

    public void addToList(Point p) {
        pList.add(p);
    }

    public void deleteFromList(Point p) {
        pList.remove(p);
    }

    public double distance(Point p1, Point p2) {
        double sum = Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));

        System.out.println(sum);

        return sum;
    }

    public void distances() {
        int i;
        if (!pList.isEmpty() && pList.size() >= 2) {
            System.out.println("Distances");
            for (i = 0; i < pList.size() - 1; i++) {
                dList.add(distance(pList.get(i), pList.get(i + 1)));
            }
            if (i > 2) {
                dList.add(distance(pList.get(i), pList.get(0)));
            }
        }
    }

    public void Perimeter() {
        double sum = 0;
        for (double d : dList) {
            sum = sum + d;
        }
        System.out.println("Perimeter: " + sum);
    }

    public void longestSide() {
        double max = 0;
        for (double d : dList) {
            if (max < d) {
                max = d;
            }
        }
        System.out.println("Longest side: " + max);
    }

    public void averageLength() {
        double sum = 0;
        for (double d : dList) {
            sum = sum + d;
        }
        sum = sum / dList.size();
        System.out.println("Average length: " + sum);
    }
}
