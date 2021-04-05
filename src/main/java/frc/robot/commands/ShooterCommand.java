package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Launcher;

/**
 * An example command that uses an example subsystem.
 */
public class ShooterCommand extends CommandBase {
  //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  public double ShooterSpeed = 0.9;
  public double Pivot = 0.9;

  Launcher _shooter;

  

  /**
   * constructor method
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterCommand(Launcher shooter) {
    _shooter = shooter;

    CommandScheduler.getInstance().requiring(shooter);
    
  }

  public void fire() {
      _shooter.start();
  }

  public void stop() {
      _shooter.stop();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // a runs shooter
//     if (Robot.xBoxController.getAButton()) 
//     {
//       ShooterSpeed = 0.9;
//       ShooterOne.SetShooterRate(ShooterSpeed);
//       ShooterOne.ShooterRun();
//     }
//     // b is pivot down
//     else if (Robot.xBoxController.getBButton()) 
//     {
//       ShooterOne.PivotUp();
//     }
//     // x is to Pivot Down
//     else if (Robot.xBoxController.getXButton()) 
//     {
//       ShooterOne.PivotDown();
//     }

//     // Automatically Locks Pivot
//     ShooterOne.PivotShutDown();

//     if (!Robot.xBoxController.getAButton()){ //epic gamming moment!
//       ShooterSpeed = 0; // f's in the chat for the shooter speed
//       ShooterOne.SetShooterRate(ShooterSpeed); // begone ShooterSpeed
//       ShooterOne.ShooterRun(); // wow u r such noob Shooterspeed uwu
//     }


//   //Curreently the Pivot is Manual, but later it can be done with a sensor
  }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

