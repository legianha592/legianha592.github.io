package com.company;

public class Equation {
    double a, b, c, delta;

    public void setEquation(double new_a, double new_b, double new_c){
        a = new_a;
        b = new_b;
        c = new_c;
    }

    public void solution(){
        delta = b*b-4*a*c;
        if (delta < 0){
            System.out.println("Phương trình vô nghiệm");
        }
        else if (delta == 0){
            System.out.print("Phương trình có nghiệm kép: x = ");
            System.out.println(-b/(2*a));
        }
        else{
            System.out.println("Phương trình có 2 nghiệm phân biệt:");
            System.out.println("x1 = " + (-b+Math.sqrt(delta))/(2*a));
            System.out.println("x2 = " + (-b-Math.sqrt(delta))/(2*a));
        }
    }
}
