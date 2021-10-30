// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.NavXGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.*;


/**
 * Field Centric command that can operate as Robot centric when left 
 * joystick trigger is pulled.
 */
public class FieldCentricSwerveDrive extends CommandBase {

	public static final double OMEGA_SCALE = 1.0 / 45.0;//30
	public static final double DEADZONE_LSTICK = 0.06;
	private static final double DEADZONE_RSTICK = 0.07;
	private double originHeading = 0.0;
	private double originCorr;
	private double leftPow = 2;
	private double rightPow = 2;

	private Drive drive;
	private NavXGyro _navXGyro;
	private final DoubleSupplier stickForward;
	private final DoubleSupplier stickStrafe;
	private final DoubleSupplier stickRotation;
	private final boolean stickFieldCentric;
	
	//private static FieldCentricSwerveDrive instance;
	public FieldCentricSwerveDrive(Drive driveSub,
									DoubleSupplier forward,
									DoubleSupplier strafe,
									DoubleSupplier rotation,
									boolean fieldCentric) {
		
		this.stickForward = forward;
		this.stickStrafe = strafe;
		this.stickRotation = rotation;
		this.stickFieldCentric = fieldCentric;

		drive = driveSub;
		addRequirements(drive);
	}

	
	public FieldCentricSwerveDrive(Drive driveSub, 
									Joystick leftJoystick, 
									Joystick rightJoystick,
									NavXGyro navXGyro) {
		this.stickForward = () -> leftJoystick.getY();
		this.stickStrafe = () -> leftJoystick.getX();
		this.stickRotation = () -> rightJoystick.getX();
		this.stickFieldCentric = leftJoystick.getTrigger();
		this._navXGyro = navXGyro;

		
		drive = driveSub;
		addRequirements(drive);
	}

	// public static FieldCentricSwerveDrive getInstance() {
	// 	if(instance == null) {
	// 		instance = new FieldCentricSwerveDrive();
	// 	}
	// 	return instance;
	// }
	// Called when the command is initially scheduled.

	@Override
	public void initialize() {

		originHeading = _navXGyro.getZeroAngle();

		//originHeading = _navXGyro.getNavHeading();
	}

    @Override
	public void execute() {

		SmartDashboard.putBoolean("LeftTrigger", stickFieldCentric);
		final double originOffset = 360 - originHeading;
		originCorr = _navXGyro.getNavAngle() + originOffset;
		//originCorr = _navXGyro.getNavHeading() + originOffset;
		
		//double forward = stickForward.getAsDouble();
		//double strafe = -stickStrafe.getAsDouble();
		//double omega = -stickRotation.getAsDouble() * OMEGA_SCALE;

		double strafe = Math.pow(Math.abs(stickStrafe.getAsDouble()), leftPow) * Math.signum(-stickStrafe.getAsDouble());
		double forward = Math.pow(Math.abs(stickForward.getAsDouble()), leftPow) * Math.signum(stickForward.getAsDouble());
        double omega = Math.pow(Math.abs(stickRotation.getAsDouble()), rightPow) * Math.signum(-stickRotation.getAsDouble()) * OMEGA_SCALE;

		SmartDashboard.putNumber("Forward", forward);
		SmartDashboard.putNumber("Strafe", strafe);
		SmartDashboard.putNumber("Rotation", omega);

		// Add a small deadzone on the joysticks
		if (Math.abs(strafe) < DEADZONE_LSTICK)
			strafe = 0.0;
		if (Math.abs(forward) <DEADZONE_LSTICK)
			forward = 0.0;
		if (Math.abs(omega) < DEADZONE_RSTICK * OMEGA_SCALE)
			omega = 0.0;

		if (!stickFieldCentric) {
			// When the Left Joystick trigger is not pressed, The robot is in Field Centric
			// Mode.
			// The calculations correct the forward and strafe values for field centric
			// attitude.

			// Rotate the velocity vector from the joystick by the difference between our
			// current orientation and the current origin heading
			// final double originCorrection = Math.toRadians(originHeading - Navx.getInstance().navX.getFusedHeading());
			// final double temp = forward * Math.cos(originCorrection) - strafe * Math.sin(originCorrection);
			final double originCorrection = Math.toRadians(originHeading - _navXGyro.getNavAngle());
			//final double originCorrection = Math.toRadians(originHeading - _navXGyro.getNavHeading());
			final double temp = forward * Math.cos(originCorrection) - strafe * Math.sin(originCorrection);
			strafe = strafe * Math.cos(originCorrection) + forward * Math.sin(originCorrection);
			forward = temp;
        }

		// If all of the joysticks are in the deadzone, don't update the motors
		// This makes side-to-side strafing much smoother
		boolean deadStick = false;
		if (strafe == 0.0 && forward == 0.0 && omega == 0.0) {
			deadStick = true;
		}

		SmartDashboard.putNumber("Forward Done", forward);
		SmartDashboard.putNumber("Strafe Done", strafe);
		SmartDashboard.putNumber("Rotation Done", omega);


		drive.swerveDrive(strafe, forward, omega, deadStick);
        
	}
}

