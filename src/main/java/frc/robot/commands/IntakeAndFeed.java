// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.IPFSSub;

public class IntakeAndFeed extends Command {
  private final Subsystem m_subsystem;
  /** Creates a new Feed. */
  public IntakeAndFeed(IPFSSub subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // We want to run these two at siilar or same speeds,
    // or else the transfer between can have issues
    ((IPFSSub) m_subsystem).Feed(0.5);
    ((IPFSSub) m_subsystem).Intake(0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ((IPFSSub) m_subsystem).Feed(0);
    ((IPFSSub) m_subsystem).Intake(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (((IPFSSub) m_subsystem).hasNote()) {
      return true;
    }else{
      return false;
    }
  }
}
