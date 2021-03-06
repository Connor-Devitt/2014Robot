
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
    
    //DO NOT FORGET TO UNCOMMENT FIELD ORIENTED DRIVE IF DESIRED!
    
    private String status;
    private boolean timerStarted;
    private final BallRetriever ballpickup;
    private final Timer timer;
    private final DriveTrain drivetrain;
    private final Sensors sensors;
    private final Turret turret;

    public Autonomous(DriveTrain drivetrain, Sensors sensors, Turret turret, Actuators actuators, BallRetriever ballpickup) {
        this.drivetrain = drivetrain;
        this.sensors = sensors;
        this.turret = turret;
        this.ballpickup = new BallRetriever(actuators);
        timer = new Timer();
        
        timerStarted = false;
        status = "drive"; //CAUTION THIS IS STARTING STATUS
        turret.setTriggerPull(false);
        //sensors.camLoadNewImg();
    }
    
    public void runAuto(int autoChoice) {
        switch (autoChoice) {
            case 1: 
                //auto1();
                break;
            case 2:
                //auto2();
                break;
            case 3:
                //auto3();
                break;
            case 4:
                auto4();
                break;
            default:
                break;
        }
    }
    
    private boolean isTimerStarted() {
        return timerStarted;
    }
    
    private void startTimer() {
        timer.start();
        timerStarted = true;
    }
    
    //Drives forward for 3 seconds and then shoots
    /*
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
                //turret.reloadInit();
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
                            
                            //drivetrain.fieldDriveMecanumPolar(sensors.getGyroAngle(),
                            //                                  0.0, 
                            //                                  0.0, 
                            //                                  StaticVars.AUTONOMOUS_TWIST_MAGNITUDE);
                            
                        }
                    }
                }
            }
            
        }
        
    }
    */
    //Just like the other auto method, except it drives until
    //either the timer is up or the camera finds that the target is hot and within half a foot of shooting distance (arbitrary)
    /*
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
    */
    //does not use camera, just rangefinder, start status must equal "drive"
    /*
    private void auto3() {
        
        sensors.updateRangefinder();
        
        if (status.equals("drive")) {
            //drive robot
            //Robot will drive while the timer is running.
            turret.pullInit();
            if (sensors.getRangefinderDistanceFeet() < StaticVars.AUTO_SHOOT_DIST_FEET) {
                drivetrain.driveMecanumPolar(0.0, 0.0, 0.0);
                status = "lower";
                timer.reset();
                timer.start();
            } else drivetrain.driveMecanumPolar(StaticVars.AUTONOMOUS_DRIVE_MAGNITUDE, 0, 0);
        }
        
        if (status.equals("lower")){
              ballpickup.setBallRetrieverDown();
           if (timer.get() >= StaticVars.BALL_LOAD_TIME){
               ballpickup.setBallRetriverStop();
               status = "shoot";
           }
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
    }
    */
    //does not use camera or rangefinder, just timer
    private void auto4(){
        
        if (status.equals("drive")) {
            if (!this.timerStarted){
                timer.start();
                timerStarted = true;
            }
            //drive robot
            //Robot will drive while the timer is running.
            if (timer.get() >= StaticVars.AUTONOMOUS_DRIVE_TIMER) {
                drivetrain.driveMecanumPolar(0.0, 0.0, 0.0);
                status = "lower";
                timer.reset();
                timer.start();
            } else drivetrain.driveMecanumPolar(StaticVars.AUTONOMOUS_DRIVE_MAGNITUDE, 0, 0);
        }
        
        if (status.equals("lower")){
              ballpickup.setBallRetrieverDown();
           if (timer.get() >= StaticVars.BALL_LOAD_TIME){
               ballpickup.setBallRetriverStop();
               timer.stop();
               timer.reset();
               timerStarted = false;
               status = "load";
           }
        }
        
        if (status.equals("load")) {
            turret.pushInit();
            status = "wait";
        }
        
        if (status.equals("shoot")) {
                //shoot robot
                turret.setTriggerPull(true);
                status = "stopped";
        }
        
        if (status.equals("wait")) {
            if (!timerStarted) {
                timerStarted = true;
                timer.reset();
                timer.start();
            }
            
            if (timer.get() >= StaticVars.PUSH_TIME_LIMIT) {
                status = "shoot";
                timer.reset();
                timer.stop();
                timerStarted = false;
                
            }
        }
        
        if (status.equals("stopped")) {
            //do nothing
            //turret.setTriggerPull(true);
        }
    }
}
