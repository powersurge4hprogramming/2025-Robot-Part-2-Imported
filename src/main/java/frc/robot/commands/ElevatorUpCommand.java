package frc.robot.commands;

import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorUpCommand extends Command {
  private ElevatorSubsystem m_subsystem = null;

  public ElevatorUpCommand(ElevatorSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  public void initialize() {}

  public void execute() {
    if (m_subsystem.isUp()) {
      m_subsystem.setPower(0);
    } else {
        m_subsystem.setPosition(90);
    }
  }

  public void end(boolean interrupted) {
    m_subsystem.setPower(0);
  }

  public boolean isFinished() {
    return false;
  }
}