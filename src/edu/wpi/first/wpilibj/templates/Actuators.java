
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

public class Actuators {

    private final Jaguar frontLeft;
    private final Jaguar rearLeft;
    private final Jaguar frontRight;
    private final Jaguar rearRight;
    
    private final Jaguar turretMotor;
    
    private final Solenoid solenoid1;
    
    public Actuators() {
        
        frontLeft = new Jaguar(StaticVars.FRONT_LEFT_MOTOR);
        rearLeft = new Jaguar(StaticVars.REAR_LEFT_MOTOR);
        frontRight = new Jaguar(StaticVars.FRONT_RIGHT_MOTOR);
        rearRight = new Jaguar(StaticVars.REAR_RIGHT_MOTOR);
        
        turretMotor = new Jaguar(StaticVars.TURRET_MOTOR);
        
        solenoid1 = new Solenoid(StaticVars.SOLENOID_ONE_PORT);
    }
    
    /**
     * I decided to use nested if-else statements to better encapsulate motor channels.
     * @param motorChannel
     * @return SpeedController (Jaguar, Talon, Victor)
     */
    public SpeedController getMotorController(int motorChannel) {
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
                        if (motorChannel == StaticVars.TURRET_MOTOR)
                            return turretMotor;
                        else {
                            return null;
                        }
                    }
                }
            }
        }
    }
    
    public Solenoid getSolenoid() {
        return solenoid1;
    }
    
}
