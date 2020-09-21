package com.company;

public class Main {

    public static void main(String[] args) {
	    int player = 0, computer = 0;
	    for (int i=0; i<3; i++){
	        OneTwoThree play = new OneTwoThree();
	        int result = play.setNumber();
	        if (result == 1){
	        	player++;
			}
	        else if (result == -1){
	        	computer++;
			}
        }

		System.out.println("Bạn thắng " + player + " ván, máy thắng " + computer + " ván.");
    }
}
