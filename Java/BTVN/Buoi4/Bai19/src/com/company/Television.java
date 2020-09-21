package com.company;

public class Television {
    int volume = 0, channel = 0;
    boolean turnOn = false;

    Television(){

    }

    public void pressTurnOnAndOff(){
        turnOn = !turnOn;
        if (turnOn){
            System.out.println("Trạng thái TV: đang bật");
        }
        else{
            System.out.println("Trạng thái TV: đang tắt");
        }
    }

    public void changeChannel(int channel){
        if (channel>= 0){
            this.channel = channel;
        }
        System.out.println("Kênh hiện tại: " + channel);
    }

    public void increaseChannel(){
        channel++;
        System.out.println("Kênh hiện tại: " + channel);
    }

    public void decreaseChannel(){
        channel--;
        System.out.println("Kênh hiện tại: " + channel);
    }

    public void increaseVolume(){
        volume++;
        System.out.println("Volume hiện tại: " + volume);
    }

    public void decreaseVolume(){
        volume--;
        System.out.println("Volume hiện tại: " + volume);
    }
}
