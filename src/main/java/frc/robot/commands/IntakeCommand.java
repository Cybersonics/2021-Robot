package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

/**
 * An example command that uses an example subsystem.
 */
public class IntakeCommand extends CommandBase {

  // Member Variables
  Intake _intake;
  DoubleSupplier _direction;
  // End Member Variables

  // Constrcutors
  public IntakeCommand(Intake intake) {
    _intake = intake;

    addRequirements(intake);
  }
  
  public IntakeCommand(Intake intake, DoubleSupplier direction) {
    _intake = intake;
    _direction = direction;

    addRequirements(intake);
  }

  // End Constructors

  // Public Methods

  public void forward() {
    _intake.forward();
  }

  public void reverse() {
    _intake.reverse();
  }

  public void stop() {
    _intake.stop();
  }

  @Override
  public void execute() {
    if (_direction != null) {
      _intake.manualControl(_direction.getAsDouble());
    }
  }

  @Override
  public void end(boolean interrupted) {
    _intake.stop();
  }

  // End Public Methods
}