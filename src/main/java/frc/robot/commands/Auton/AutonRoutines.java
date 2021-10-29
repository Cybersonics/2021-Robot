package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.IndexerCommand;
import frc.robot.commands.PivotCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;

public class AutonRoutines {
    private Drive _drive;
    private Launcher _launcher;
    // private Vision _vision;
    private Intake _intake;
    private Indexer _indexer;


    public AutonRoutines(Drive drive,
                        Launcher launcher,
                        Intake intake,
                        Indexer indexer) {
        this._drive = drive;
        this._launcher = launcher;
        this._indexer = indexer;
        this._intake = intake;
    }

	public Command DoNothing() {
		return new DoNothingCommand();
	}

	public Command getRotateAndFire() {
		return new SequentialCommandGroup(        
            // new ZeroHeadingCommand(this._drive),
            new PivotCommand(this._launcher, Constants.AUTON_POSITION),
            new RotateCommand(this._drive, 45.0),
            new ParallelCommandGroup(
                new IndexerCommand(this._indexer, () -> { return 1.0; }),
                new ShooterCommand(this._launcher)
            ),
            new AutonDriveDistanceCommand(this._drive, 12.0)
        );
	}
}
