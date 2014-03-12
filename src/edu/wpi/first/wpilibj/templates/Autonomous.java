
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
    
    //DO NOT FORGET TO UNCOMMENT FIELD ORIENTED DRIVE IF DESIRED!
    
    private String status;
    private boolean timerStarted;
    private static Timer timer;
    private final DriveTrain drivetrain;
    private final Sensors sensors;
    private final Turret turret;
    
    public Autonomous(DriveTrain drivetrain, Sensors sensors, Turret turret) {
        //status = "turn";    //CAUTION!
        status = "imgprocess"; //ONLY WHEN USING AUTO2
        this.drivetrain = drivetrain;
        this.sensors = sensors;
        timerStarted = false;
        timer = new Timer();
        this.turret = turret;
        turret.setTriggerPull(false);
        sensors.camLoadNewImg();
    }
    
    public void runAuto(int autoChoice) {
        switch (autoChoice) {
            case 1: 
                auto1();
                break;
            case 2:
                auto2();
                break;
            default:
                break;
        }
    }
    
    public boolean isTimerStarted() {
        return timerStarted;
    }
    
    public void startTimer() {
        timer.start();
        timerStarted = true;
    }
    
    //Drives forward for 3 seconds and then shoots
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
                } else {
                    if (status.equals("turn")) {
                        if (Math.abs(sensors.getGyroAngle()) >= 180) {
                            //stop turning
                            drivetrain.driveMecanumPolar(0.0, 0.0, 0.0);
                            //drivetrain.fieldDriveMecanumPolar(sensors.getGyroAngle(), 0.0, 0.0, 0.0);
                            status = "drive";
                        } else {
                            //keep turning
                            drivetrain.driveMecanumPolar(0.0, 0.0, StaticVars.AUTONOMOUS_TWIST_MAGNITUDE);
                            /*
                            drivetrain.fieldDriveMecanumPolar(sensors.getGyroAngle(),
                                                              0.0, 
                                                              0.0, 
                                                              StaticVars.AUTONOMOUS_TWIST_MAGNITUDE);
                            */
                        }
                    }
                }
            }
            
        }
        
    }
    
    //Just like the other auto method, except it drives until
    //either the timer is up or the camera finds that the target is hot and within half a foot of shooting distance (arbitrary)
    private void auto2()
    {
        sensors.updateRangefinder();
        
        if (status.equals("imgprocess")){
            if (sensors.camTargetHot()) {
                status = "drive";
            } else {
                timer.reset();
                timer.start();
                status = "wait";
            }
        }
        if (status.equals("drive")) {
            //drive robot
            //Robot will drive while the timer is running.
            turret.pullInit();
            if (sensors.getRangefinderDistanceFeet() < StaticVars.AUTO_SHOOT_DIST_FEET) {
                drivetrain.driveMecanumPolar(0.0, 0.0, 0.0);
                status = "shoot";
            } else drivetrain.driveMecanumPolar(StaticVars.AUTONOMOUS_DRIVE_MAGNITUDE, 0, 0);
        }
        
        if (status.equals("shoot")) {
                //shoot robot
                turret.setTriggerPull(true);
                status = "stopped";
        }
        
        if (status.equals("stopped")) {
            //do nothing
            //turret.setTriggerPull(true);
        }
        
        if (status.equals("wait")) {
            if (timer.get() > StaticVars.AUTO_TARGET_HOT_WAIT_TIME) {
                status = "drive";
            }
        }
    }
}
