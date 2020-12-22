package com.company;

import java.util.Arrays;

public class Tohop {
    int[] arr;
    int length;

    public Tohop(int length) {
        this.length = length;
        arr = new int[length];
        taoToHop(0);
    }

    private void vietToHop(){
        System.out.println(Arrays.toString(arr));
    }

    private void taoToHop(int step){
        if (step == length){
            vietToHop();
        }
        for (int i=1; i<=length; i++){
            boolean check = true;
            for (int j=0; j<step; j++){
                if (arr[j] == i){
                    check = false;
                    break;
                }
            }
            if (check){
                arr[step] = i;
                taoToHop(step + 1);
            }
        }
    }
}
