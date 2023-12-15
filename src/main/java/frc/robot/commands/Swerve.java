// package frc.robot.commands;

// import java.util.function.Supplier;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Constants.InputConstants;
// import frc.robot.subsystems.SwerveSubsystem;

// public class Swerve extends CommandBase{

//     private final SwerveSubsystem swerveSubsystem;
//     private final Supplier<Double> xSpdFunction, ySpdFunction, turningSpdFunction;
//     private final Supplier<Boolean> fieldOrientedFunction;

//     public Swerve(SwerveSubsystem swerveSubsystem,
//             Supplier<Double> xSpdFunction, Supplier<Double> ySpdFunction, Supplier<Double> turningSpdFunction,
//             Supplier<Boolean> fieldOrientedFunction) {
//         this.swerveSubsystem = swerveSubsystem;
//         this.xSpdFunction = xSpdFunction;
//         this.ySpdFunction = ySpdFunction;
//         this.turningSpdFunction = turningSpdFunction;
//         this.fieldOrientedFunction = fieldOrientedFunction;
//         addRequirements(swerveSubsystem);
//     }

//     @Override
//     public void initialize() {
//     }

//     @Override 
//     public void execute() {

//         // 1. Get real-time joystick inputs
//         double xSpeed = xSpdFunction.get();
//         double ySpeed = ySpdFunction.get();
//         double turningSpeed = turningSpdFunction.get();

//         // 2. Apply deadband
//         xSpeed = Math.abs(xSpeed) > InputConstants.kDeadband ? xSpeed : 0.0;
//         ySpeed = Math.abs(ySpeed) > InputConstants.kDeadband ? ySpeed : 0.0;
//         turningSpeed = Math.abs(turningSpeed) > InputConstants.kDeadband ? turningSpeed : 0.0;
       
//     }

//     @Override 
//     public void end(boolean interrupted) {
//     }

//     @Override 
//     public boolean isFinished() {
//         return false;
//     }
    
// }
