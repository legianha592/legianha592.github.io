package com.company;

public class RationalNumber extends ComparableNumber<String>{
    int numerator, denominator;

    public RationalNumber(int numerator, int denominator) {
        if (denominator == 0){
            throw new ArithmeticException();
        }
        else{
            
        }
    }

    @Override
    String value() {
        return null;
    }

    @Override
    public String add(String obj) {
        return null;
    }

    @Override
    public String sub(String obj) {
        return null;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }
}
