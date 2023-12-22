package frc.robot;

import frc.robot.Constants.InputConstants;
import frc.robot.Constants.DrivingConstants;
//import frc.robot.Constants.ModuleConstants;
import frc.robot.commands.Swerve;
import frc.robot.subsystems.SwerveSubsystem;
// import frc.robot.Constants.OperatorConstants;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
//import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {

  private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();

  private final CommandXboxController m_driverController = new CommandXboxController(InputConstants.kDriverControllerPort);

  public RobotContainer() {

    swerveSubsystem.setDefaultCommand(new Swerve(
                swerveSubsystem,
                () -> -m_driverController.getLeftY() * DrivingConstants.kTeleDriveMaxAccelerationUnitsPerSecond,
                () -> m_driverController.getLeftX()  * DrivingConstants.kTeleDriveMaxAccelerationUnitsPerSecond,
                () -> m_driverController.getRightX() *  DrivingConstants.kTeleDriveMaxAngularAccelerationUnitsPerSecond
                ));
    configureBindings();
  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
