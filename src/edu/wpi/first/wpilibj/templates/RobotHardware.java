
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;

public class RobotHardware {
    
    //Joystick used to control robot functions
    //private final CustomJoystick joystick;
    
    //RobotDrive used to drive robot
    private final RobotDrive robotDrive;
    
    //Jaguar motor controllers used by robotDrive object
    private final Talon frontLeft;
    private final Talon rearLeft;
    private final Talon frontRight;
    private final Talon rearRight;
    
    public RobotHardware() {
        //joystick = new CustomJoystick();
        
        frontLeft  = new Talon(StaticVars.FRONT_LEFT_MOTOR);
        rearLeft   = new Talon(StaticVars.REAR_LEFT_MOTOR);
        frontRight = new Talon(StaticVars.FRONT_RIGHT_MOTOR);
        rearRight  = new Talon(StaticVars.REAR_RIGHT_MOTOR);
        
        robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
    }
    
    //Hardware testing method to be run during robotInitialization.
    public void testHardware() {
        //TODO create a hardware test
    }
    
    //Accessor Methods
    /*
    public Joystick getJoystick() {
        return joystick;
    }
    */
    public RobotDrive getRobotDrive() {
        return robotDrive;
    }
    
    /**
     * I decided to use nested if-else statements to better encapsulate motor channels.
     * @param motorChannel
     * @return jaguar motor controller
     */
    public Talon getTalon(int motorChannel) {
        if (motorChannel == StaticVars.FRONT_LEFT_MOTOR) {
            return frontLeft;
        } else {
            if (motorChannel == StaticVars.REAR_LEFT_MOTOR) {
                return rearLeft;
            } else {
                if (motorChannel == StaticVars.FRONT_RIGHT_MOTOR) {
                    return frontRight;
                } else {
                    if (motorChannel == StaticVars.REAR_RIGHT_MOTOR) {
                        return rearRight;
                    } else {
                        return null;
                    }
                }
            }
        }
        
    }
}

