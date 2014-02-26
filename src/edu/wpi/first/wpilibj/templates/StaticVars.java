package edu.wpi.first.wpilibj.templates;

public class StaticVars {
    
    //motor number channels
    public static int FRONT_LEFT_MOTOR = 3;
    public static int REAR_LEFT_MOTOR = 1;
    public static int FRONT_RIGHT_MOTOR = 4;
    public static int REAR_RIGHT_MOTOR = 2;
    public static int BALL_LIFT_MOTOR = 5;
    
    //joystick constants
    public static int JOYSTICK_PORT = 1;
    public static double JOYSTICK_TWIST_DEADBAND = 0.6;
    public static double JOYSTICK_MAGNITUDE_DEADBAND = 0.25;
    
    //Joystick Axis Mapping
    public static int TWIST_AXIS = 3;
    public static int BALL_LOAD_AXIS = 6;
    
    //Joystick Button Mapping
    public static int MAG_LOCK_TRIGGER_BUTTON = 1;
    public static int RANGE_BUTTON = 4;
    public static int GYRO_RESET_BUTTON = 8;
    public static int RELOAD_BUTTON = 6;
    
    //Autonomous drive magnitude
    public static double AUTONOMOUS_DRIVE_MAGNITUDE = 0.5;
    
    //Autonomous drive timer
    public static double AUTONOMOUS_DRIVE_TIMER = 3;
    
    //Gyro channel
    public static int GYRO_CHANNEL = 2;
    
    //Rangefinder DigitalModule
    public static int RANGEFINDER_DIGITAL_MODULE = 1; //Can only be 1 or 2
    public static double RANGE_DELAY = 0.08;    //delay in s -> 80ms
    
    //Relay channels
    public static int MAG_LOCK_RELAY_CHANNEL = 2;
    public static int BALL_LOAD_RELAY_CHANNEL = 1;
    public static int RELOAD_RELAY_CHANNEL = 3;
    
    //DigitalInput channels
    public static int BALL_LOAD_DOWN_LIMIT_CHANNEL = 6;
    public static int BALL_LOAD_UP_LIMIT_CHANNEL = 7;
    
    //Timer
    public static double PUSH_TIME_LIMIT =2;
    public static double PULL_TIME_LIMIT =2;
    
}
