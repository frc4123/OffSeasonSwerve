package frc.robot.subsystems;

import frc.robot.Constants.DrivingConstants;
import frc.robot.Constants.ModuleConstants;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;

import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.math.geometry.Pose2d;
// import edu.wpi.first.math.geometry.Translation2d;

public class SwerveSubsystem extends SubsystemBase{

    private final SwerveModule frontLeft = new SwerveModule(
            DrivingConstants.Front_Left_Drive,
            DrivingConstants.Front_Left_Turn,
            DrivingConstants.Front_Left_Drive_Encoder_Reversed,
            DrivingConstants.Front_Left_Turning_Encoder_Reversed,
            DrivingConstants.Front_Left_Drive_Absolute_Encoder_Port,
            DrivingConstants.Front_Left_Drive_Absolute_Encoder_Offset_Rad,
            DrivingConstants.Front_Left_Drive_Absolute_Encoder_Reversed);

    private final SwerveModule frontRight = new SwerveModule(
            DrivingConstants.Front_Right_Drive,
            DrivingConstants.Front_Right_Turn,
            DrivingConstants.Front_Right_Drive_Encoder_Reversed,
            DrivingConstants.Front_Right_Turning_Encoder_Reversed,
            DrivingConstants.Front_Right_Drive_Absolute_Encoder_Port,
            DrivingConstants.Front_Right_Drive_Absolute_Encoder_Offset_Rad,
            DrivingConstants.Front_Right_Drive_Absolute_Encoder_Reversed);

    private final SwerveModule backLeft = new SwerveModule(
            DrivingConstants.Back_Left_Drive,
            DrivingConstants.Back_Left_Turn,
            DrivingConstants.Back_Left_Drive_Encoder_Reversed,
            DrivingConstants.Back_Left_Turning_Encoder_Reversed,
            DrivingConstants.Back_Left_Drive_Absolute_Encoder_Port,
            DrivingConstants.Back_Left_Drive_Absolute_Encoder_Offset_Rad,
            DrivingConstants.Back_Left_Drive_Absolute_Encoder_Reversed);

    private final SwerveModule backRight = new SwerveModule(
            DrivingConstants.Back_Right_Drive,
            DrivingConstants.Back_Right_Turn,
            DrivingConstants.Back_Right_Drive_Encoder_Reversed,
            DrivingConstants.Back_Right_Turning_Encoder_Reversed,
            DrivingConstants.Back_Right_Drive_Absolute_Encoder_Port,
            DrivingConstants.Back_Right_Drive_Absolute_Encoder_Offset_Rad,
            DrivingConstants.Back_Right_Drive_Absolute_Encoder_Reversed);

    private final AHRS gyro = new AHRS(SPI.Port.kMXP);

        public SwerveSubsystem() {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    zeroHeading();
                } catch (Exception e) {}
            }).start();
        }
        // creates new thread to reset gyro

    public void zeroHeading() {
        gyro.reset();
    }

    public double getHeading() {
        return Math.IEEEremainder(gyro.getAngle(), 360);
    }
    // keeps degree within 360 degree measurements

    public Rotation2d getRotation2d() {
        return Rotation2d.fromDegrees(getHeading());
    }
    // wpilib 

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Robot Heading", getHeading());
    }

    public void stopModules() {
        frontLeft.stop();
        frontRight.stop();
        backLeft.stop();
        backRight.stop();
    }

    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, ModuleConstants.kPhysicalMaxSpeedMetersPerSecond);
        frontLeft.setDesiredState(desiredStates[0]);
        frontRight.setDesiredState(desiredStates[1]);
        backLeft.setDesiredState(desiredStates[2]);
        backRight.setDesiredState(desiredStates[3]);
    }
    //sets wheel speeds at same speed (desaturate)
}
