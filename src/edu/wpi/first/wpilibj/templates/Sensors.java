
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.DigitalInput;

public class Sensors {

    //private final Gyro gyro;
    private final Rangefinder rangefinder;
    //private final AnalogChannel gyroChannel;
    
    public Sensors() {
        /*
        gyroChannel = new AnalogChannel(StaticVars.GYRO_CHANNEL);
        gyro = new Gyro(gyroChannel);
        gyro.setSensitivity(.007);
        gyro.reset();   //zero the gyro
        */
        
        rangefinder = new Rangefinder();
        rangefinder.checkRangefinder();
        rangefinder.startThread();
        
    }
    /*
    public double getGyroAngle() {
        return gyro.getAngle();
    }
    
    public double getGyroChannelVoltage() {
        return gyroChannel.getVoltage();
    }
    
    public void resetGyro() {
        gyro.reset();
    }
    */
    
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
