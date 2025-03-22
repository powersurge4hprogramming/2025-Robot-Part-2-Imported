package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.math.controller.PIDController;

/*public class CoralPickupCommand extends PIDCommand { 
   private final ElevatorSubsystem arm ;
    private boolean hold = true;

    public CoralPickupCommand(ElevatorSubsystem subsystem){
        super ( new PIDController(Constants.ElevatorConstants.kp,Constants.ElevatorConstants.ki, Constants.ElevatorConstants.kd ), Constants.ElevatorConstants.L4,subsystem.getmotorconsumer());
        arm=subsystem;
        addRequirements(subsystem);
    }

    public CoralPickupCommand(ElevatorSubsystem subsystem, boolean h){
        super ( new PIDController(Constants.ElevatorConstants.kp,Constants.ElevatorConstants.ki, Constants.ElevatorConstants.kd ), Constants.ElevatorConstants.L4,subsystem.getmotorconsumer());
        hold = h;
        arm=subsystem;
        addRequirements(subsystem);
    }

    public void execute (){
        super.execute();
        arm.setSetPoint(Constants.AlgaeArmConstants.CORAL_STATION_POSITION);
    }
}
    */