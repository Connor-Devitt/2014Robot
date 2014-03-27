
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

public class Turret {
    
    boolean triggerPulled;
    boolean reloading;
    boolean pulling;
    boolean pushing;
    boolean pushTimerStarted;
    boolean pullTimerStarted;
    //Timer reloadTimer;
    Timer pullTimer;
    Timer pushTimer;
    Actuators actuators;
    Sensors sensors;
    
    public Turret(Actuators actuators, Sensors sensors) {
        this.actuators = actuators;
        this.sensors = sensors;
        //reloadTimer = new Timer();
        triggerPulled = false;
        reloading = false;
        pushTimerStarted = false;
        pullTimerStarted = false;
        pushing = false;
        pulling = false;
        pullTimer = new Timer();
        pushTimer = new Timer();
    }
    public void setTriggerPull(boolean isPulled){
        if (isPulled)
            actuators.turnMagLockOff();
        else actuators.turnMagLockOn();
    }
    /*
    public void reloadInit() {
        if(!reloading) {
            reloading = true;
            reloadTimer.start();
            pushTimerStarted = true;
            pullTimerStarted = false;
            actuators.setReloadRelayForward();
        }
    }
    */
    public void pullInit() {
        if (!pulling) {
            pulling = true;
            pushing = false;
            pullTimer.reset();
            pullTimer.start();
            pullTimerStarted = true;
            pushTimerStarted = false;
            actuators.setReloadRelayForward();
        }
    }
    
    public void pushInit() {
        if (!pushing) {
            pulling = false;
            pushing = true;
            //reloadTimer.start();
            pullTimer.reset();
            pullTimer.start();
            pullTimerStarted = false;
            pullTimerStarted = true;
            actuators.setReloadRelayReverse();
        }
    }
    
    public void reloadUpdate() {
        if (pulling) {
            if (pullTimer.get() > StaticVars.PULL_TIME_LIMIT) {
                actuators.setReloadRelayStop();
                pushTimerStarted = false;
                pullTimerStarted = false;
                pulling = false;
                pushing = false;
                //pullTimer.reset();
            } else actuators.setReloadRelayForward();
        } else {
            if (pushing) {
                if (pushTimer.get() > StaticVars.PUSH_TIME_LIMIT) {
                    actuators.setReloadRelayStop();
                    pushTimerStarted = false;
                    pullTimerStarted = false;
                    pulling = false;
                    pushing  = false;
                    //pushTimer.reset();
                } else actuators.setReloadRelayReverse();
            } else actuators.setReloadRelayStop();
        }
    }
}
