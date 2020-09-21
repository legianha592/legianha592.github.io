package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the sides of triangle : ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        if (a + b > c && b + c > a && a + c > b && a != 0 && b != 0 && c != 0)
            System.out.println("Perimeter of triangle : " + (a + b + c));
        else
            System.out.println("Invalid sides entered.");
    }
}
