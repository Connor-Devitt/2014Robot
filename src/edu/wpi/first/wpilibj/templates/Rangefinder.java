
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SensorBase;

//TODO implement!
public class Rangefinder extends SensorBase {
    
    private I2C ranger;
    
    public Rangefinder() {
        ranger = DigitalModule.getInstance(1).getI2C(0xE0); //Use default shppd add
        
    }
    
    public void takeRange() {
        ranger.write(0xE0, 0x51);   //take range reading add=0xE0, cmmd=0x51
    }
    
    //returns distance in centimeters...
    public int getRange() {
        byte[] byteReturn = new byte[2];
        ranger.read(0xE1, 2, byteReturn);
        return (((int) byteReturn[0]) << 8) + ((int) byteReturn[1]);
    }
}
