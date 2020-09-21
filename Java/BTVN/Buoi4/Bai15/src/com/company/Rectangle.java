package com.company;

public class Rectangle {
    public double width = 1;
    public double height = 1;
    public double center_x = 0;
    public double center_y = 0;

    public double getArea() {
        return width*height;
    }

    public double getPerimeter() {
        return (width+height)*2;
    }

    public void setWidth(double newWidth) {
        width = newWidth;
    }

    public void setHeight(double newHeight){
        height = newHeight;
    }

    public void setCenter(double new_x, double new_y){
        center_x = new_x;
        center_y = new_y;
    }
}
