package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants;
import frc.robot.subsystems.Launcher;

/**
 * An example command that uses an example subsystem.
 */
public class ShooterCommand extends CommandBase {
  //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  public double ShooterSpeed = 0.9;
  public double Pivot = 0.9;

  private Launcher _shooter;
  private Timer _timer;

  

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
    _timer = new Timer();
    _timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    fire();
  }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    stop();
    this._timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this._timer.hasElapsed(Constants.AutoRunTime);
  }
}

