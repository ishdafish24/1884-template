package frc.robot.subsystems;

import java.util.HashSet;
import java.util.Set;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.RobotMap;
import frc.robot.util.drivers.TalonSRXFactory;

public class ExampleTankDrive extends SubsystemBase {
  private static ExampleTankDrive instance;

  public static ExampleTankDrive getInstance() {
    if (instance == null) instance = new ExampleTankDrive();
    return instance;
  }

  // Motors
  public TalonSRX leftFront;
  public TalonSRX leftBack;
  public TalonSRX rightFront;
  public TalonSRX rightBack;

  // Gains
  private static final double kP = 0.1;
  private static final double kI = 0.1;
  private static final double kD = 0.1;
  private static final double kF = 0.1;

  private static final int kMMacceleration = (1000); // sensorUnitsPer100msPerSec
  private static final int kMMvelocity = (1000); // sensorUnitsPer100ms

  private static final int kElevatorTolerance = 1000;

  public ExampleTankDrive() {

    leftFront = TalonSRXFactory.createDefaultTalon(RobotMap.TankDriveMap.leftFrontMaster);
    leftBack = TalonSRXFactory.createDefaultTalon(RobotMap.TankDriveMap.leftBackMaster);
    rightFront = TalonSRXFactory.createDefaultTalon(RobotMap.TankDriveMap.rightFrontMaster);
    rightBack = TalonSRXFactory.createDefaultTalon(RobotMap.TankDriveMap.rightBackMaster);

    configPID(leftFront);
    configPID(leftBack);
    configPID(rightFront);
    configPID(rightBack);
  }

  public void configPID(TalonSRX motor) {
    motor.config_kP(0, kP, 0);
    motor.config_kI(0, kI, 0);
    motor.config_kD(0, kD, 0);
    motor.config_kF(0, kF, 0);

    motor.configMotionAcceleration(kMMacceleration);
    motor.configMotionCruiseVelocity(kMMvelocity);

    motor.setSensorPhase(true);
    motor.overrideLimitSwitchesEnable(false);
}

    public Command driveToCommand(double pos) {
        return new Command(){
            @Override
            public void initialize() {
                Command.super.initialize();
            }

            @Override
            public Set<Subsystem> getRequirements() {
                var reqs = new HashSet<Subsystem>();

                reqs.add(ExampleTankDrive.getInstance());
                return reqs;
            }
            
        };
    }

}