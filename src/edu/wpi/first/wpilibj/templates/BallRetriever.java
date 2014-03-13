
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
        intakeWheelsOn = true;
        intakeWheelsForward = true;
    }
    
    public void stopIntakeWheels() {
        actuators.setBallLoadWheelRelayOff();
        intakeWheelsOn = false;
    }
    
    public void startIntakeWheelsReverse() {
        actuators.setBallLoadWheelRelayReverse();
        intakeWheelsOn = true;
        intakeWheelsForward = false;
    }
    
    public boolean isIntakeWheelsForward() {
        return intakeWheelsForward;
    }
    
    public boolean isIntakeWheelsOn() {
        return intakeWheelsOn;
    }
    
}
