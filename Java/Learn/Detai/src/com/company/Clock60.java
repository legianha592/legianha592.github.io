package com.company;

public class Clock60 extends Clock{

    public Clock60(int index) {
        super(index);
        super.setMaxRange(59);
        super.setMinRange(0);
    }
}
