/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public final static int INTAKE_VICTOR = 5;
    public final static int INDEXER_VICTOR = 4;
    public final static int MECANUM_TALON = 7;
    public final static int SHOOTER_SPARK_ONE = 1;
    public final static int SHOOTER_SPARK_TWO = 2;
    public final static int PIVOT_TALON = 3;
    // NEED TO Find real value
    public final static int CLIMBER_TALON = 6;

    public static final int LEFT_JOYSTICK = 0;
    public static final int RIGHT_JOYSTICK = 1;
    public final static int XBOX_CONTROLLER = 2;
    
    public static final int FL_DRIVE_MOTOR = 10;
    public static final int FL_STEER_MOTOR = 20;
    public static final double FL_STEER_OFFSET = 0;
    public static final int FL_STEER_ENCODER = 0;

    public static final int FR_DRIVE_MOTOR = 13;
    public static final int FR_STEER_MOTOR = 23;
    public static final double FR_STEER_OFFSET = 0;
    public static final int FR_STEER_ENCODER = 3;

    public static final int BL_DRIVE_MOTOR = 11;
    public static final int BL_STEER_MOTOR = 21;
    public static final double BL_STEER_OFFSET = 0;
    public static final int BL_STEER_ENCODER = 1;

    public static final int BR_DRIVE_MOTOR = 12;
    public static final int BR_STEER_MOTOR = 22;
    public static final double BR_STEER_OFFSET = 0;
    public static final int BR_STEER_ENCODER = 2;

    public final static double TRENCH_POSITION = -865;
    public final static double AUTON_POSITION = -810;
    public final static double LIFT_OPEN_POSITION = -800;
    public final static double LIFT_LOCK_POSITION = -875;
 
    public final static double ROTATION_PER_INCH = 0.556;

    public final static double AutoRunTime = 5.0;
}
