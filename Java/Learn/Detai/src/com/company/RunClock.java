package com.company;

public class RunClock {
    public static void runClock(){
        var completeClock = new CompleteClock();
        completeClock.setMinCompleteClock(0, 0, 0);
        completeClock.setMaxCompleteClock(0, 0, 5);
        completeClock.setType("down");
        completeClock.run();
    }
}
