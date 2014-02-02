
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.Timer;

//TODO implement!
public class Rangefinder extends I2C {
    
    DigitalModule digitalModule;
    int sevenBitAddress;
    Timer bootTimer;
    
    public Rangefinder() {
        super(DigitalModule.getInstance(StaticVars.RANGEFINDER_DIGITAL_MODULE), 0x70);
        sevenBitAddress = 0x70;  //defaul address will change later in constructor..
        digitalModule = DigitalModule.getInstance(StaticVars.RANGEFINDER_DIGITAL_MODULE);
        
        //change address
        super.write(sevenBitAddress*2, 0xAA);
        super.write(sevenBitAddress*2, 0xA5);
        super.write(sevenBitAddress*2, 0x80);
        sevenBitAddress = 0x80/2;
        bootTimer = new Timer();
        bootTimer.start();
        while (bootTimer.get() < 0.05) {/*Wait for sensor to reboot*/};
        bootTimer.stop();
    }
    
}
