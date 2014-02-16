package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.Timer;

public class Rangefinder extends SensorBase {
    
    private I2C ranger;
    private int latestDist;
    private byte[] byteReturn;
    private RangeThread rangeThread;
    
    public Rangefinder() {
        ranger = DigitalModule.getInstance(1).getI2C(0x70); //Use default shppd add
        latestDist = -1;
        byteReturn = new byte[2];
        rangeThread = new RangeThread();
        Timer.delay(0.1);
    }
    
    private void takeRange() {
        ranger.write(0x70, 0x51);   //take range reading addr=0xE0, cmmd=0x51
    }
    
    //returns distance in centimeters...
    private int getRange() {
        ranger.read(0x70, 2, byteReturn);
        return (int) ((byteReturn[0] << 8) | byteReturn[1]);
    }
    
    public void startThread() {
        rangeThread.start();
        System.out.println("Thread started.");
    }
    
    public int getDistance() {
        return latestDist;
    }
    
    public void update() {
        if (!rangeThread.isAlive()) {
            System.out.println("Thread stopped, starting");
            rangeThread.start();
        }
    }
    
    public void checkRangefinder() {
        if (ranger.addressOnly())
            System.out.println("I2C Rangefinder Address: SUCCESS");
        else System.out.println("I2C Rangefinder Address: FAILURE");
    }
    
    private class RangeThread extends Thread {
        public void run() {
            while (true){
                takeRange();
                Timer.delay(StaticVars.RANGE_DELAY);
                latestDist = getRange();
                Timer.delay(StaticVars.RANGE_DELAY);
                //System.out.println("LatestDist updated");
            }
       }
    }
}
