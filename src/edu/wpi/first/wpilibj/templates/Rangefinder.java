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
    private byte readByte;
    private byte writeByte;
    
    public Rangefinder() {
        ranger = DigitalModule.getInstance(1).getI2C(0xE0); //Use default shppd add
        ranger.setCompatabilityMode(true);
        latestDist = -1;
        byteReturn = new byte[2];
        rangeThread = new RangeThread();
        readByte = (byte) 0xE1;
        writeByte = (byte) 0xE0;
        Timer.delay(0.1);
    }
    
    private void takeRange() {
        byte[] dataToSend = new byte[2];
        byte[] dataToReceive = new byte[0];
        dataToSend[0] = writeByte;
        dataToSend[1] = (byte) 0x51;
        ranger.transaction(dataToSend, 2, dataToReceive, 0);
        //ranger.write(0x70, 0x51);   //take range reading addr=0xE0, cmmd=0x51
    }
    
    //returns distance in centimeters...
    private int getRange() {
        //ranger.read(0x70, 2, byteReturn);
        byte[] dataToSend = new byte[1];
        dataToSend[0] = readByte;
        ranger.transaction(dataToSend, 1, byteReturn, 2);
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
        if (!ranger.addressOnly())
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
