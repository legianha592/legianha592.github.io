package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.IntConsumer;

public class Main {
    static int a = 0, b = 0, c = 1;
    static int count = 2, n = 0;
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Nhap n: ");
        n = input.nextInt();

        System.out.println("Fibo: ");
        System.out.println(b);
        System.out.println(c);

        fibo();
    }

    public static void fibo() {
        a = b;
        b = c;
        c = a + b;
        System.out.println(c);
        count++;
        if (count<n){
            fibo();
        }
    }
}

