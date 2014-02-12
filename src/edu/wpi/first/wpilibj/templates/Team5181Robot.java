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
    //RobotHardware hardware;
    
    //Autonomous autonomous;
    //Actuators actuators;
    //Sensors sensors;
    //DriveTrain driveTrain;
    //Turret turret;
    CustomJoystick joystick;
    //RobotDrive used to drive robot
    RobotDrive robotDrive;
    
    //Jaguar motor controllers used by robotDrive object
    Talon frontLeft;
    Talon rearLeft;
    Talon frontRight;
    Talon rearRight;
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        //hardware = new RobotHardware();
        //autonomous = new Autonomous(hardware);
        
        //actuators = new Actuators();
        //sensors = new Sensors();
        //driveTrain = new DriveTrain(actuators);
        joystick = new CustomJoystick();
        frontLeft  = new Talon(StaticVars.FRONT_LEFT_MOTOR);
        rearLeft   = new Talon(StaticVars.REAR_LEFT_MOTOR);
        frontRight = new Talon(StaticVars.FRONT_RIGHT_MOTOR);
        rearRight  = new Talon(StaticVars.REAR_RIGHT_MOTOR);
        robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
        //TestPeriodic initializations
        
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
        
    }
    
    public void robotDisabled() {
        
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
        LiveWindow.run();
        double direction = joystick.getDirectionDegrees();
        double magnitude = joystick.getMagnitude();
        double twist = joystick.getTwist();
        
        //hardware.getRobotDrive().mecanumDrive_Polar(magnitude, direction, twist);
        robotDrive.mecanumDrive_Polar(magnitude, direction, twist);
        //driveTrain.fieldDriveMecanumPolar(sensors.getGryro(), magnitude, direction, twist);
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        //LiveWindow.run();
    }
    
}
