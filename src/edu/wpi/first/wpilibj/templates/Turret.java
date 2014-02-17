
package edu.wpi.first.wpilibj.templates;

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
        
    }
    
    public void reload() {
        
    }
    
}
