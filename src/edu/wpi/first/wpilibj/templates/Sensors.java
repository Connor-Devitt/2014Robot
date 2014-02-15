
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.DigitalInput;

public class Sensors {

    private final Encoder turretEncoder;
    private final Gyro gyro;
    private final Rangefinder rangefinder;
    private final Relay magLockRelay;
    private final Relay ballLoadRelay;
    private final DigitalInput ballLoadDownLimit;
    private final DigitalInput ballLoadUpLimit;
    
    public Sensors() {
        turretEncoder = new Encoder(StaticVars.TURRET_ENCODER_A_CHANNEL,
                                    StaticVars.TURRET_ENCODER_B_CHANNEL);
        gyro = new Gyro(StaticVars.GYRO_CHANNEL);
        gyro.reset();   //zero the gyro
        rangefinder = new Rangefinder();
        magLockRelay = new Relay(StaticVars.MAG_LOCK_RELAY_CHANNEL);
        ballLoadRelay = new Relay(StaticVars.BALL_LOAD_RELAY_CHENNEL);
        ballLoadDownLimit = new DigitalInput(StaticVars.BALL_LOAD_DOWN_LIMIT_CHANNEL);
        ballLoadUpLimit = new DigitalInput(StaticVars.BALL_LOAD_UP_LIMIT_CHANNEL);
    }
    
    public double getGyroAngle() {
        return gyro.getAngle();
    }
    
    public void resetGyro() {
        gyro.reset();
    }
    
    //Returns int, measured in centimeters...
    public int getRangefinderDistance() {
        return rangefinder.getRange();
    }
    
    public void turnMagLockOff() {
        magLockRelay.set(Relay.Value.kOff);
    }
    
    public void turnMagLockOn() {
        magLockRelay.set(Relay.Value.kOn);
    }
    
    public void setBallLoadRelayForward() {
        ballLoadRelay.set(Relay.Value.kForward);
    }
    
    public void setBallLoadRelayReverse() {
        ballLoadRelay.set(Relay.Value.kReverse);
    }
    
    public void setballLoadRelayOff() {
        ballLoadRelay.set(Relay.Value.kOff);
    }
    
    public boolean ballLoadDownLimitReached() {
        return ballLoadDownLimit.get();
    }
    
    public boolean ballLoadUpLimitReached() {
        return ballLoadDownLimit.get();
    }
    
}
