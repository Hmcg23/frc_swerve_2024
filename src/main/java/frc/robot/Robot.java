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
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Robot extends TimedRobot {
  // private DifferentialDrive m_myRobot;
  private final Timer m_timer = new Timer();

  // Drive Motors
  private static final int frontLeftDriveID = 2;
  private static final int frontRightDriveID = 8;
  private static final int backLeftDriveID = 4;
  private static final int backRightDriveID = 6;

  private CANSparkMax m_frontLeftDrive;
  private CANSparkMax m_frontRightDrive;
  private CANSparkMax m_backLeftDrive;
  private CANSparkMax m_backRightDrive;
  // Directional Motors
  private static final int frontLeftDirectionalId = 1;
  private static final int frontRightDirectionalId = 7;
  private static final int backLeftDirectionalId = 3;
  private static final int backRightDirectionalId = 5;

  private CANSparkMax m_frontLeftDirectional;
  private CANSparkMax m_frontRightDirectional;
  private CANSparkMax m_backLeftDirectional;
  private CANSparkMax m_backRightDirectional;

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

  // Drive Motors
    m_frontLeftDrive = new CANSparkMax(frontLeftDriveID, MotorType.kBrushless);
    m_frontRightDrive = new CANSparkMax(frontRightDriveID, MotorType.kBrushless);
    m_backLeftDrive = new CANSparkMax(backLeftDriveID, MotorType.kBrushless);
    m_backRightDrive = new CANSparkMax(backRightDriveID, MotorType.kBrushless);
  
  // Directional Motors
    m_frontLeftDirectional = new CANSparkMax(frontLeftDirectionalId, MotorType.kBrushless);
    m_frontRightDirectional = new CANSparkMax(frontRightDirectionalId, MotorType.kBrushless);
    m_backLeftDirectional = new CANSparkMax(backLeftDirectionalId, MotorType.kBrushless);
    m_backRightDirectional = new CANSparkMax(backRightDirectionalId, MotorType.kBrushless);

    /* Current: Set directional motors to brake mode, initialize them
    at 0 so they they all have the same direciton */

    /**
     * The RestoreFactoryDefaults method can be used to reset the configuration parameters
     * in the SPARK MAX to their factory default state. If no argument is passed, these
     * parameters will not persist between power cycles
     */
    m_frontLeftDrive.restoreFactoryDefaults();
    m_frontRightDrive.restoreFactoryDefaults();
    m_backLeftDrive.restoreFactoryDefaults();
    m_backRightDrive.restoreFactoryDefaults();

    m_frontLeftDirectional.setIdleMode(IdleMode.kBrake);
    m_frontRightDirectional.setIdleMode(IdleMode.kBrake);
    m_backLeftDirectional.setIdleMode(IdleMode.kBrake);
    m_backRightDirectional.setIdleMode(IdleMode.kBrake);

    // m_myRobot = new DifferentialDrive(m_frontLeftMotor, m_frontRightMotor);

    m_leftJoystick = new Joystick(0);
    m_rightJoystick = new Joystick(1);

    m_backLeftDrive.follow(m_frontLeftDrive);
    m_backRightDrive.follow(m_frontRightDrive);

  }

  @Override
  public void autonomousInit() {
    m_timer.restart();
  }

  @Override
  public void teleopInit() {}

  @Override
  public void autonomousPeriodic() {
    if (m_timer.get() < 10.0) {
      m_frontLeftDrive.set(0.5);
      m_frontRightDrive.set(0.5);
      m_backLeftDrive.set(0.5);
      m_backRightDrive.set(0.5);

    } else {
      m_frontLeftDrive.stopMotor();
      m_frontRightDrive.stopMotor();
      m_backLeftDrive.stopMotor();
      m_backRightDrive.stopMotor();
    }
  }

  @Override
  public void teleopPeriodic() {
    m_frontRightDrive.set(-m_leftJoystick.getY());
    m_frontLeftDrive.set(m_rightJoystick.getY());

    m_backLeftDirectional.set(0);
    m_backRightDirectional.set(0);
    m_frontRightDirectional.set(0);
    m_frontLeftDirectional.set(0);
  }
}