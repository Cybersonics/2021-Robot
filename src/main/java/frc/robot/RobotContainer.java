/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.FieldCentricSwerveDrive;
import frc.robot.commands.IndexerCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.Auton.AutonDriveDistanceCommand;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;


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

  public static Drive driveSub = Drive.getInstance();
  public static Indexer _indexer = new Indexer();
  public static Intake _intake = new Intake();
  public static Launcher _launcher = new Launcher();
  
  private final AutonDriveDistanceCommand m_autoCommand = new AutonDriveDistanceCommand(12.0);
  private final IntakeCommand _intakeCommand = new IntakeCommand(_intake);
  private final IndexerCommand _indexerCommand = new IndexerCommand(_indexer);
  private final ShooterCommand _shooterCommand = new ShooterCommand(_launcher);

  public static Joystick leftJoy;
  public static Joystick rightJoy;
  public XboxController xboxController;
  public XboxController driveController;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    leftJoy = new Joystick(Constants.LEFT_JOYSTICK);
    rightJoy = new Joystick(Constants.RIGHT_JOYSTICK);
    // xboxController = new XboxController(Constants.XBOX_CONTROLLER);
    driveController = new XboxController(Constants.XBOX_CONTROLLER);

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
    CommandScheduler.getInstance()
    .setDefaultCommand(
      driveSub,
      new FieldCentricSwerveDrive(
        driveSub,
        () -> driveController.getY(Hand.kLeft) * 0.8,
        () -> driveController.getX(Hand.kLeft) * 0.8,
        () -> driveController.getX(Hand.kRight) * 0.8,
        driveController.getAButton()
      )
    );
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  //new JoystickButton(leftJoy, 7).whenPressed(() -> Navx.getInstance().getFuzedHeading());
    // new JoystickButton(leftJoy, 7).whenPressed(() -> driveSub.zeroNavHeading());     
        // _indexer.setDefaultCommand(new IndexerCommand(
        //   _indexer,
        //   () -> xboxController.getY(Hand.kRight)
        // ));
    
        // _intake.setDefaultCommand(new IntakeCommand(
        //   _intake, 
        //   () -> xboxController.getY(Hand.kLeft)
        // ));

        // new JoystickButton(xboxController, 1).whenPressed(new PivotCommand(_launcher, Constants.TRENCH_POSITION ));
        // new JoystickButton(xboxController, 4).whenPressed(new PivotCommand(_launcher, Constants.AUTON_POSITION));
        // new JoystickButton(xboxController, 3).whenPressed(new PivotCommand(_launcher, Constants.LIFT_LOCK_POSITION));
        // new JoystickButton(xboxController, 2).whenPressed(new PivotCommand(_launcher, Constants.LIFT_OPEN_POSITION));

	    	// new JoystickButton(xboxController, 6).whenPressed(() -> _shooterCommand.fire());
        // new JoystickButton(xboxController, 6).whenReleased(() -> _shooterCommand.stop());
    }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public AutonDriveDistanceCommand getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

}
