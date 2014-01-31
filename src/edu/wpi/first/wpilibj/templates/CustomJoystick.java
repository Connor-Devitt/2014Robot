
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;


/**
 * This class can be used to optimally tune input values from a joystick.
 * And can be used to dead-band the joystick.
 * @author davidcox95
 */
public class CustomJoystick extends Joystick {

    public CustomJoystick(int port) {
        super(port);
        
        //Necessary for Logitech 3D Joystick. Must specify 3rd axis as twist.
        setAxisChannel(Joystick.AxisType.kTwist, 3);
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
        //Might not be needed because Joystick class compensates
        //for deadband elimination enableDeadbandElimination(true) ->default
        //if (Math.abs(super.getMagnitude()) < 0.25)
        //    return 0;
        //return Math.sqrt(Math.abs(super.getMagnitude()));   //cook the magnitude
        //return Math.abs((4.0/3.0)*super.getMagnitude() - 1.0/3.0);
        
        //We could turn it into 1 statement using ternary operator
        return (Math.abs(super.getMagnitude()) < StaticVars.JOYSTICK_MAGNITUDE_DEADBAND) ? 0 :
                Math.abs((4.0/3.0)*super.getMagnitude() - 1.0/3.0);
    }
    /**
     * Overrides superclass getTwist()
     * in order to give programmer easy
     * tuning abilities.[-1,1]
     * @return double
     */
    public double getTwist() {
        if(Math.abs(super.getTwist())< StaticVars.JOYSTICK_TWIST_DEADBAND)
            return 0;
        else{
            if(super.getTwist() >= .6)
                return 2.5*super.getTwist() - 1.5;
            else
                return 2.5*super.getTwist() + 1.5;
        }
        //Might not be needed because Joystick class compensates
        //for deadband elimination enableDeadbandElimination(true) ->default
        //if (Math.abs(super.getTwist()) < 0.6)
        //    return 0;
        //return super.getTwist();
        //return 2.5*super.getTwist() - 1.5;
        
        //We can turn it into one statment using ternary operator
        //return (Math.abs(super.getTwist()) < StaticVars.JOYSTICK_TWIST_DEADBAND) ? 0 :
        //        2.5*super.getTwist() - 1.5: 2.5*super.getTwist() + 1.5;
    }

}
