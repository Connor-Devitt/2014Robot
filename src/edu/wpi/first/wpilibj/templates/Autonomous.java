
package edu.wpi.first.wpilibj.templates;

import java.lang.String;
import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
    
    private String status;
    private boolean timerStarted;
    private static Timer timer;
    private RobotHardware hardware;
    
    
    public Autonomous(RobotHardware hardware) {
        status = "shoot";
        this.hardware = hardware;
        timerStarted = false;
        timer = new Timer();
    }
    
    public void runAuto(int autoChoice) {
        auto1();
    }
    
    public boolean isTimerStarted() {
        return timerStarted;
    }
    
    public void startTimer() {
        timer.start();
        timerStarted = true;
    }
    
    private void auto1() {
        
        if (status.equals("drive")) {
            //drive robot
            if (!isTimerStarted())
                startTimer();
            
            //Robot will drive while the timer is running.
            if (timer.get()<StaticVars.DRIVE_TIMER) {
                hardware.getRobotDrive().mecanumDrive_Polar(StaticVars.DRIVE_MAGNITUDE, 0, 0);
            } else {
                hardware.getRobotDrive().mecanumDrive_Polar(0.0, 0.0, 0.0);
            }
        } else {
            if (status.equals("shoot")) {
                //shoot robot
                status = "drive";
            } else {
                if (status.equals("stopped")) {
                    //do nothing...
                }
            }
            
        }
        
    }
    
    
}
