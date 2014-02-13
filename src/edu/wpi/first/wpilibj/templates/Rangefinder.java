
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.Timer;

//TODO implement!
public class Rangefinder extends I2C {
    
    private int sevenBitAddress;
    private String state;
    private Timer stateTimer;
    private double distance;    //measured in centimeters.
    
    public Rangefinder(int addressHex) {
        
        super(DigitalModule.getInstance(StaticVars.RANGEFINDER_DIGITAL_MODULE), 0x70);
        stateTimer = new Timer();
        Timer.delay(0.15);  //Boot delay of 150ms   make sure pin 1 is grounded (low).
        sevenBitAddress = 0x70;  //defaul address will change later in constructor..
        distance = 0;
        //change address
        changeAddress(addressHex);
        
        /*
        
        super.write(sevenBitAddress*2, 0xAA);
        super.write(sevenBitAddress*2, 0xA5);
        super.write(sevenBitAddress*2, 0x80);
        
        sevenBitAddress = addressHex/2; //0xH0 -> form of address to keep things simple
        Timer.delay(0.15);  //Time needed to reboot
        */
    }
    
    private void changeAddress(int addressHex) {
        byte[] dataToSend = new byte[4];
        byte[] dataReceived = new byte[0];
        
        dataToSend[0] = (byte) (sevenBitAddress*2);
        dataToSend[1] = (byte) 0xAA;
        dataToSend[2] = (byte) 0xA5;
        dataToSend[3] = (byte) addressHex;
        
        boolean aborted = super.transaction(dataToSend, 4, dataReceived, 0);
        sevenBitAddress = addressHex/2;
    }
    
    private void takeRange() {
        byte[] rangeCommand = new byte[2];
        byte[] rangeResponse = new byte[0];
        
        rangeCommand[0] = (byte) (sevenBitAddress*2);
        rangeCommand[1] = (byte) 0x51;
        boolean response = transaction(rangeCommand, 2, rangeResponse, 0);
        //Timer.delay(0.1); //delay for 100 ms.
    }
    
    private byte[] retrieveRange() {
        byte[] readAddress = new byte[1];
        byte[] range = new byte[2];
        readAddress[0] = (byte) (sevenBitAddress*2 + 1);
        boolean response = transaction(readAddress, 1, range, 2);
        return range;
    }
    
    
    public void calcDistance() {
        if (state != "ranging" && state != "retrieving") {
            takeRange();
            state = "ranging";
            stateTimer.reset();
            stateTimer.start();
        }
        
        if (state == "ranging" && stateTimer.get() > 0.1) {
            byte[] range = retrieveRange();
            distance = range[0] + 0xFF + range[1];
            state = "waiting";
            stateTimer.stop();
            stateTimer.reset();
        }
    }
    
    public double getDistance() {
        return distance;
    }
    
}
