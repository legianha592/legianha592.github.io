package com.company;

public class RunClock {
    public static void runClock(){
        var completeClock = new CompleteClock();
        completeClock.setMinCompleteClock(5, 59, 55);
        completeClock.setMaxCompleteClock(6, 0, 5);
        completeClock.setType("down");
        completeClock.run();
    }
}
