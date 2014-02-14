
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class can be used to optimally tune input values from a joystick.
 * And can be used to dead-band the joystick.
 * @author davidcox95
 */
public class CustomJoystick extends Joystick {

    private final JoystickButton rangeButton;
    private final JoystickButton gyroResetButton;
    private final JoystickButton magLockTriggerButton;
    
    public CustomJoystick() {
        super(StaticVars.JOYSTICK_PORT);
        //Necessary for Logitech 3D Joystick. Must specify 3rd axis as twist.
        setAxisChannel(Joystick.AxisType.kTwist, StaticVars.TWIST_AXIS);
        setAxisChannel(Joystick.AxisType.kNumAxis, StaticVars.BALL_LOAD_AXIS);
        
        rangeButton = new JoystickButton(this, StaticVars.RANGE_BUTTON);
        gyroResetButton = new JoystickButton(this, StaticVars.GYRO_RESET_BUTTON);
        magLockTriggerButton = new JoystickButton(this, StaticVars.MAG_LOCK_TRIGGER_BUTTON);
    }
    
    /**
     * Converts direction in rads to degrees.
     * It overrides superclass getDirectionDegrees()
     * which does not use PI, instead acos(-1) for C++ library.
     * @return double
     */
    public double getDirectionDegrees() {
        return super.getDirectionRadians()*180.0/Math.PI;
    }
    
    /**
     * Overrides superclass getMagnitude()
     * in order to give programmer easy
     * tuning abilities.
     * @return magnitude of vector
     */
    public double getMagnitude() {
        //Piecewise function which takes into account the deadband and
        //cooks the variables above deadband.
        return (Math.abs(super.getMagnitude()) < StaticVars.JOYSTICK_MAGNITUDE_DEADBAND) ? 0 :
                Math.abs((super.getMagnitude() - StaticVars.JOYSTICK_MAGNITUDE_DEADBAND)/
                         (1 - StaticVars.JOYSTICK_MAGNITUDE_DEADBAND));
    }
    
    /**
     * Overrides superclass getTwist()
     * in order to give programmer easy
     * tuning abilities.[-1,1]
     * @return double
     */
    public double getTwist() {
        //Piecewise function which takes into account the deadband and
        //cooks the variables outside deadband.
        if(Math.abs(super.getTwist()) < StaticVars.JOYSTICK_TWIST_DEADBAND)
            return 0;
        else{
            if(super.getTwist() >= StaticVars.JOYSTICK_TWIST_DEADBAND) {
                return (super.getTwist() - StaticVars.JOYSTICK_TWIST_DEADBAND)/
                       (1 - StaticVars.JOYSTICK_TWIST_DEADBAND);
            } else {
                return (super.getTwist() + StaticVars.JOYSTICK_TWIST_DEADBAND)/
                       (1 - StaticVars.JOYSTICK_TWIST_DEADBAND);
            }
        }
    }
    
    public boolean rangeButtonPressed() {
        return rangeButton.get();
    }
    
    public boolean gyroResetButtonPressed() {
        return gyroResetButton.get();
    }
    
    public boolean magLockTriggerButtonPressed() {
        return magLockTriggerButton.get();
    }
    
    public int getBallLoadValue() {
        return (int) getRawAxis(StaticVars.BALL_LOAD_AXIS);
    }

}
