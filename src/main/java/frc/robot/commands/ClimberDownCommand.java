package frc.robot.commands;

import frc.robot.subsystems.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ClimberDownCommand extends Command {
  private ClimberSubsystem m_subsystem = null;

  public ClimberDownCommand(ClimberSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  public void initialize() {}

  public void execute() {
    if (m_subsystem.isDown()) {
      m_subsystem.setPower(0);
    } else {
        m_subsystem.setPosition(70);
    }
  }

  public void end(boolean interrupted) {
    m_subsystem.setPower(0);
  }

  public boolean isFinished() {
    return false;
  }
}