package com.company;

import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Bai1();

//        Bai2();

//        Bai3();

//        Bai4();

//        Bai5();

//        Bai6();

//        Bai7();

//        Bai8();
    }

    public static void Bai1(){
        int year;

        Scanner input = new Scanner(System.in);

        while(true){
            System.out.println("Nhập một năm vào từ bàn phím (lớn hơn 0): ");
            year = input.nextInt();
            if (year > 0){
                break;
            }
            System.out.println("Bạn đã nhập sai. Vui lòng nhập lại");
        }

        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)){
            System.out.println("Năm " + year + " là năm nhuận");
        }
        else{
            System.out.println("Năm " + year + " là năm không nhuận");
        }
    }

    public static void Bai2(){
        int year;
        String[] arr = {"Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ", "Ngọ", "Mùi", "Thân", "Dậu", "Tuất", "Hợi"};

        Scanner input = new Scanner(System.in);

        while(true){
            System.out.println("Nhập một năm vào từ bàn phím (từ 0 đến 11): ");
            year = input.nextInt();
            if (year >= 0 && year <= 11){
                System.out.println(arr[year]);
                break;
            }
            System.out.println("Bạn đã nhập sai. Vui lòng nhập lại");
        }
    }

    public static void Bai3(){
        int year;
        String[] arr = {"một", "hai", "ba", "tư", "năm", "sáu", "bảy", "tám", "chín", "mười", "mười một", "mười hai"};

        year = (int)Math.floor(Math.random()*12) + 1;

        System.out.println("Tháng " + arr[year-1]);
    }

    public static void Bai4(){
        double[] arr = new double[3];
        double total = 0;

        Scanner input = new Scanner(System.in);

        for (int i=0; i<3; i++){
            while(true){
                System.out.println("Nhập số thứ " + (i+1) + " (lớn hơn 0): ");
                arr[i] = input.nextDouble();
                if (arr[i] > 0){
                    total += arr[i];
                    break;
                }
                System.out.println("Bạn đã nhập sai. Vui lòng nhập lại");
            }
        }

        boolean check = true;
        for (int i=0; i<3; i++){
            if (arr[i] >= total - arr[i]){
                check = false;
                break;
            }
        }

        if (check){
            System.out.println("Chu vi của tam giác là: " + total);
        }
        else{
            System.out.println("Ba số vừa nhập không phải ba cạnh của tam giác");
        }
    }

    public static void Bai5(){
        double x, y;
        final double X_MIN = -5, X_MAX = 5, Y_MIN = -2.5, Y_MAX = 2.5;

        Scanner input = new Scanner(System.in);

        System.out.println("Nhập tọa độ x: ");
        x = input.nextDouble();
        System.out.println("Nhập tọa độ y: ");
        y = input.nextDouble();

        if (x >= X_MIN && x <= X_MAX){
            if (y >= Y_MIN && y <= Y_MAX){
                System.out.println("Điểm đã cho nằm trong hình chữ nhật.");
                return;
            }
        }
        System.out.println("Điểm đã cho nằm ngoài hình chữ nhật.");
    }

    public static void Bai6(){
        int random_card, random_number, random_type;
        String[] number = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] type = {"Cơ", "Rô", "Chuồn", "Bích"};

        random_card = (int)Math.floor(Math.random()*52);
        random_number = random_card%12;
        random_type = random_card%4;

        System.out.println("Lá bài bạn vừa rút ra là " + number[random_number] + " " + type[random_type]);
    }

    public static void Bai7(){
        double[][] arr = new double[2][4];

        Scanner input = new Scanner(System.in);

        for (int i=0; i<2; i++){
            System.out.println("Nhập hoành độ của tâm HCN thứ " + (i+1) + " :");
            arr[i][0] = input.nextDouble();
            System.out.println("Nhập tung độ của tâm HCN thứ " + (i+1) + " :");
            arr[i][1] = input.nextDouble();

            while(true){
                System.out.println("Nhập chiều dài của HCN thứ " + (i+1) + " :");
                arr[i][2] = input.nextDouble();
                if (arr[i][2] > 0){
                    break;
                }
                System.out.println("Bạn đã nhập sai. Vui lòng nhập lại.");
            }

            while(true){
                System.out.println("Nhập chiều rộng của HCN thứ " + (i+1) + " :");
                arr[i][3] = input.nextDouble();
                if (arr[i][3] > 0){
                    break;
                }
                System.out.println("Bạn đã nhập sai. Vui lòng nhập lại.");
            }
        }

        if (arr[0][0] + arr[0][2]/2 <= arr[1][0] + arr[1][2]/2 && arr[0][0] - arr[0][2]/2 >= arr[1][0] - arr[1][2]/2
            && arr[0][1] + arr[0][3]/2 <= arr[1][1] + arr[1][3]/2 && arr[0][1] - arr[0][3]/2 >= arr[1][1] - arr[1][3]/2){
            System.out.println("Hai hình chữ nhật lồng nhau.");
        }
        else if(arr[1][0] + arr[1][2]/2 <= arr[0][0] + arr[0][2]/2 && arr[1][0] - arr[1][2]/2 >= arr[0][0] - arr[0][2]/2
                && arr[1][1] + arr[1][3]/2 <= arr[0][1] + arr[0][3]/2 && arr[1][1] - arr[1][3]/2 >= arr[0][1] - arr[0][3]/2){
            System.out.println("Hai hình chữ nhật lồng nhau.");
        }
        else if(arr[0][0] + arr[0][2]/2 >= arr[1][0] - arr[1][2]/2 && arr[1][0] + arr[1][2] >= arr[0][0] - arr[0][2]/2
                && arr[0][1] + arr[0][3]/2 >= arr[1][1] - arr[1][3]/2 && arr[1][1] + arr[1][3]/2 >= arr[0][1] - arr[0][3]/2){
            System.out.println("Hai hình chữ nhật giao nhau");
        }
        else{
            System.out.println("Hai hình chữ nhật ở ngoài nhau.");
        }
    }

    public static void Bai8() {
        int[][] arr = new int[5][16];
        int[] count = new int[5];
        int[] answer = new int[5];
        int result = 0;

        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 5; i++){
            count[i] = 0;
        }
        for (int i = 1; i <= 31; i++) {
            int number = i;
            for (int j = 0; j < 5; j++){
                int excess = number%2;
                if (excess == 1){
                    arr[j][count[j]] = i;
                    count[j]++;
                }
                number /= 2;
            }
        }

        for (int i=0; i<5; i++){
            while(true){
                System.out.println("Ngày sinh của bạn có trong set thứ " + (i+1) + " không?");
                System.out.print("Set thứ " + (i+1) + " gồm: " + arr[i][0]);
                for (int j=1; j<16; j++){
                    System.out.print(", " + arr[i][j]);
                }
                System.out.println(".");
                System.out.println("Chọn 0 nếu không có, chọn 1 nếu có.");

                int number = input.nextInt();
                if (number == 0 || number == 1){
                    answer[i] = number;
                    break;
                }
                System.out.println("Bạn đã nhập sai. Vui lòng nhập lại.");
            }
        }

        for (int i=0; i<5; i++){
            result += answer[i] * Math.pow(2, i);
        }
        System.out.println("Ngày sinh của bạn là ngày " + result);
    }
}
