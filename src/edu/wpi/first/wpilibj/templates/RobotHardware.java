
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class RobotHardware {
    
    //Joystick used to control robot functions
    private final CustomJoystick joystick;
    
    //RobotDrive used to drive robot
    private final RobotDrive robotDrive;
    
    private final Jaguar frontLeft;
    private final Jaguar rearLeft;
    private final Jaguar frontRight;
    private final Jaguar rearRight;
    
    
    
    public RobotHardware() {
        joystick = new CustomJoystick(StaticVars.JOYSTICK_PORT); //Construct joystick object using JOYSTICK_PORT
        
        frontLeft  = new Jaguar(StaticVars.FRONT_LEFT_MOTOR);
        rearLeft   = new Jaguar(StaticVars.REAR_LEFT_MOTOR);
        frontRight = new Jaguar(StaticVars.FRONT_RIGHT_MOTOR);
        rearRight  = new Jaguar(StaticVars.REAR_RIGHT_MOTOR);
        
        robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
    }
    
    //Hardware testing method to be run during robotInitialization.
    public void testHardware() {
        //TODO create a hardware test
    }
    
    //Accessor Methods
    public Joystick getJoystick() {
        return joystick;
    }
    
    public RobotDrive getRobotDrive() {
        return robotDrive;
    }
    
    /**
     * I decided to use nested if-else statements to better encapsulate motor channels.
     * @param motorChannel
     * @return jaguar motor controller
     */
    public Jaguar getJaguar(int motorChannel) {
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
        /*switch (j) {
            case 1: 
                return frontLeft;
            case 2:
                return rearLeft;
            case 3: 
                return frontRight;
            case 4: 
                return rearRight;
            default:
                return null;
        }*/
    }
}

