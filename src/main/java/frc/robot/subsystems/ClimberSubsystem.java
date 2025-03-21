package frc.robot.subsystems;


import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionDutyCycle;
//import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

//import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;


public class ClimberSubsystem extends SubsystemBase {
    private final double kMaxRotation = 1;

    private  TalonFX motor;
    private PositionDutyCycle motorOut = new PositionDutyCycle(0).withSlot(0);
//    private  TalonFX follower;
    private  double homeOffset;
    private DigitalInput lowerLimitSwitch;
/*
Climber Kraken x60  Climber CAN: roborio    16
Climber limit switch    Climber Home    DIO 2
Climber REV Through Bore Encoder    Climber Output   DIO 0,1
*/
    public ClimberSubsystem() {
        motor = new TalonFX (Constants.ClimberConstants.primaryMotorID, Constants.ClimberConstants.primaryMotorCAN);
        motor.setControl(motorOut);


        TalonFXConfiguration motorConfig = new TalonFXConfiguration();
        motorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        motor.getConfigurator().apply(motorConfig);

        Slot0Configs slot = new Slot0Configs();
        slot.kP = .1;
        slot.kI = 0;
        slot.kD = 0;
        motor.getConfigurator().apply(slot);
        homeOffset = getCurrentPosition();

        lowerLimitSwitch = new DigitalInput(Constants.ClimberConstants.lowerLimitSwitchID);
    }

    public double getCurrentPosition() {
        StatusSignal<Double> p = motor.getDifferentialOutput();
        return p.getValueAsDouble();
    }

    public void home(){
        // If the lower limit switch is open, lower the motor
        while (!lowerLimitSwitch.get()) {
            motor.set(-.1);
        }
        // Stop the motor
        motor.set(0);

        // Update the homeOffset
        homeOffset = getCurrentPosition();
    }

    public void setPosition(double p){
        motor.setControl(motorOut.withPosition(p));
    }   

    public boolean isUp() {
        return (getCurrentPosition() > (homeOffset + kMaxRotation));
    }

    public boolean isDown() {
        return (getCurrentPosition() > (homeOffset + kMaxRotation));
    }

    public void setPower(double p) {
        motor.set(p);
    }
}