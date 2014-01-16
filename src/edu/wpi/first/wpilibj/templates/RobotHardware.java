
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class RobotHardware {
    
    //Joystick used to control robot functions
    private CustomJoystick joystick;
    
    //RobotDrive used to drive robot
    private RobotDrive robotDrive;
    
    public RobotHardware() {
        joystick = new CustomJoystick(StaticVars.JOYSTICK_PORT); //Construct joystick object using JOYSTICK_PORT
        robotDrive = new RobotDrive(StaticVars.FRONT_LEFT_MOTOR,
                                    StaticVars.REAR_LEFT_MOTOR,
                                    StaticVars.FRONT_RIGHT_MOTOR, 
                                    StaticVars.REAR_RIGHT_MOTOR);
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

}
