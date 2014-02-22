
package edu.wpi.first.wpilibj.templates;

public class Turret {
    
    Actuators actuators;
    
    public Turret(Actuators actuators) {
        this.actuators = actuators;
    }
    
    public void launch() {
        actuators.turnMagLockOff();
    }
    
}
