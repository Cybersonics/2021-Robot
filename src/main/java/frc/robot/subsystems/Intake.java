/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020-2021 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Intake extends SubsystemBase {
  public VictorSPX _intakeMotor;
  public final double MAX_INTAKE_SPEED = .50;

  public Intake () {
    CommandScheduler.getInstance().registerSubsystem(this);
    
    _intakeMotor = new VictorSPX(Constants.INTAKE_VICTOR);
    _intakeMotor.configFactoryDefault();
  }

  public void manualControl(double speed) {
    _intakeMotor.set(ControlMode.PercentOutput, speed * MAX_INTAKE_SPEED);
  }

  public void forward() {
    this.manualControl(MAX_INTAKE_SPEED);
  }

  public void reverse() {
    this.manualControl(-MAX_INTAKE_SPEED);
  }

  public void stop() {
    this.manualControl(0);
  }
}

