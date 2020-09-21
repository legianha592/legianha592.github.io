package com.company;

import java.util.Random;
import java.util.Scanner;

public class OneTwoThree {
    public int player, computer;

    public int setNumber(){
        Scanner input = new Scanner(System.in);
        System.out.println("Ra quyết định: 1 = Đấm, 2 = Lá, 3 = Kéo");
        player = input.nextInt();
        getRandomNumber();

        if (player - 1 == computer){
            System.out.println("Hòa nhau");
            return 0;
        }
        else if (player == computer){
            System.out.println("Máy thắng");
            return -1;
        }
        else{
            System.out.println("Bạn thắng");
            return 1;
        }
    }

    public void getRandomNumber(){
        Random random = new Random();
        computer = random.nextInt(3);
    }

}
