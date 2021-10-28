/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Launcher extends SubsystemBase {


  private final double MAX_SHOOTER_RATE = 1.0;
  public double PivotRate = 0.5;
  public CANSparkMax _leftMotor;
  public CANSparkMax _rightMotor;
  private static Launcher instance;
  public static TalonSRX _pivotMotor;

  public double CurrentAngle;
  public double IncreaseOfAngle;
  
  private NetworkTableEntry pivotAngle;


  /**
   * Creates a new ExampleSubsystem.
   */
  private Launcher() {
    CommandScheduler.getInstance().registerSubsystem(this);
    setupRightMotor();
    setupLeftMotor();
    setupPivotMotor();
    
    ShuffleboardTab tab = Shuffleboard.getTab("Turret");
        pivotAngle = tab.add("Pivot Angle", 0.0)
                .withPosition(0, 0)
                .withSize(1, 1)
                .getEntry();
  }  
  
  // Public Methods
  public static Launcher getInstance()
  {
    if(instance==null)
    {
        instance = new Launcher();
    }
    return instance;
  }
  public void calculatedLaunch(double speed) {
    _rightMotor.set(speed);
    _leftMotor.set(speed);
  }

  public void start() {
    calculatedLaunch(MAX_SHOOTER_RATE);
    calculatedLaunch(MAX_SHOOTER_RATE);
  }

  public void stop() {
    calculatedLaunch(0);
    calculatedLaunch(0);
  }

  public void calculatedPivot(final double setPoint) {
    if (setPoint > -950 && setPoint < -650) {
      _pivotMotor.set(ControlMode.Position, setPoint);
    }
  }

  public void stopPivot() {
    _pivotMotor.set(ControlMode.PercentOutput, 0.0);
  }

  // End Public Methods

  private void setupRightMotor() {
    _rightMotor = new CANSparkMax(Constants.SHOOTER_SPARK_ONE, MotorType.kBrushless);
    _rightMotor.restoreFactoryDefaults();
  }

  private void setupLeftMotor() {
    _leftMotor = new CANSparkMax(Constants.SHOOTER_SPARK_TWO, MotorType.kBrushless);
    _leftMotor.restoreFactoryDefaults();
    _leftMotor.setInverted(true);
  }

  private void setupPivotMotor() {
    _pivotMotor = new TalonSRX(Constants.PIVOT_TALON);
    _pivotMotor.configFactoryDefault();
    _pivotMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
    _pivotMotor.setInverted(false);
    _pivotMotor.setSensorPhase(true);
    _pivotMotor.config_kP(0, 5, 0);
    _pivotMotor.config_kI(0, 0.04, 0);
    _pivotMotor.config_kD(0, 0, 0);
    _pivotMotor.config_IntegralZone(0, 100, 0);
    _pivotMotor.configAllowableClosedloopError(0, 5, 0);
    _pivotMotor.setNeutralMode(NeutralMode.Brake);
    _pivotMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 0);
  }

  public static double getPivotAngle() {
    return _pivotMotor.getSelectedSensorPosition();
  }

  @Override
    public void periodic() {
      pivotAngle.setDouble(getPivotAngle());
    }
}
