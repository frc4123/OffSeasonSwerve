// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final class DrivingConstants{

    public static final int Front_Left_Drive = 2;
    public static final int Front_Right_Drive = 3;
    public static final int Back_Right_Drive = 4;
    public static final int Back_Left_Drive = 5;
    // drive motors - order -  start top left in clockwise rotation
    
    public static final int Front_Left_Turn = 6;
    public static final int Front_Right_Turn = 7;
    public static final int Back_Right_Turn = 8;
    public static final int Back_Left_Turn = 9;
    // turn motors - order - start top left in clockwise rotation

  }

  public static final class ModuleConstants {

    public static final int kEncoderCPR = 1024;
    public static final double kWheelDiameterMeters = Units.inchesToMeters(4); //wheel diameter
    public static final double kDriveMotorGearRatio = 1 / 5.8462; // motor gear ratio
    public static final double TurningMotorGearRatio = 1 / 18.0; // turning gear ratio
    public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters;
    public static final double kTurningEncoderRot2Rad = TurningMotorGearRatio * 2 * Math.PI;
    public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60;
    public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60;
    public static final double kPTurning = 0.5;
    // the above will have to be changed after gear ratio measurements, wheel measurements, and pid tuning
    
  }

  public static final class PhysicalConstants {
    
  }

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

}
