package frc.robot.subsystems;

import frc.robot.Constants.ModuleConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;



public class SwerveModule {

    private final CANSparkMax driveMotor;
    private final CANSparkMax turnMotor;

    private final RelativeEncoder driveEncoder;
    private final RelativeEncoder turnEncoder;

    private final PIDController turnPIDController;
    // PID controller built into motor, adjusts turn 

    private final AnalogInput absoluteEncoder; 
    private final boolean isAbsoluteEncoderReversed;
    private final double absoluteEncoderOffsetRad;
    // absoluteEncoder will determinte where to turn wheels to face straight upon initialization, measured in Radians

    public SwerveModule(int driveMotorId, int turnMotorId, boolean isDriveMotorReversed, 
    boolean isTurnMotorReversed, int absoluteEncoderId, double absoluteEncoderOffset, boolean isAbsoluteEncoderReversed) {

        this.absoluteEncoderOffsetRad = absoluteEncoderOffset;
        this.isAbsoluteEncoderReversed = isAbsoluteEncoderReversed;
        absoluteEncoder = new AnalogInput(absoluteEncoderId);
        // absolute encoder

        driveMotor = new CANSparkMax(driveMotorId, MotorType.kBrushless);
        turnMotor = new CANSparkMax(turnMotorId, MotorType.kBrushless);
        // the module's motors

        driveMotor.setInverted(isDriveMotorReversed);
        turnMotor.setInverted(isTurnMotorReversed);
        // reversed?

        driveEncoder = driveMotor.getEncoder();
        turnEncoder = turnMotor.getEncoder();
        // neo encoders

        driveEncoder.setPositionConversionFactor(ModuleConstants.kDriveEncoderRot2Meter);
        driveEncoder.setVelocityConversionFactor(ModuleConstants.kDriveEncoderRPM2MeterPerSec);
        turnEncoder.setPositionConversionFactor(ModuleConstants.kTurningEncoderRot2Rad) ;
        turnEncoder.setVelocityConversionFactor(ModuleConstants.kTurningEncoderRPM2RadPerSec);
        // converts encoders to work with radians

        turnPIDController = new PIDController(ModuleConstants.kPTurning,0,0);
        turnPIDController.enableContinuousInput(Math.PI, Math.PI);
        // PID controller -  continuous readings

        resetEncoders();
    }

    public double getDrivePosition() {
        return driveEncoder.getPosition();
    }

    public double getTurningPosition() {
        return turnEncoder.getPosition();
    }

    public double getDriveVelocity() {
        return driveEncoder.getVelocity();
    }

    public double getTurningVelocity(){
        return turnEncoder.getVelocity();
    }
    //methods for obtaining encoder values


    public double getAbsoluteEncoderRad() {
        double angle = absoluteEncoder.getVoltage() / RobotController.getVoltage5V();
        angle *= 2.0 * Math.PI;
        angle -= absoluteEncoderOffsetRad;
        return angle * (isAbsoluteEncoderReversed ? -1.0 : 1.0);
    }
    // determites offset of wheels with absolute encoder

    public void resetEncoders() {
        driveEncoder.setPosition(0);
        turnEncoder.setPosition(getAbsoluteEncoderRad());
    }
    // resets encoders to zero state - turn encoder alligns itself with wheel angle

    public SwerveModuleState getState() {
        return new SwerveModuleState(getDriveVelocity(), new Rotation2d(getTurningPosition()));
    }
}
