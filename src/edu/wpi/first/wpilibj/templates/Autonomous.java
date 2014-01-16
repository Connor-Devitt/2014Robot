
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
    }
    
    public void runAuto(int autoChoice) {
        auto1();
    }
    
    public boolean isTimerStarted() {
        return timerStarted;
    }
    
    public void startTimer() {
        timer.start();
    }
    
    private void auto1() {
        
        if (status.equals("drive")) {
            //drive robot
            hardware.getRobotDrive().mecanumDrive_Polar(StaticVars.DRIVE_MAGNITUDE, 0, 0);
        } else {
            //shoot robot
            status = "drive";
            
        }
        
    }
    
    
}
