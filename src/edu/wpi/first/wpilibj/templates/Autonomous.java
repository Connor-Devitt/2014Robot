
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
    
    private String status;
    private boolean timerStarted;
    private static Timer timer;
    private final DriveTrain drivetrain;
    private final Sensors sensors;
    private final Turret turret;
    
    public Autonomous(DriveTrain drivetrain, Sensors sensors, Turret turret) {
        status = "shoot";
        this.drivetrain = drivetrain;
        this.sensors = sensors;
        timerStarted = false;
        timer = new Timer();
        this.turret = turret;
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
            if (timer.get() < StaticVars.AUTONOMOUS_DRIVE_TIMER) {
                //drivetrain.fieldDriveMecanumPolar(sensors.getGyroAngle(),
                //                                  StaticVars.AUTONOMOUS_DRIVE_MAGNITUDE,
                //                                  0, 0);
                drivetrain.driveMecanumPolar(StaticVars.AUTONOMOUS_DRIVE_MAGNITUDE, 0, 0);
            } else {
                //drivetrain.fieldDriveMecanumPolar(sensors.getGyroAngle(), 0.0, 0.0, 0.0);
                drivetrain.driveMecanumPolar(0.0, 0.0, 0.0);
                status = "stopped";
            }
        } else {
            if (status.equals("shoot")) {
                //shoot robot
                turret.setTriggerPull(true);
                status = "drive";
                turret.reloadInit();
            } else {
                if (status.equals("stopped")) {
                    turret.setTriggerPull(false);
                    
                    //do nothing...
                }
            }
            
        }
        
    }
    
    
}
