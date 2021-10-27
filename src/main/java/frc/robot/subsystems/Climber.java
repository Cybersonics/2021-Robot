/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.*;
import frc.robot.subsystems.Launcher;
import frc.robot.Constants;
import com.revrobotics.CANError;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Servo;

public class Climber extends SubsystemBase {
    private CommandScheduler scheduler;
    public static TalonSRX ClimberTalon;
    public static Servo ClimberServo;
    private double ClimbSpeed;
    private static Climber _instance;

    /**
     * 
     */
    private Climber () {
        scheduler = CommandScheduler.getInstance();
        scheduler.registerSubsystem(this);

        ClimberTalon = new TalonSRX(Constants.CLIMBER_TALON);
        ClimberTalon.configFactoryDefault();

        ClimbSpeed = 0.6;
    }

    public static Climber getInstance() {
        if(_instance == null) {
            _instance = new Climber();
        }
        return _instance;
    }

    public void extend(){
        ClimberTalon.set(ControlMode.PercentOutput, 0.6);
    }

    public boolean extended() {
        return true;
    }

    public boolean retracted() {
        return true;
    }

    public void retract(){
        ClimberTalon.set(ControlMode.PercentOutput, -(0.6));
    }

    public void stop(){
        ClimberTalon.set(ControlMode.PercentOutput, 0.0);
    }
}