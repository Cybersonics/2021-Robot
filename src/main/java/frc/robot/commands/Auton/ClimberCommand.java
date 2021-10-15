// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.commands;

// import frc.robot.subsystems.Climber;

// import frc.robot.Robot;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj2.command.Subsystem;
// import frc.robot.Constants;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;
// import com.ctre.phoenix.motorcontrol.ControlMode;
// // import edu.wpi.first.wpilibj.command.Command;
// //import edu.wpi.first.wpilibj.GenericHID.Hand;

// /**
//  * An example command that uses an example subsystem.
//  */
// public class ClimberCommand extends CommandBase {
//   @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
//   public static TalonSRX ClimberMotor;
//   Climber climber;
//   private static boolean isLocked;

//   /**
//    * Creates a new ExampleCommand.
//    *
//    * @param subsystem The subsystem used by this command.
//    */
//   public ClimberCommand() {
//     // Creating new climber object
//     ClimberMotor = new TalonSRX(Constants.CLIMBER);
//   }

//   public void extend(){
//     ClimberMotor.set(ControlMode.PercentOutput, 0.5);
//   }

//   public void retract(){
//     ClimberMotor.set(ControlMode.PercentOutput, -(0.5));
//   }

//   public void stop(){
//     ClimberMotor.set(ControlMode.PercentOutput, 0);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//   }



//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {

//    /* // Intake Runs In
//     if(Robot.xBoxController.getPOV() == 0) 
//     {
//       climber.extend();
//     } 
//     // Intake Goes Backward
//     else if(Robot.xBoxController.getPOV() == 180) 
//     {
//       climber.retract();
//     }
//     // Intake Shuts off
//     else if (Robot.xBoxController.getPOV() == 270) 
//     {
//       climber.stop();
//     } 
//     if (Robot.xBoxController.getRawButton(4)){
//       if (isLocked){
//         climber.release();
//       }
//       else{
//         climber.lock();
//       }
//     }*/
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }
