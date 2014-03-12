/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Team5181Robot extends IterativeRobot {
    
    //Global Declarations
    Autonomous autonomous;
    Actuators actuators;
    Sensors sensors;
    DriveTrain driveTrain;
    CustomJoystick joystick;
    Turret turret;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
           
        actuators  = new Actuators();
        sensors    = new Sensors();
        driveTrain = new DriveTrain(actuators);
        joystick   = new CustomJoystick();
        turret     = new Turret(actuators, sensors);
    }
    
    public void autonomousInit() {
        autonomous = new Autonomous(driveTrain, sensors, turret);
        turret.reloadInit();
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        
        
        autonomous.runAuto(2); //negative value because we only have 1 auto function.
        turret.reloadUpdate();
        
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
        if (joystick.gyroResetButtonPressed()) {
            //System.out.println(sensors.getGyroAngle());
            sensors.resetGyro();
        }
        
        if (joystick.rangeButtonPressed()) {
            System.out.println(sensors.getRangefinderDistance());
        }
        
        if (joystick.pushReloadButtonPressed()) {
            turret.pushInit();
        }
        
        if (joystick.pullReloadButtonPressed()) {
            turret.pullInit();
        }
        
        turret.setTriggerPull(joystick.magLockTriggerButtonPressed());
       
        //Ball loading logic...
        if (joystick.getBallLoadValue() != 0) {
            if (joystick.getBallLoadValue() == -1) {
                //actuators.setBallLoadUp();
                actuators.setBallLoadRelayReverse();
            }
                
            if (joystick.getBallLoadValue() == 1) {
                actuators.setBallLoadRelayForward();
                //actuators.setBallLoadDown();
            }
                
        } else actuators.setballLoadRelayOff();/*actuators.setBallLoadStop();*/
        
        
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
        sensors.updateRangefinder();
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
}
