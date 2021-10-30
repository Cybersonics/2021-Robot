package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.NavXGyro;

public class ZeroHeadingCommand extends CommandBase {

    private Drive _drive;
    private NavXGyro _navXGyro;

    /**
     * Creates a new Drive.
     */
    public ZeroHeadingCommand(Drive drive, NavXGyro navXGyro) {
      this._drive = drive;
      this._navXGyro = navXGyro;
	  addRequirements(this._drive);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      
      System.out.println("[ZeroHeadingCommand#exe] Reset Nav!");
            this._navXGyro.zeroNavHeading();
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {      
      System.out.println("[ZeroHeadingCommand#fin] Heading: " + this._navXGyro.getNavAngle());
        return this._navXGyro.getNavAngle() == 0.0;
    }
  }
  