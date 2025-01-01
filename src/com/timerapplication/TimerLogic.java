package com.timerapplication;

public class TimerLogic {
    private long startTime;
    private long elapsedTime;
    private boolean running = false ;


    public void start(){
        if (!running){
            running = true ;
            startTime = System.currentTimeMillis() - elapsedTime;
        }
    }

    public void pause(){
        if (running){
            running = false ;
            elapsedTime =System.currentTimeMillis() - startTime;
        }
    }
    public void reset(){
        running= false ;
        startTime = 0;
        elapsedTime =0 ;
}

public String getTime(){
        long time = running ? System.currentTimeMillis()- startTime :
                elapsedTime;
        long seconds = (time / 1000) % 60;
        long minutes = (time / 60000) % 60;
        long hours = (time / 3600000);

        return String.format("%02d:%02d:%02d",hours,minutes,seconds);
}
}

