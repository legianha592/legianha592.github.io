package com.company;

import java.util.Date;

public class Account {
    public int id;
    public double balance;
    public static double annualInterestRate;
    public Date dateCreated;

    Account(){

    }

    Account(int new_id, double new_balance){
        id = new_id;
        balance = new_balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static void setAnnualInterestRate(double annualInterestRate) {
        Account.annualInterestRate = annualInterestRate;
    }

    public static double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public double getMonthlyInterestRate(){
        return annualInterestRate/12;
    }

    public double getMonthlyInterest(){
        return getMonthlyInterestRate()*balance;
    }

    public void withdraw(double money){
        if (money < balance){
            balance -= money;
        }
        else{
            balance = 0;
        }
    }

    public void deposit(double money){
        balance += money;
    }
}
