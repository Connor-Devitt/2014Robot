
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.Timer;

//TODO implement!
public class Rangefinder extends I2C {
    
    private DigitalModule digitalModule;
    private int sevenBitAddress;
    private byte[] bytes = new byte[2];
    
    public Rangefinder(int addressHex) {
        super(DigitalModule.getInstance(StaticVars.RANGEFINDER_DIGITAL_MODULE), 0x70);
        
        Timer.delay(0.15);  //Boot delay of 150ms   make sure pin 1 is grounded (low).
        sevenBitAddress = 0x70;  //defaul address will change later in constructor..
        digitalModule = DigitalModule.getInstance(StaticVars.RANGEFINDER_DIGITAL_MODULE);
        
        //change address
        super.write(sevenBitAddress*2, 0xAA);
        super.write(sevenBitAddress*2, 0xA5);
        super.write(sevenBitAddress*2, 0x80);
        
        sevenBitAddress = addressHex/2; //0xH0 -> form of address to keep things simple
        Timer.delay(0.05);  //Time needed to reboot
        
    }
    
    private void readBytes() {
        
    }
    
    public double getDist() {
        return 0.0;
    }
    
}
