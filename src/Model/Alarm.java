package Model;

import java.util.TimerTask;

public class Alarm extends TimerTask {
    private String alarmMessage;
    public Alarm(String alarmMessage){
        this.alarmMessage = alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }

    @Override
    public void run() {
        System.out.print(alarmMessage);
    }
}
