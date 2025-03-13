package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.autos.*;
import frc.robot.commands.ClimberUpCommand;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.ClimberDownCommand;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private SendableChooser<Command> autoChooser;

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final ClimberSubsystem climber = new ClimberSubsystem();
    private CommandXboxController operatorButtons = new CommandXboxController(1);
   /*  private CommandXboxController povButtonCommandXboxController = new CommandXboxController(1);*/
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
            )
        );

        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));

        /* Operator Buttons */
        operatorButtons.leftBumper().onTrue(new ClimberUpCommand(climber));
        operatorButtons.rightBumper().onTrue(new ClimberDownCommand(climber));
/* 
        POVButton UpPov;
                        /*kaleb testing something
                        Object POVButton = UpPov = new POVButton(operatorButtons, 0); */
        /* Auto */
        autoChooser = new SendableChooser<Command>();
    autoChooser.addOption("Hard coded leave", new RunCommand(() -> s_Swerve.drive(new Translation2d(1.5, 0), 0.0, false, false), s_Swerve));

    try {
        // Get configuration set up in FRC Pathplanner
        RobotConfig driveConfig = RobotConfig.fromGUISettings();

        // Initialize/configure AutoBuilder with methods for controlling the swerve drive with Path Planner
        AutoBuilder.configure(
            s_Swerve::getPose, 
            s_Swerve::setPose, 
            s_Swerve::getRelativeSpeeds, 
            (speeds, feedfowards) -> s_Swerve.driveRobotRelative(speeds), 
            new PPHolonomicDriveController(
                new PIDConstants(.31,0,.1),
                new PIDConstants(1,0,0)
            ), 
            driveConfig, 
            () -> {
                // Allows us to alter path here; good for mirroring based on alliance color
                return false;
            },
            s_Swerve);

        // Set up the pathplanner autos
        autoChooser.addOption("Red 1",AutoBuilder.buildAuto("Red 1"));
        autoChooser.addOption("Red 2",AutoBuilder.buildAuto("Red 2"));
        autoChooser.addOption("Red 3",AutoBuilder.buildAuto("Red 3"));
        autoChooser.addOption("Blue 1",AutoBuilder.buildAuto("Blue 1"));
        autoChooser.addOption("Blue 2",AutoBuilder.buildAuto("Blue 2"));
        autoChooser.addOption("Blue 3",AutoBuilder.buildAuto("Blue 3"));
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    // autoChooser.setDefaultOption("Red 1",new PathPlannerAuto("Red 1"));
    // autoChooser.addOption("Red 2",new PathPlannerAuto("Red 2"));
    // autoChooser.addOption("Red 3",new PathPlannerAuto("Red 3"));
    // autoChooser.addOption("Blue 1",new PathPlannerAuto("Blue 1"));
    // autoChooser.addOption("Blue 2",new PathPlannerAuto("Blue 2"));

    // autoChooser.addOption("Blue 3",new PathPlannerAuto("Blue 3"));
    SmartDashboard.putData("Selected Auto", autoChooser);

    Shuffleboard.getTab("Match").add("Path Name", autoChooser);
}  

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
      }

      public void resetToAbsolute() {
        s_Swerve.resetModulesToAbsolute();
      }
    }
