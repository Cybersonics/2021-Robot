/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Auton;

import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Launcher;
import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
// import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * An example command that uses an example subsystem.
 */
public class ClimberCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  public static TalonSRX ClimberMotor;
  private Climber _climber;
  private boolean _isLocked = false;
  private Launcher _launcher;
  private double _pivotSetpoint;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ClimberCommand(Climber climber, Launcher launcher, boolean shouldExtend) {
    this._climber = climber;
    this._launcher = launcher;
    this._pivotSetpoint = (shouldExtend) ? Constants.LIFT_OPEN_POSITION : Constants.LIFT_LOCK_POSITION;
    addRequirements(launcher, climber);
  }  

  private boolean isLocked() {
    if(Math.abs(Launcher.getPivotAngle()) > Constants.LIFT_OPEN_POSITION) {
      this._isLocked = false;
    } else if(Math.abs(Launcher.getPivotAngle())<Constants.LIFT_LOCK_POSITION) {
      this._isLocked = true;
    }

    return this._isLocked;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (this._pivotSetpoint > -700) {
      System.out.println("[ClimberCommand] Ending command bad setpoint passed");
      end(true);
      }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("[ClimberCommand] TargetSetPoint: " + _pivotSetpoint + " Current Angle: " +  Launcher.getPivotAngle());
    if (Math.abs(this._pivotSetpoint - Launcher.getPivotAngle()) > 700) {
      this._launcher.calculatedPivot(this._pivotSetpoint);
    } else if (Math.abs(this._pivotSetpoint - Launcher.getPivotAngle()) < 950) {
      this._launcher.calculatedPivot(this._pivotSetpoint);
    } else if (!this.isLocked() && !this._climber.extended()) {
      this._climber.extend();
    } else if (this.isLocked() && !this._climber.retracted()) {
      this._climber.retract();
    } else {
      end(true);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      System.out.println("[PivotCommand] interrupted due to bad setpoint value");
    }
    this._launcher.stopPivot();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(this._pivotSetpoint - Launcher.getPivotAngle()) < 3;
  }
}
