/*package frc.robot.commands;

import java.lang.module.ModuleDescriptor.Requires;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorUpCommand extends Command{
    private final ElevatorSubsystem Elevator() {
        Requires Subsystem;
    }

    protected void Initialize() {

    }

    public void execute() {
            TalonFX.primaryMotor.setArmPosition(0.8);
        }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
            TalonFX.primaryMotor.setArmPosition(0.0);
    }

    protected void interrupted() {
        end();
    }
}
*/