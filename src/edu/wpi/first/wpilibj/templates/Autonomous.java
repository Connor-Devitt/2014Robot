
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
        status = "drive";
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
                status = "shoot";
            }
        } else {
            if (status.equals("shoot")) {
                //shoot robot
                turret.setTriggerPull(true);
                status = "stopped";
                turret.reloadInit();
            } else {
                if (status.equals("stopped")) {
                    turret.setTriggerPull(true);
                    
                    //do nothing...
                }
            }
            
        }
        
    }
    
    //Just like the other auto method, except it grabs an image every iteration (could be inefficient), then drives until
    //either the timer is up or the camera finds that the target is hot and within half a foot of shooting distance (arbitrary)
    private void auto2()
    {
        sensors.camLoadNewImg();
        if (status.equals("drive")) {
            //drive robot
            if (!isTimerStarted())
                startTimer();
            
            //Robot will drive while the timer is running.
            if (timer.get() >= StaticVars.AUTONOMOUS_DRIVE_TIMER || (sensors.camTargetHot() && (Math.abs(StaticVars.SHOOTING_DISTANCE - sensors.camDistanceToTarget()) < 0.5))) {
                //drivetrain.fieldDriveMecanumPolar(sensors.getGyroAngle(), 0.0, 0.0, 0.0);
                drivetrain.driveMecanumPolar(0.0, 0.0, 0.0);
                status = "shoot";
            } else {
                //drivetrain.fieldDriveMecanumPolar(sensors.getGyroAngle(),
                //                                  StaticVars.AUTONOMOUS_DRIVE_MAGNITUDE,
                //                                  0, 0);
                drivetrain.driveMecanumPolar(StaticVars.AUTONOMOUS_DRIVE_MAGNITUDE, 0, 0);
            }
        } else {
            if (status.equals("shoot")) {
                //shoot robot
                turret.setTriggerPull(true);
                status = "stopped";
                turret.reloadInit();
            } else {
                if (status.equals("stopped")) {
                    turret.setTriggerPull(true);
                    
                    //do nothing...
                }
            }
            
        }
    }
}
