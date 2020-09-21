package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int principle, period;
        double rate;

        System.out.println("Nhập số tiền:");
        principle = input.nextInt();
        System.out.println("Nhập lãi suất:");
        rate = input.nextDouble();
        System.out.println("Nhập số kì trả nợ:");
        period = input.nextInt();
        for (int i=1; i<=period; i++){
            System.out.print("Số tiền còn lại sau kì trả nợ thứ " + i + " là: ");
            System.out.println(money(principle, rate, period, i));
        }
    }

    public static double money(int principle, double rate, int period, int current_period){
        return principle*(Math.pow(1+rate, period) - Math.pow(1+rate, current_period))/(Math.pow(1+rate, period)-1);
    }
}
