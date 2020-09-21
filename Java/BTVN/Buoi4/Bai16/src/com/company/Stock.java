package com.company;

public class Stock {
    public String name, code;
    public double previousClosingPrice, currentPrice;

    Stock(String new_name, String new_code){
        name = new_name;
        code = new_code;
    }

    public void setPreviousClosingPrice(double price){
        previousClosingPrice = price;
    }

    public void setCurrentPrice(double price){
        currentPrice = price;
    }

    public void getChangePercent(){
        System.out.println("" + (previousClosingPrice/currentPrice*100) + "%");
    }
}
