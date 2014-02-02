
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;

public class Sensors {

    private final Encoder turretEncoder;
    private final Gyro gyro;
    private final Rangefinder rangefinder;  //TODO implement Rangefinder class
    
    public Sensors() {
        turretEncoder = new Encoder(StaticVars.TURRET_ENCODER_A_CHANNEL,
                                    StaticVars.TURRET_ENCODER_B_CHANNEL);
        gyro = new Gyro(StaticVars.GYRO_CHANNEL);
        rangefinder = new Rangefinder();
    }
    
}
