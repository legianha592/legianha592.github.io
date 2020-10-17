package com.company;

public class Clock24 extends Clock{

    public Clock24(int index) {
        super(index);
        super.setMaxRange(23);
        super.setMinRange(0);
    }
}
