
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class RobotHardware {
    
    //Joystick used to control robot functions
    private CustomJoystick joystick;
    
    //RobotDrive used to drive robot
    private RobotDrive robotDrive;
    
    private Jaguar jag1;
    private Jaguar jag2;
    private Jaguar jag3;
    private Jaguar jag4;
    
    
    
    public RobotHardware() {
        joystick = new CustomJoystick(StaticVars.JOYSTICK_PORT); //Construct joystick object using JOYSTICK_PORT
        
        jag1 = new Jaguar(StaticVars.FRONT_LEFT_MOTOR);
        jag2 = new Jaguar(StaticVars.REAR_LEFT_MOTOR);
        jag3 = new Jaguar(StaticVars.FRONT_RIGHT_MOTOR);
        jag4 = new Jaguar(StaticVars.REAR_RIGHT_MOTOR);
        robotDrive = new RobotDrive(jag1, jag2, jag3, jag4);
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
    public Jaguar getJaguar(int j) {
        switch (j) {
            case 1: 
                return jag1;
            case 2:
                return jag2;
            case 3: 
                return jag3;
            case 4: 
                return jag4;
            default:
                return null;
        }
    }
}

