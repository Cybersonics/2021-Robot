/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020-2021 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  private VictorSPX _indexMotor;
  private TalonFX _mecanumMotor;
  public final double MAX_INDEXER_SPEED = 1.0; 

  public Indexer () {
    CommandScheduler.getInstance().registerSubsystem(this);

    _indexMotor = new VictorSPX(Constants.INDEXER_VICTOR);
    _mecanumMotor = new TalonFX(Constants.MECANUM_TALON);
    _indexMotor.configFactoryDefault();
  }

  public void manualControl(double speed) {
    _indexMotor.set(ControlMode.PercentOutput,speed);
    _mecanumMotor.set(ControlMode.PercentOutput, speed);
  }

  public void forward(){
    this.manualControl(MAX_INDEXER_SPEED);
  } 
  
  public void reverse(){
    this.manualControl(-MAX_INDEXER_SPEED);
  } 

  public void stop(){
    this.manualControl(0);
  }    
 
}
