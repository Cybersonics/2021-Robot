package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
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

    public Command getRotateFireMove() {
        Command group; 

        group = new SequentialCommandGroup(
            new InstantCommand(() -> {
                this._drive.zeroNavHeading();
            }),
            new PivotCommand(this._launcher, Constants.AUTON_POSITION),
            new RotateCommand(-90),
            new IndexerCommand(this._indexer, () -> { return 1.0; }),
            new ShooterCommand(this._launcher),
            new AutonDriveDistanceCommand(12.0)
        );

        return group;
    }
}
