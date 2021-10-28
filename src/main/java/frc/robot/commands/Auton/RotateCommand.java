package frc.robot.commands.Auton;

import java.io.Console;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;

public class RotateCommand extends CommandBase {

    private Drive _drive;
    private double _degrees;
    private Timer _timer;

    /**
     * Creates a new Drive.
     */
    public RotateCommand(double angle) {
      this._drive = Drive.getInstance();

      this._degrees = this._drive.getNavAngle() + angle;
      
      this._drive.setDriveEncodersPosition(0);
      // System.out.println("Distance To Travel: " + this._distance);
		  addRequirements(this._drive);

    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      this._timer = new Timer();
      _timer.start();
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      // double currentRotation = this._drive.getDriveEncoderAvg();
      // _distance = currentRotation + this._distance;
      this._drive.swerveDrive(0.0, 0.5, 0.5, false);
      // System.out.println("Curent: " + currentRotation);
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      _timer.stop();
      this._drive.swerveDrive(0.0, 0.0, 0.0, false);

    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      if(this._degrees >= this._drive.getNavAngle()) {
        return true;
      }
      return false;
    }
  }
  