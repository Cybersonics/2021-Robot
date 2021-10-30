/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.FieldCentricSwerveDrive;
import frc.robot.commands.IndexerCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.PivotCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.Auton.AutonRoutines;
import frc.robot.commands.Auton.ClimberCommand;
import frc.robot.commands.Auton.ZeroHeadingCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.NavXGyro;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  /*
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  */

  public static Drive _drive = Drive.getInstance();
  public static Indexer _indexer = new Indexer();
  public static Intake _intake = new Intake();
  public static Launcher _launcher = Launcher.getInstance();
  public static Climber _climber = Climber.getInstance();
  public static NavXGyro _navXGyro = new NavXGyro();

  public double speedMultiplier = 0.45;

  
  private final AutonRoutines _autonRoutines = new AutonRoutines(_drive, _launcher, _intake, _indexer, _navXGyro);
  private final IntakeCommand _intakeCommand = new IntakeCommand(_intake);
  private final IndexerCommand _indexerCommand = new IndexerCommand(_indexer);
  private final ShooterCommand _shooterCommand = new ShooterCommand(_launcher);

  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  public static Joystick leftJoy;
  public static Joystick rightJoy;
  public XboxController xboxController;
  public XboxController driveController;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {



    // Set up auton selector
    m_chooser.setDefaultOption("Do Nothing", _autonRoutines.DoNothing());
    m_chooser.addOption("Rotate and Fire", _autonRoutines.getRotateAndFire());

    // Put the chooser on the dashboard
    SmartDashboard.putData(m_chooser);

    // Configure the button bindings
    leftJoy = new Joystick(Constants.LEFT_JOYSTICK);
    rightJoy = new Joystick(Constants.RIGHT_JOYSTICK);
    xboxController = new XboxController(Constants.XBOX_CONTROLLER);

    // This method passes in the values of the controller to the command
    // CommandScheduler.getInstance()
    // .setDefaultCommand(
    //   driveSub,
    //   new FieldCentricSwerveDrive(
    //     driveSub,
    //     () -> leftJoy.getY(),
    //     () -> leftJoy.getX(),
    //     () -> rightJoy.getX(),
    //     leftJoy.getTrigger()
    //   )
    // );

    // This method passes the controllers themselves in so the command can get values
    CommandScheduler.getInstance()
    .setDefaultCommand(_drive, 
      new FieldCentricSwerveDrive(_drive, leftJoy, rightJoy, _navXGyro)
    );

    // This method uses the xbox controller as the drive controller (xbox controller must be disabled for opp controls)  
    /// driveController = new XboxController(Constants.XBOX_CONTROLLER);
    // CommandScheduler.getInstance()
    // .setDefaultCommand(
    //   driveSub,
    //   new FieldCentricSwerveDrive(
    //     driveSub,
    //     () -> driveController.getY(Hand.kLeft) * speedMultiplier,
    //     () -> driveController.getX(Hand.kLeft) * speedMultiplier,
    //     () -> driveController.getX(Hand.kRight) * speedMultiplier,
    //     driveController.getAButton()
    //   )
    // );
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(leftJoy, 7).whenPressed(new ZeroHeadingCommand(_drive, _navXGyro));

        _indexer.setDefaultCommand(new IndexerCommand(
          _indexer,
          () -> xboxController.getY(Hand.kRight)
        ));
    
        _intake.setDefaultCommand(new IntakeCommand(
          _intake, 
          () -> xboxController.getY(Hand.kLeft)
        ));

        // Set A button to raise launcher and tower
        new JoystickButton(xboxController, 1).whenPressed(new ClimberCommand(_climber, _launcher, true));
        // Set Y button to lower launcher and tower
        new JoystickButton(xboxController, 4).whenPressed(new ClimberCommand(_climber, _launcher, false));
        // Set X Button to reset launcher back to teleop position
        new JoystickButton(xboxController, 2).whenPressed(new PivotCommand(_launcher, Constants.AUTON_POSITION));

        // While R1 is held launcher will spin up, when released it it slow to stop
	    	new JoystickButton(xboxController, 6).whenPressed(() -> _shooterCommand.fire());
        new JoystickButton(xboxController, 6).whenReleased(() -> _shooterCommand.stop());
    }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // This runs the Auton picked from smart dashboard
    return m_chooser.getSelected();
  }

}
