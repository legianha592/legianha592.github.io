package com.company;

import java.util.Timer;
import java.util.TimerTask;

public class CompleteClock {
    private int minHour, minMinute, minSecond, maxHour, maxMinute, maxSecond;
    private String type;
    private Timer time;

    public void setMinCompleteClock(int minHour, int minMinute, int minSecond) {
        this.minHour = minHour;
        this.minMinute = minMinute;
        this.minSecond = minSecond;
    }

    public void setMaxCompleteClock(int maxHour, int maxMinute, int maxSecond) {
        this.maxHour = maxHour;
        this.maxMinute = maxMinute;
        this.maxSecond = maxSecond;
    }

    public void setType(String type){
        this.type = type;
    }

    private boolean checkCompleteClock(){
        if (checkSingleClock(minHour, minMinute, minSecond)
        && checkSingleClock(maxHour, maxMinute, maxSecond)
        && checkType() && checkSettingTime()){
            return true;
        }
        System.out.println("Can't start counting");
        return false;
    }

    private boolean checkSingleClock(int hour, int minute, int second){
        if (hour < 0 || hour > 23){
            System.out.println("Invalid single clock");
            return false;
        }
        if (minute < 0 || minute > 59 || second < 0 || second > 59){
            System.out.println("Invalid single clock");
            return false;
        }
        return true;
    }

    private boolean checkType(){
        if (type.equals("up") || type.equals("down")){
            return true;
        }
        System.out.println("Invalid type");
        return false;
    }

    private boolean checkSettingTime(){
        int totalMinSecond = minSecond + minMinute*60 + minHour*3600;
        int totalMaxSecond = maxSecond + maxMinute*60 + maxHour*3600;
        if (totalMinSecond < totalMaxSecond){
            return true;
        }
        System.out.println("Invalid setting time: min-time is not smaller than max-time");
        return false;
    }

    public void run(){
        if (checkCompleteClock()){
            if (type.equals("up")){
                countUp();
            }
            else{
                countDown();
            }
        }

    }

    private void countUp(){
        time = new Timer();
        var second = new Clock60(minSecond);
        var minute = new Clock60(minMinute);
        var hour = new Clock24(minHour);
        second.setPreviousClock(minute);
        minute.setPreviousClock(hour);

        time.schedule(new TimerTask() {
            @Override
            public void run() {
                timeToString(hour.getIndex(), minute.getIndex(), second.getIndex());
                second.countUp();

                int currentTime = second.getIndex() + minute.getIndex()*60 + hour.getIndex()*3600;
                int maxTime = maxSecond + maxMinute*60 + maxHour*3600;
                if (currentTime == maxTime){
                    timeToString(hour.getIndex(), minute.getIndex(), second.getIndex());
                    time.cancel();
                }
            }
        }, 0, 1000);
    }

    private void countDown(){
        time = new Timer();
        var second = new Clock60(maxSecond);
        var minute = new Clock60(maxMinute);
        var hour = new Clock24(maxHour);
        second.setPreviousClock(minute);
        minute.setPreviousClock(hour);

        time.schedule(new TimerTask() {
            @Override
            public void run() {
                timeToString(hour.getIndex(), minute.getIndex(), second.getIndex());
                second.countDown();

                int currentTime = second.getIndex() + minute.getIndex()*60 + hour.getIndex()*3600;
                int minTime = minSecond + minMinute*60 + minHour*3600;
                if (currentTime == minTime){
                    timeToString(hour.getIndex(), minute.getIndex(), second.getIndex());
                    time.cancel();
                }
            }
        }, 0, 1000);
    }

    private void timeToString(int hour, int minute, int second){
        String hourString, minuteString, secondString;
        if (hour<10){
            hourString = "0" + hour;
        }
        else{
            hourString = "" + hour;
        }
        if (minute<10){
            minuteString = "0" + minute;
        }
        else{
            minuteString = "" + minute;
        }
        if (second<10){
            secondString = "0" + second;
        }
        else{
            secondString = "" + second;
        }
        System.out.println(hourString + ":" + minuteString + ":" + secondString);
    }
}
