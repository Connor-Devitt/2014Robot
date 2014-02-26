
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

public class Turret {
    boolean triggerPulled;
    boolean reloading;
    boolean pushTimerStarted;
    boolean pullTimerStarted;
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
   
    
    public void ReloadInit() {
        if(!reloading) {
            reloading = true;
            reloadTimer.start();
            pushTimerStarted = true;
            pullTimerStarted = false;
            actuators.setreloadRelayForward();
        }
    }
    public void ReloadUpdate() {
        if (reloading){
            if (pushTimerStarted == true){
                if(reloadTimer.get() > StaticVars.PUSH_TIME_LIMIT) {
                    actuators.setreloadRelayReverse();
                    pushTimerStarted = false;
                    pullTimerStarted = true;
                    reloadTimer.reset();
                    reloadTimer.start();
                } else actuators.setreloadRelayForward();
            } else {
                    if (pullTimerStarted ==true){
                        if(reloadTimer.get() > StaticVars.PULL_TIME_LIMIT){
                            actuators.setreloadRelayStop();
                            pushTimerStarted = false;
                            pullTimerStarted = false;
                            reloading =false;
                            reloadTimer.reset();    
                       } else actuators.setBallLoadRelayReverse();
                
                    }
               }
        }
    }
}
