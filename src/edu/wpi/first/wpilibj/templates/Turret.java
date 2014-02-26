
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

public class Turret {
    
    String state;
    Sensors sensors;
    Actuators actuators;
    
    public Turret(Sensors sensors, Actuators actuators) {
        state = "wait";
        this.sensors = sensors;
        this.actuators = actuators;
    }
    
    public void shoot() {
        actuators.turnMagLockOff();
        Timer.delay(StaticVars.MAG_LOCK_DELAY);
        actuators.turnMagLockOn();
    }
    
    public void reload() {
        
    }
    
}
