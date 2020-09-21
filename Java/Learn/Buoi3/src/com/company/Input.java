package com.company;

import java.util.Scanner;

public class Input {
    public int principle, year, month;
    public double rate;

    public void input(){
        Scanner input = new Scanner(System.in);
        System.out.println("Nhập số tiền: ");
        principle = input.nextInt();
        System.out.println("Nhập lãi suất: ");
        rate = input.nextDouble();
        rate = rate/12/100;
        System.out.println("Nhập số kì trả nợ: ");
        year = input.nextInt();
        month = year*12;
    }
}
