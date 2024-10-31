package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Template;

public class MultiSubsystemCommands {
    public static Command MultiSubsystemCommand(Template template) {
        return Commands.repeatingSequence(
            template.exampleFactory()
        );
    }
}
