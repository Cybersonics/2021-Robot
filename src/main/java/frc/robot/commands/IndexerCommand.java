package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

/**
 * An example command that uses an example subsystem.
 */
public class IndexerCommand extends CommandBase {
  // Member Variables
  Indexer _indexer;
  DoubleSupplier _direction;

  // End Member Variables

  // Constructors

  public IndexerCommand(Indexer indexer) {
    _indexer = indexer;
    addRequirements(indexer);
  }

  public IndexerCommand(Indexer indexer, DoubleSupplier direction) {
    _indexer = indexer;
    _direction = direction;
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
  public void execute() {
    if (_direction != null) {
      _indexer.manualControl(_direction.getAsDouble());
    }
  }

  @Override
  public void end(boolean interrupted) {
    _indexer.stop();
  }

  // End Public Methods
}
