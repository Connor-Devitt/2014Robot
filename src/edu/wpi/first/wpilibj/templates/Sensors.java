
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.DigitalInput;

public class Sensors {

    private final Gyro gyro;
    private final Rangefinder rangefinder;
    private final DigitalInput ballLoadDownLimit;
    private final DigitalInput ballLoadUpLimit;
    
    public Sensors() {
        gyro = new Gyro(StaticVars.GYRO_CHANNEL);
        gyro.reset();   //zero the gyro
        rangefinder = new Rangefinder();
        ballLoadDownLimit = new DigitalInput(StaticVars.BALL_LOAD_DOWN_LIMIT_CHANNEL);
        ballLoadUpLimit = new DigitalInput(StaticVars.BALL_LOAD_UP_LIMIT_CHANNEL);
        //rangefinder.update();
    }
    
    public double getGyroAngle() {
        return gyro.getAngle();
    }
    
    public void resetGyro() {
        gyro.reset();
    }
    
    //Returns int, measured in centimeters...
    
    public int getRangefinderDistance() {
        return rangefinder.getDistance();
    }
    
    public void updateRangefinder() {
        rangefinder.update();
    }
    
    public boolean ballLoadDownLimitReached() {
        return ballLoadDownLimit.get();
    }
    
    public boolean ballLoadUpLimitReached() {
        return ballLoadUpLimit.get();
    }
    
}
