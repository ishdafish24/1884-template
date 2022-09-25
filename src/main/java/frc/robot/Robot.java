// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.RobotMap.DriveMap.BACK_LEFT_MODULE_STEER_MOTOR;
import static frc.robot.RobotMap.DriveMap.BACK_RIGHT_MODULE_STEER_MOTOR;
import static frc.robot.RobotMap.DriveMap.FRONT_LEFT_MODULE_STEER_MOTOR;
import static frc.robot.RobotMap.DriveMap.FRONT_RIGHT_MODULE_STEER_MOTOR;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.util.MathUtils;
import frc.robot.util.controllers.ButtonMap;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // TODO put auto chooser here. make sure to use the one from
    // robot/auto/selector/AutoModeSelector.java

    // ExampleElevator.getInstance();

    var oi = OI.getInstance();

    var brr = new TalonFX(BACK_RIGHT_MODULE_STEER_MOTOR);
    var blr = new TalonFX(BACK_LEFT_MODULE_STEER_MOTOR);
    var frr = new TalonFX(FRONT_RIGHT_MODULE_STEER_MOTOR);
    var flr = new TalonFX(FRONT_LEFT_MODULE_STEER_MOTOR);

    brr.setSelectedSensorPosition(0, 0, 20);
    blr.setSelectedSensorPosition(0, 0, 20);
    frr.setSelectedSensorPosition(0, 0, 20);
    flr.setSelectedSensorPosition(0, 0, 20);

    var swerve = SwerveDrive.getInstance();
    swerve.zeroGyroscope();

    // ? this is all the binding stuff, maybe let's move it somewhere else?
    swerve.setDefaultCommand(
        swerve.driveCommand(
            () ->
                ChassisSpeeds.fromFieldRelativeSpeeds(
                    -MathUtils.modifyAxis(oi.getDriver().getAxis(ButtonMap.Axis.AXIS_LEFT_Y))
                        * SwerveDrive.MAX_VELOCITY_METERS_PER_SECOND,
                    -MathUtils.modifyAxis(oi.getDriver().getAxis(ButtonMap.Axis.AXIS_LEFT_X))
                        * SwerveDrive.MAX_VELOCITY_METERS_PER_SECOND,
                    -MathUtils.modifyAxis(oi.getDriver().getAxis(ButtonMap.Axis.AXIS_RIGHT_X))
                        * SwerveDrive.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND,
                    swerve.getGyroscopeRotation())));
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {}

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    // TODO figure out how cancel auto maybe this?
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
