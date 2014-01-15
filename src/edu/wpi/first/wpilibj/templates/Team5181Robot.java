/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Team5181Robot extends IterativeRobot {
    
    //Declarations
    Joystick joystick;
    RobotDrive robotDrive;
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        
        joystick = new Joystick(StaticVars.JOYSTICK_PORT); //Construct joystick object using JOYSTICK_PORT
        
        robotDrive = new RobotDrive(StaticVars.FRONT_LEFT_MOTOR,
                                    StaticVars.REAR_LEFT_MOTOR,
                                    StaticVars.FRONT_RIGHT_MOTOR, 
                                    StaticVars.REAR_RIGHT_MOTOR);
        
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
        //1. get driver station data
        double degDir = joystick.getDirectionRadians()*180.0/Math.PI;
        double magnitude = joystick.getMagnitude();
        //2. get sensor data
        //3. process data
        //4. output data
        robotDrive.mecanumDrive_Polar(magnitude, degDir, 0);    //drive robot with no twist right now.
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
