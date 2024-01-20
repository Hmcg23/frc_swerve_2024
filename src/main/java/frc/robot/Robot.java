/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Robot extends TimedRobot {
  // private DifferentialDrive m_myRobot;
  private final Timer m_timer = new Timer();
  /* drive motors */
  private static final int frontLeftDriveID = 2;
  private static final int frontRightDriveID = 8;
  private static final int backLeftDriveID = 4;
  private static final int backRightDriveID = 6;
  /* directional motors */
  private static final int frontLeftDirectionalId = 1;
  private static final int frontRightDirectionalId = 7;
  private static final int backLeftDirectionalId = 3;
  private static final int backRightDirectionalId = 5;


   
  private CANSparkMax m_frontLeftMotor;
  private CANSparkMax m_frontRightMotor;
  private CANSparkMax m_backLeftMotor;
  private CANSparkMax m_backRightMotor;

  private Joystick m_leftJoystick;
  private Joystick m_rightJoystick;

  @Override
  public void robotInit() {
  /**
   * SPARK MAX controllers are intialized over CAN by constructing a CANSparkMax object
   * 
   * The CAN ID, which can be configured using the SPARK MAX Client, is passed as the
   * first parameter
   * 
   * The motor type is passed as the second parameter. Motor type can either be:
   *  com.revrobotics.CANSparkLowLevel.MotorType.kBrushless
   *  com.revrobotics.CANSparkLowLevel.MotorType.kBrushed
   * 
   * The example below initializes four brushless motors with CAN IDs 1 and 2. Change
   * these parameters to match your setup
   */
    m_frontLeftMotor = new CANSparkMax(frontLeftDriveID, MotorType.kBrushless);
    m_frontRightMotor = new CANSparkMax(frontRightDriveID, MotorType.kBrushless);
    m_backLeftMotor = new CANSparkMax(backLeftDriveID, MotorType.kBrushless);
    m_backRightMotor = new CANSparkMax(backRightDriveID, MotorType.kBrushless);

    /**
     * The RestoreFactoryDefaults method can be used to reset the configuration parameters
     * in the SPARK MAX to their factory default state. If no argument is passed, these
     * parameters will not persist between power cycles
     */
    m_frontLeftMotor.restoreFactoryDefaults();
    m_frontRightMotor.restoreFactoryDefaults();
    m_backLeftMotor.restoreFactoryDefaults();
    m_backRightMotor.restoreFactoryDefaults();

    // m_myRobot = new DifferentialDrive(m_frontLeftMotor, m_frontRightMotor);

    m_leftJoystick = new Joystick(2);
    m_rightJoystick = new Joystick(8);

    m_backLeftMotor.follow(m_frontLeftMotor);
    m_backRightMotor.follow(m_frontRightMotor);
  }

  @Override
  public void autonomousInit() {
    m_timer.restart();
  }

  @Override
  public void autonomousPeriodic() {
    if (m_timer.get() < 10.0) {
      m_frontLeftMotor.set(0.5);
      m_frontRightMotor.set(0.5);
      m_backLeftMotor.set(0.5);
      m_backRightMotor.set(0.5);

    } else {
      m_frontLeftMotor.stopMotor();
      m_frontRightMotor.stopMotor();
      m_backLeftMotor.stopMotor();
      m_backRightMotor.stopMotor();
    }
  }

  @Override
  public void teleopPeriodic() {
    m_frontRightMotor.set(m_leftJoystick.getY());
    m_frontLeftMotor.set(m_rightJoystick.getY());
  }
}