
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
        status = "turn";    //CAUTION!
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
    
    private void auto2() //vision processing
    {
        //sensors.camLoadNewImg();
        if (status.equals("drive")) {
            //drive robot
            if (!isTimerStarted())
                startTimer();
            
            //Robot will drive while the timer is running and hot target not detected
            if (timer.get() >= StaticVars.AUTONOMOUS_DRIVE_TIMER || sensors.camTargetHot()){
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
