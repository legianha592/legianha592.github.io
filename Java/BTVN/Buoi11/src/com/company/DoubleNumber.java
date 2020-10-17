package com.company;

public class DoubleNumber extends ComparableNumber<Double>{
    double number;

    public DoubleNumber(double number) {
        this.number = number;
    }

    @Override
    Double value() {
        return number;
    }

    @Override
    public Double add(Double obj) {
        return this.number+obj;
    }

    @Override
    public Double sub(Double obj) {
        return this.number-obj;
    }

    @Override
    public int compareTo(Double obj) {
        if (number>obj){
            return 1;
        }
        else if (number<obj){
            return -1;
        }
        return 0;
    }
}
