package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.math.controller.PIDController;

/*public class ElevatorL4Command extends PIDCommand { 
    private final ElevatorSubsystem elevator ;
    private boolean hold = true;

    public ElevatorL4Command(ElevatorSubsystem subsystem){
     super( new PIDController(Constants.ElevatorConstants.kp,Constants.ElevatorConstants.ki, Constants.ElevatorConstants.kd ), Constants.ElevatorConstants.L4,subsystem.getmotorconsumer());
        elevator=subsystem;
        addRequirements(subsystem);
    }

    public ElevatorL4Command(ElevatorSubsystem subsystem, boolean h){
        super ( new PIDController(Constants.ElevatorConstants.kp,Constants.ElevatorConstants.ki, Constants.ElevatorConstants.kd ), Constants.ElevatorConstants.L4,subsystem.getmotorconsumer());
        hold = h;
        elevator=subsystem;
        addRequirements(subsystem);
    }

    @SuppressWarnings("removal")
    public void execute (){
        elevator.setSetPoint(Constants.ElevatorConstants.Level4);
    }

    public boolean isFinished(){
        return (hold) ? false : getController().atSetpoint();
    }
}
    */