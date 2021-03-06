
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class Actuators {

    private final Talon frontLeft;
    private final Talon rearLeft;
    private final Talon frontRight;
    private final Victor rearRight;
    //private final Victor ballLift;
    
    private final Relay magLockRelay;
    private final Relay ballLoadPivotRelay;
    private final Relay reloadRelay;
    private final Relay ballLoadWheelRelay;
    
    public Actuators() {
        
        frontLeft = new Talon(StaticVars.FRONT_LEFT_MOTOR);
        rearLeft = new Talon(StaticVars.REAR_LEFT_MOTOR);
        frontRight = new Talon(StaticVars.FRONT_RIGHT_MOTOR);
        rearRight = new Victor(StaticVars.REAR_RIGHT_MOTOR);
        //ballLift = new Victor(StaticVars.BALL_LIFT_MOTOR);
        
        magLockRelay = new Relay(StaticVars.MAG_LOCK_RELAY_CHANNEL);
        magLockRelay.set(Relay.Value.kOn);
        ballLoadPivotRelay = new Relay(StaticVars.BALL_LOAD_PIVOT_RELAY);
        ballLoadWheelRelay = new Relay(StaticVars.BALL_LOAD_WHEEL_RELAY);
        reloadRelay = new Relay(StaticVars.RELOAD_RELAY_CHANNEL);
        
    }
    
    public void turnMagLockOff() {
        magLockRelay.set(Relay.Value.kOff);
    }
    
    public void turnMagLockOn() {
        magLockRelay.set(Relay.Value.kOn);
    }
    /*
    public void setBallLoadDown() {
        ballLift.set(StaticVars.BALL_LIFT_SPEED_DOWN);
    }
    
    public void setBallLoadUp() {
        ballLift.set(StaticVars.BALL_LIFT_SPEED_UP);
    }
    
    public void setBallLoadStop() {
        ballLift.set(0.0);
    }
    */
    
    public void setBallLoadPivotRelayForward() {
        ballLoadPivotRelay.set(Relay.Value.kForward);
    }
    
    public void setBallLoadPivotRelayReverse() {
        ballLoadPivotRelay.set(Relay.Value.kReverse);
    }
    
    public void setBallLoadPivotRelayOff() {
        ballLoadPivotRelay.set(Relay.Value.kOff);
    }
    
    public void setBallLoadWheelRelayForward() {
        ballLoadWheelRelay.set(Relay.Value.kForward);
    }
    
    public void setBallLoadWheelRelayReverse() {
        ballLoadWheelRelay.set(Relay.Value.kReverse);
    }
    
    public void setBallLoadWheelRelayOff() {
        ballLoadWheelRelay.set(Relay.Value.kOff);
    }
    
    public void setReloadRelayForward() {
        reloadRelay.set(Relay.Value.kForward);
    }
    
    public void setReloadRelayReverse() {
        reloadRelay.set(Relay.Value.kReverse);
    }
    
    public void setReloadRelayStop() {
        reloadRelay.set(Relay.Value.kOff);
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
                        //if (motorChannel == StaticVars.BALL_LIFT_MOTOR) {
                        //    return ballLift;
                        //} else {
                            return null;
                        //}
                    }
                }
            }
        }
    }
}
