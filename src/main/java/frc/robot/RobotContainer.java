// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Feed;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.IPFSSub;
import frc.robot.commands.Intake;
import frc.robot.commands.IntakeAndFeed;
import frc.robot.commands.AutoShoot;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final IPFSSub m_IPFSSub;

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    m_IPFSSub = new IPFSSub();
    m_IPFSSub.setDefaultCommand(new Shoot(m_IPFSSub));
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    Trigger aButton = m_driverController.a();
    aButton.whileTrue(new Feed(m_IPFSSub));
    Trigger xButton = m_driverController.x();
    xButton.whileTrue(new Intake(m_IPFSSub));
    Trigger rBumper = m_driverController.rightBumper();
    //AutoShoot needs a trigger constraint: if BeanBrake(IntakeSensor)
    //reads true, allow AutoShoot to run off a keybind
    //Later this kiybind will actually be a boolean on whether we are centered on target

    rBumper.whileTrue(new AutoShoot(m_IPFSSub));
    //Per Mando and Christyn request:
    Trigger lBumper = m_driverController.leftBumper();
    lBumper.whileTrue(new IntakeAndFeed(m_IPFSSub));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  //public Command getAutonomousCommand() {
    // An example command will be run in autonomous
   // return Autos.exampleAuto(m_exampleSubsystem);
  }
//}
