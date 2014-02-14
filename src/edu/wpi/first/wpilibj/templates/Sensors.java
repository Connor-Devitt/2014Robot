
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Relay;

public class Sensors {

    private final Encoder turretEncoder;
    private final Gyro gyro;
    private final Rangefinder rangefinder;
    private final Relay magLockRelay;
    
    public Sensors() {
        turretEncoder = new Encoder(StaticVars.TURRET_ENCODER_A_CHANNEL,
                                    StaticVars.TURRET_ENCODER_B_CHANNEL);
        gyro = new Gyro(StaticVars.GYRO_CHANNEL);
        gyro.reset();   //zero the gyro
        rangefinder = new Rangefinder();
        magLockRelay = new Relay(StaticVars.MAG_LOCK_RELAY_CHANNEL);
    }
    
    public Gyro getGryro() {
        return gyro;
    }
    
    public Rangefinder getRangefinder() {
        return rangefinder;
    }
    
    public Relay getMagLockRelay() {
        return magLockRelay;
    }
    
}
