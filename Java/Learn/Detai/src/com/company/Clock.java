package com.company;

import java.util.Timer;

public class Clock {
    private int minRange, maxRange;
    private int index;
    private Clock clock;

    public Clock(int index) {
        this.index = index;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public int getIndex() {
        return index;
    }

    public void setMinRange(int min) {
        this.minRange = min;
    }

    public void setMaxRange(int max) {
        this.maxRange = max;
    }

    public void setPreviousClock(Clock other){
        clock = other;
    }

    public void countUp(){
        index++;
        if (index > maxRange){
            index = minRange;
            if (clock != null){
                clock.countUp();
            }
        }
    }

    public void countDown() {
        index--;
        if (index < minRange) {
            index = maxRange;
            if (clock != null){
                clock.countDown();
            }
        }
    }
}
