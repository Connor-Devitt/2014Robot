
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.Timer;

//TODO implement!
public class Rangefinder extends SensorBase {
    
    private I2C ranger;
    private int latestDist;
    private byte[] byteReturn;
    private RangeThread rangeThread;
        
    
    public Rangefinder() {
        ranger = DigitalModule.getInstance(1).getI2C(0xE0); //Use default shppd add
        latestDist = -1;
        byteReturn = new byte[2];
        rangeThread = new RangeThread();
    }
    
    private void takeRange() {
        ranger.write(0xE0, 0x51);   //take range reading addr=0xE0, cmmd=0x51
    }
    
    //returns distance in centimeters...
    private int getRange() {
        ranger.read(0xE1, 2, byteReturn);
        return (int) ((byteReturn[0] << 8) | byteReturn[1]);
    }
    
    public int getDistance() {
        return latestDist;
    }
    
    public void update() {
        if (!rangeThread.isAlive())
            rangeThread.start();
    }
    
    private class RangeThread extends Thread {
        public void run() {
            takeRange();
            Timer.delay(StaticVars.RANGE_DELAY);
            latestDist = getRange();
        }
    
    }
}
