package frc.robot.commands.Auton;

import java.io.Console;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;

public class AutonDriveDistanceCommand extends CommandBase {

    private Drive _drive;
    private double _distance;
    private Timer _timer;

    /**
     * Creates a new Drive.
     */
    public AutonDriveDistanceCommand(Drive drive, double distance) {
      this._drive = drive;

      this._distance = distance * Constants.ROTATION_PER_INCH;
      
      this._drive.setDriveEncodersPosition(0);
      // System.out.println("Distance To Travel: " + this._distance);
		  addRequirements(this._drive);

    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      this._timer = new Timer();
      _timer.start();

      this._drive.setDriveEncodersPosition(0);
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      // double currentRotation = this._drive.getDriveEncoderAvg();
      // _distance = currentRotation + this._distance;
      //The strafe value is used as pushing the value directly into the drive system 
      //operates the bot in Robot Centric form.
      this._drive.swerveDrive(0.4, 0.0, 0.0, false);
      // System.out.println("Curent: " + currentRotation);
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      _timer.stop();
      this._drive.setDriveEncodersPosition(0);
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      if(this._distance <= Math.abs(this._drive.getDriveEncoderAvg())) {
        return true;
      }
      return false;
    }
  }
  