
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.RobotDrive;

public class DriveTrain  {
    
    private final RobotDrive robotDrive;
    
    public DriveTrain(Actuators actuators) {
        robotDrive = new RobotDrive(actuators.getMotorController(StaticVars.FRONT_LEFT_MOTOR),
                                    actuators.getMotorController(StaticVars.REAR_LEFT_MOTOR),
                                    actuators.getMotorController(StaticVars.FRONT_RIGHT_MOTOR),
                                    actuators.getMotorController(StaticVars.REAR_RIGHT_MOTOR));
    }
    
    public void fieldDriveMecanumPolar(double gyroAngle, double magnitude, double direction, double rotation) {
        direction = direction - (gyroAngle % 360);    //assume cw is +, ccw is -
        robotDrive.mecanumDrive_Polar(magnitude, direction, rotation);
    } 
    
    public void driveMecanumPolar(double magnitude, double direction, double rotation) {
        robotDrive.mecanumDrive_Polar(magnitude, direction, rotation);
    }
    
    //DriveTrain testing method to be run during robotInitialization.
    public void testDriveTrain() {
        //TODO create a hardware test
    }
    
}
