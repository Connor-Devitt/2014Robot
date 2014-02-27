
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Gyro;

public class Sensors {

    private final Gyro gyro;
    private final Rangefinder rangefinder;
    
    public Sensors() {
        
        gyro = new Gyro(StaticVars.GYRO_CHANNEL);
        gyro.setSensitivity(.007);
        gyro.reset();   //zero the gyro
        
        rangefinder = new Rangefinder();
        rangefinder.checkRangefinder();
        rangefinder.startThread();
        
    }
    
    public double getGyroAngle() {
        return gyro.getAngle();
    }
    
    public void resetGyro() {
        gyro.reset();
    }
    
    /*
    * Returns int, measured in centimeters.
    */
    public int getRangefinderDistance() {
        return rangefinder.getDistance();
    }
    
   public void updateRangefinder() {
        rangefinder.update();
   }
}
