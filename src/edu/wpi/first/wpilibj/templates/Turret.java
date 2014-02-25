
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

public class Turret {
    boolean triggerPulled;
    boolean reloading;
    boolean pushTimerStarted;
    Timer reloadTimer = new Timer();
    Actuators actuators;
    
    public Turret(Actuators actuators) {
        this.actuators = actuators;
    }
    public void setTriggerPull(boolean isPulled){
        if (isPulled)
            actuators.turnMagLockOff();
        else actuators.turnMagLockOn();
    }
        
    public void launch() {
        actuators.turnMagLockOff();
    }
    public void reload() {
        if(!reloading) {
            reloading = true;
            reloadTimer.start();
            pushTimerStarted = true;
            actuators.setreloadRelayForward();
        }
        actuators.turnMagLockOn();
        
    }
    
}
