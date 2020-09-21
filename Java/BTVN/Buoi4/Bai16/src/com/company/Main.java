package com.company;

public class Main {

    public static void main(String[] args) {
	    Stock stock = new Stock("Công ty CP Chứng khoán SSI", "SSI");
	    stock.setPreviousClosingPrice(34.5);
	    stock.setCurrentPrice(34.35);
	    stock.getChangePercent();
    }
}
