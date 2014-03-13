
package edu.wpi.first.wpilibj.templates;

public class BallRetriever {
    private final Actuators actuators;
    private boolean intakeWheelsOn, intakeWheelsForward;
    
    public BallRetriever(Actuators actuators) {
        this.actuators = actuators;
        intakeWheelsOn = false;
        intakeWheelsForward = true;
    }
    
    public void setBallRetrieverDown() {
        actuators.setBallLoadPivotRelayForward();
    }
    
    public void setBallRetrieverUp() {
        actuators.setBallLoadPivotRelayReverse();
    }
    
    public void setBallRetriverStop() {
        actuators.setBallLoadPivotRelayOff();
    }
    
    public void startIntakeWheels() {
        actuators.setBallLoadWheelRelayForward();
    }
    
    public void stopIntakeWheels() {
        actuators.setBallLoadWheelRelayOff();
    }
    
    public void startIntakeWheelsReverse() {
        actuators.setBallLoadWheelRelayReverse();
    }
    
    public boolean intakeWheelsForward() {
        return intakeWheelsForward;
    }
    
    public boolean intakeWheelsOn() {
        return intakeWheelsOn;
    }
    
}
