// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class LimeVision extends SubsystemBase {
  
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry tx = table.getEntry("tx");
	NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");

  /** Creates a new LimeVision. */
  public LimeVision() {

    double xVisionVal = tx.getDouble(0.0);
		double yVisionVal = ty.getDouble(0.0);
		double areaVisionVal = ta.getDouble(0.0);
	
		SmartDashboard.putNumber("LimelightX", xVisionVal);
		SmartDashboard.putNumber("LimelightY", yVisionVal);
		SmartDashboard.putNumber("LimLightArea", areaVisionVal);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
