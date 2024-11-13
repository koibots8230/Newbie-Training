package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import monologue.Annotations.Log;
import monologue.Logged;

public class Template extends SubsystemBase implements Logged {

  @Log double loggedValue;

  public Template() {
    loggedValue = 0;
    // This is your constructor
  }

  @Override
  public void periodic() {
    loggedValue += 0.1;
  }

  private void exampleMethod() {}

  public Command exampleFactory() {
    return Commands.runOnce(
        this::exampleMethod,
        this); // NOTE: Do not forget the subsystem requirement (the "this" part)
  }
}
