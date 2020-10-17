package com.company;

import com.sun.jdi.ObjectCollectedException;

public class GenericList <T> {
    private int size;
    private T[] arr;
    private int count = 0;

    public GenericList(int size) {
        this.size = size;
        arr = (T[]) new Object[size];
    }


    public void add(T obj){
        arr[count++] = obj;
    }

    @Override
    public String toString() {
        String str = "";
        for (T obj : arr){
            str += obj + "\t";
        }
        return str;
    }
}
