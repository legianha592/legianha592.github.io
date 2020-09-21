package com.company;

public class CheckCollision {
    public double x1, y1, width1, height1;
    public double x2, y2, width2, height2;

    public void getRect1(double x, double y, double width, double height){
        x1 = x;
        y1 = y;
        width1 = width;
        height1 = height;
    }

    public void getRect2(double x, double y, double width, double height){
        x2 = x;
        y2 = y;
        width2 = width;
        height2 = height;
    }

    public void checkCollisionOfTwoRects(){
        if (Math.abs(x1-x2) <= Math.abs(width1-width2)/2 && Math.abs(y1-y2) <= Math.abs(height1-height2)/2){
            System.out.println("Hai HCN đựng nhau");
        }
        else if (Math.abs(x1-x2) <= (width1+width2)/2 && Math.abs(y1-y2) <= (height1+height2)/2){
            System.out.println("Hai HCN giao nhau");
        }
        else{
            System.out.println("Hai HCN ngoài nhau");
        }
    }
}
