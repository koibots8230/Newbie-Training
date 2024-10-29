// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.RobotConstants;
import frc.robot.subsystems.Template;
import monologue.Logged;
import monologue.Monologue;

public class RobotContainer implements Logged {
  private final XboxController controller;

  private final Template template;

  public RobotContainer() {
    controller = new XboxController(RobotConstants.CONTROLLER_PORT);

    template = new Template();

    Monologue.setupMonologue(
        this,
        RobotConstants.LOGGING_ROOT_PATH,
        RobotConstants.LOGGING_FILE_ONLY,
        RobotConstants.LAZY_LOGGING);

    configureBindings();
  }

  private void configureBindings() {
    Trigger exampleTrigger =
        new Trigger(() -> controller.getLeftTriggerAxis() > RobotConstants.TRIGGER_DEADZONE);
    exampleTrigger.onTrue(template.exampleFactory());
    exampleTrigger.onFalse(template.exampleFactory());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
