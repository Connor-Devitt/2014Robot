/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
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
    //Autonomous autonomous;
    Actuators actuators;
    Sensors sensors;
    DriveTrain driveTrain;
    //Turret turret;
    CustomJoystick joystick;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        //autonomous = new Autonomous(hardware);
        
        actuators = new Actuators();
        sensors = new Sensors();
        driveTrain = new DriveTrain(actuators);
        joystick = new CustomJoystick();
        
        /*
        LiveWindow.addActuator("Drive train", 
                             "front left motor", 
                             frontLeft);
        LiveWindow.addActuator("Drive train",
                             "front right motor",
                             frontRight);
        LiveWindow.addActuator("Drive train", 
                             "back left motor", 
                             rearLeft);
        LiveWindow.addActuator("Drive train", 
                             "back right motor",
                             rearRight);
        */
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        
        
        //autonomous.runAuto(-1); //negative value because we only have 1 auto function.
        
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        double direction = joystick.getDirectionDegrees();
        double magnitude = joystick.getMagnitude();
        double twist = joystick.getTwist();
        
        driveTrain.fieldDriveMecanumPolar(sensors.getGryro(), magnitude, direction, twist);
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
}
