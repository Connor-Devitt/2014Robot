
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Gyro;

public class DriveTrain extends RobotDrive {

    public DriveTrain(Actuators actuators) {
        super(actuators.getMotorController(StaticVars.FRONT_LEFT_MOTOR),
              actuators.getMotorController(StaticVars.REAR_LEFT_MOTOR),
              actuators.getMotorController(StaticVars.FRONT_RIGHT_MOTOR),
              actuators.getMotorController(StaticVars.REAR_RIGHT_MOTOR));
    }
    
    void fieldDriveMecanumPolar(Gyro gyro, double magnitude, double direction, double rotation) {
        //TODO: implement field-centric driving
        direction = direction - (gyro.getAngle() % 360);
        super.mecanumDrive_Polar(magnitude, direction, rotation);
    }
    
    //DriveTrain testing method to be run during robotInitialization.
    public void testDriveTrain() {
        //TODO create a hardware test
    }
    
}
