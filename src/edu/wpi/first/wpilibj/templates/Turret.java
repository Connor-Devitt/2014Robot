
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

public class Turret {
    
    boolean triggerPulled;
    boolean reloading;
    boolean pulling;
    boolean pushing;
    boolean pushTimerStarted;
    boolean pullTimerStarted;
    Timer reloadTimer;
    Actuators actuators;
    Sensors sensors;
    
    public Turret(Actuators actuators, Sensors sensors) {
        this.actuators = actuators;
        this.sensors = sensors;
        reloadTimer = new Timer();
        triggerPulled = false;
        reloading = false;
        pushTimerStarted = false;
        pullTimerStarted = false;
    }
    public void setTriggerPull(boolean isPulled){
        if (isPulled)
            actuators.turnMagLockOff();
        else actuators.turnMagLockOn();
    }
    
    public void reloadInit() {
        if(!reloading) {
            reloading = true;
            reloadTimer.start();
            pushTimerStarted = true;
            pullTimerStarted = false;
            actuators.setReloadRelayForward();
        }
    }
    
    public void pullInit() {
        if (!pulling) {
            pulling = true;
            pushing = false;
            reloadTimer.start();
            pullTimerStarted = true;
            pushTimerStarted = false;
            actuators.setReloadRelayReverse();
        }
    }
    
    public void pushInit() {
        if (!pushing) {
            pulling = false;
            pushing = true;
            reloadTimer.start();
            pullTimerStarted = false;
            pullTimerStarted = true;
            actuators.setReloadRelayForward();
        }
    }
    
    public void reloadUpdate() {
        if (pulling) {
            if (reloadTimer.get() > StaticVars.PULL_TIME_LIMIT) {
                actuators.setReloadRelayStop();
                pushTimerStarted = false;
                pullTimerStarted = false;
                reloadTimer.reset();
            } else actuators.setReloadRelayReverse();
        } else {
            if (pushing) {
                if (reloadTimer.get() > StaticVars.PUSH_TIME_LIMIT) {
                    actuators.setReloadRelayStop();
                    pushTimerStarted = false;
                    pullTimerStarted = false;
                    reloadTimer.reset();
                } else actuators.setReloadRelayForward();
            }
        }
    }
    
    public void reloadUpdateOld() {
        if (reloading){
            if (pushTimerStarted == true){
                if(reloadTimer.get() > StaticVars.PUSH_TIME_LIMIT) {
                    actuators.setReloadRelayReverse();
                    pushTimerStarted = false;
                    pullTimerStarted = true;
                    reloadTimer.reset();
                    reloadTimer.start();
                } else actuators.setReloadRelayForward();
            } else {
                if (pullTimerStarted ==true){
                    if(reloadTimer.get() > StaticVars.PULL_TIME_LIMIT || !sensors.reloadLimitReached()){
                        actuators.setReloadRelayStop();
                        pushTimerStarted = false;
                        pullTimerStarted = false;
                        reloading =false;
                        reloadTimer.reset();    
                    } else actuators.setReloadRelayReverse();
                }
            }
        }
    }
}
