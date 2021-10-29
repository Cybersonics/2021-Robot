package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;

/**
 * An example command that uses an example subsystem.
 */
public class IndexerCommand extends CommandBase {
  // Member Variables
  Indexer _indexer;
  DoubleSupplier _direction;
  Timer _timer;

  // End Member Variables

  // Constructors

  public IndexerCommand(Indexer indexer) {
    _indexer = indexer;
    _timer = new Timer();
    addRequirements(indexer);
  }

  public IndexerCommand(Indexer indexer, DoubleSupplier direction) {
    _indexer = indexer;
    _direction = direction;
    _timer = new Timer();
    addRequirements(indexer);
  }

  // End Constructors

  // Public Methods

public void forward() {
    _indexer.forward();
  }

  public void reverse() {
    _indexer.reverse();
  }

  public void stop() {
    _indexer.stop();
  }

  @Override
  public void initialize() {
    _timer = new Timer();
    _timer.start();
  }

  @Override
  public void execute() {
    if (_direction != null) {
      _indexer.manualControl(_direction.getAsDouble());
    }
  }

  @Override
  public void end(boolean interrupted) {
    _indexer.stop();
    _timer.stop();
  }

  @Override
  public boolean isFinished() {
    return _timer.hasElapsed(Constants.AutoRunTime);
  }

  // End Public Methods
}
