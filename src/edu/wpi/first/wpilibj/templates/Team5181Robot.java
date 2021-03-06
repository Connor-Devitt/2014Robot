/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.DriverStationLCD;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Team5181Robot extends IterativeRobot {
    
    Autonomous autonomous;
    Actuators actuators;
    Sensors sensors;
    DriveTrain driveTrain;
    CustomJoystick joystick;
    Turret turret;
    BallRetriever ballRetriever;
    DriverStationLCD station;
    
    public void robotInit() {
           
        actuators     = new Actuators();
        sensors       = new Sensors();
        driveTrain    = new DriveTrain(actuators);
        joystick      = new CustomJoystick();
        turret        = new Turret(actuators, sensors);
        ballRetriever = new BallRetriever(actuators);
        station       = DriverStationLCD.getInstance();
    }
    
    public void autonomousInit() {
         autonomous = new Autonomous(driveTrain, sensors, turret, actuators, ballRetriever);
        //turret.reloadInit();
    }
    
    public void autonomousPeriodic() {
    
        autonomous.runAuto(4); 
        turret.reloadUpdate();
        
    }
    
    public void teleopPeriodic() {
        /*
        if (joystick.gyroResetButtonPressed()) {
            //System.out.println(sensors.getGyroAngle());
            sensors.resetGyro();
        }
        */
        /*
        if (joystick.rangeButtonPressed()) {
            //System.out.println(sensors.getRangefinderDistance());
            station.println(DriverStationLCD.Line.kUser1, 1, "" + sensors.getRangefinderDistanceFeet());
            station.updateLCD();
        }
        */
        if (joystick.pushReloadButtonPressed()) {
            turret.pushInit();
            //System.out.println("Pushing");
            
        }
        
        if (joystick.pullReloadButtonPressed()) {
            turret.pullInit();
            //System.out.println("pulling");
            
        }
        
        
        if (joystick.intakeWheelsForwardButtonPressed()) {
            if (ballRetriever.isIntakeWheelsOn() && ballRetriever.isIntakeWheelsForward())
                ballRetriever.stopIntakeWheels();
            else {
                if (ballRetriever.isIntakeWheelsOn())     //rev direction 1st stop then strt crrct dir
                    ballRetriever.stopIntakeWheels();
                ballRetriever.startIntakeWheels();
            }
        }
        
        if (joystick.intakeWheelsReverseButtonPressed()) {
            if (ballRetriever.isIntakeWheelsOn() && !ballRetriever.isIntakeWheelsForward())
                ballRetriever.stopIntakeWheels();
            else {
                if (ballRetriever.isIntakeWheelsOn())
                    ballRetriever.stopIntakeWheels();
                ballRetriever.startIntakeWheelsReverse();
            }
        }
        
        turret.setTriggerPull(joystick.magLockTriggerButtonPressed());
       
        //Ball loading logic...
        if (joystick.getBallLoadValue() != 0) {
            if (joystick.getBallLoadValue() == -1)
                 ballRetriever.setBallRetrieverUp();
            else ballRetriever.setBallRetrieverDown();
        } else   ballRetriever.setBallRetriverStop();
        
        
        driveTrain.driveMecanumPolar(joystick.getMagnitude(),
                                     joystick.getDirectionDegrees(),
                                     joystick.getTwist());
        
        /*
        driveTrain.fieldDriveMecanumPolar(sensors.getGyroAngle(),
                                          joystick.getMagnitude(),
                                          joystick.getDirectionDegrees(),
                                          joystick.getTwist());
        */
        turret.reloadUpdate();
        //sensors.updateRangefinder();
        
    }
    
    public void testPeriodic() {
    
    }
    
}
