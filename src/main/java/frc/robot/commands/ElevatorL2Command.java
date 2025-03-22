package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.math.controller.PIDController;

/*public class ElevatorL2Command extends PIDCommand { 
    private final ElevatorSubsystem arm ;
    private boolean hold = true;

    public ElevatorL2Command(ElevatorSubsystem subsystem){
        super ( new PIDController(Constants.ElevatorConstants.kp,Constants.ElevatorConstants.ki, Constants.ElevatorConstants.kd ), Constants.ElevatorConstants.L4,subsystem.getmotorconsumer());
        arm=subsystem;
        addRequirements(subsystem);
    }

    public ElevatorL2Command(ElevatorSubsystem subsystem, boolean h){
        super ( new PIDController(Constants.ElevatorConstants.kp,Constants.ElevatorConstants.ki, Constants.ElevatorConstants.kd ), Constants.ElevatorConstants.L4,subsystem.getmotorconsumer());
        hold = h;
        arm=subsystem;
        addRequirements(subsystem);
    }

    public void execute (){
        super.execute();
        arm.setSetPoint(Constants.AlgaeArmConstants.L2_POSITION);
    }

    public boolean isFinished(){
        return (hold) ? false : getController().atSetpoint();
    }
}

*/