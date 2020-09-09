package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Nhập vào số tiền gốc:");
        long money = input.nextLong();
        System.out.println("Nhập vào tỉ lệ lãi (tính theo phần trăm/năm):");
        int rate = input.nextInt();
        double new_rate = (double)rate/12*0.01;
        System.out.println("Nhập vào số năm vay tiền:");
        int year = input.nextInt();
        int month = year*12;
        double interest = (double)money * new_rate * Math.pow(1+new_rate, month) / (Math.pow(1+new_rate, month) - 1);
        System.out.println("Lãi hàng tháng là: " + interest);
    }
}
