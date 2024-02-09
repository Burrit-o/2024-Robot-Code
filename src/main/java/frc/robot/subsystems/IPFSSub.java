// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.PickupConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;




public class IPFSSub extends SubsystemBase {
  private final CANSparkMax TLShooterMotor;
  private final CANSparkMax Feeder;
  private final CANSparkMax TRShooterMotor;
  private final CANSparkMax BLShooterMotor;
  private final CANSparkMax BRShooterMotor;
  private final CANSparkMax IntakeMotor;
  
  private final RelativeEncoder TLEncoder; 
  private final RelativeEncoder TREncoder;
  private final RelativeEncoder BLEncoder;
  private final RelativeEncoder BREncoder;
  
  private final DigitalInput IntakeSensor;

  /** Creates a new IPFSSub. */
  public IPFSSub() {
  TLShooterMotor = new CANSparkMax(ShooterConstants.TLShooterMotor, MotorType.kBrushless);
  TRShooterMotor = new CANSparkMax(ShooterConstants.TRShooterMotor, MotorType.kBrushless);
  TRShooterMotor.setInverted(true);
  BLShooterMotor = new CANSparkMax(ShooterConstants.BLShooterMotor, MotorType.kBrushless);
  BRShooterMotor = new CANSparkMax(ShooterConstants.BRShooterMotor, MotorType.kBrushless);
  Feeder = new CANSparkMax(PickupConstants.PFeederMotor, MotorType.kBrushless);
  IntakeMotor = new CANSparkMax(PickupConstants.PickupMotor, MotorType.kBrushless);

  TLEncoder = TLShooterMotor.getEncoder();
  TREncoder = TRShooterMotor.getEncoder();
  BLEncoder = BLShooterMotor.getEncoder();
  BREncoder = BRShooterMotor.getEncoder();

  IntakeSensor = new DigitalInput(1);

  }

  public double TLVelocity() {
    return TLEncoder.getVelocity();
  }
  
  public double TRVelocity() {
    return TREncoder.getVelocity();
  }

  public double BLVelocity() {
    return BLEncoder.getVelocity();
  }
  
  public double BRVelocity() {
    return BREncoder.getVelocity();
  }

  public void Shoot(double speed) {
    TLShooterMotor.set(-speed);
    TRShooterMotor.set(speed);
  }
  
  public void Feed(double speed) {
    Feeder.set(speed);
  }

  public void Intake(double speed) {
    IntakeMotor.set(speed);
  }

  public boolean haveNote() {
    return IntakeSensor.get();
  }

  

    @Override


  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("TLSpeed", TLVelocity());
    SmartDashboard.putNumber("TRSpeed", TRVelocity());
    SmartDashboard.putNumber("BLSpeed", BLVelocity());
    SmartDashboard.putNumber("BRSpeed", BRVelocity());
    SmartDashboard.putBoolean("Intake Sensor", IntakeSensor.get());

  }
}
