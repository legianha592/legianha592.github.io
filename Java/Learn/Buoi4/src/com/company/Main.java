package com.company;

public class Main {

    public static void main(String args[]) {
        var obj = new Bike2
        obj.run();
    }

    public class Vehicle {
        public void run() {
            System.out.println("Vehicle is running");
        }
    }

    public class Bike2 extends Vehicle {
        public void run() {
            System.out.println("Bike is running safely");
        }
    }
}
