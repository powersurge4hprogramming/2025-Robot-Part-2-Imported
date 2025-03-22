package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;
import frc.robot.utils.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
    private final TalonFX primaryMotor;
    private final StatusSignal<Double> encoder;
    private final DigitalInput bottomLimit;
    private final DigitalInput topLimit;
    private double currentPos = 0;

    private boolean isHomed = false;
/* 
    public enum ElevatorPosition {
        DOWN(ElevatorConstants.downPos),
        POSITION_1(ElevatorConstants.L1),
        POSITION_2(ElevatorConstants.L2),
        POSITION_3(ElevatorConstants.L3),
        POSITION_4(ElevatorConstants.L4);

        public final double positionInches;
        
        ElevatorPosition(double positionInches) {
            this.positionInches = positionInches;
        }
    }
*/
/*
Elevator    NEO Elevator    CAN: roborio    18
Elevator    NEO Elevator Follower   CAN: roborio    19
Elevator    limit switch    Elevator Lower  SparkMax    n/a
Elevator    limit switch    Elevator Upper  SparkMax    n/a
Elevator    REV Through Bore Encoder    Elevator Output SparkMax    n/a
Elevator    NEO Intake  CAN: roborio    20
*/
    public ElevatorSubsystem() {
        primaryMotor = new TalonFX(Constants.ElevatorConstants.elevatorID, Constants.ElevatorConstants.elevatorCAN);
    
        encoder = primaryMotor.getDifferentialOutput();
        bottomLimit = new DigitalInput(ElevatorConstants.bottomlimitSwitchPort);
        topLimit = new DigitalInput(ElevatorConstants.toplimitSwitchPort);

        configureMotors();
        homeElevator();
    }

    private void homeElevator() {
        while (!bottomLimit.get()) {
            primaryMotor.set(-.1);
        }
        primaryMotor.set(0);
        isHomed = true;
    }

    private void configureMotors() {
        // Primary motor configuration
        primaryMotor.setNeutralMode(NeutralModeValue.Brake);
            TalonFXConfiguration motorConfig = new TalonFXConfiguration();
            motorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
            primaryMotor.getConfigurator().apply(motorConfig);

            Slot0Configs slot = new Slot0Configs();
            slot.kP = .1;
            slot.kI = 0;
            slot.kD = 0;
            primaryMotor.getConfigurator().apply(slot);

        }

    public void increasePosition() {
        double cur = encoder.getValueAsDouble();
        double diff = 20;
        setPosition(cur+diff);
    }

    public void decreasePosition() {
        double cur = encoder.getValueAsDouble();
        double diff = -20;
        setPosition(cur+diff);
    }

    public void setPosition(double position) {
        double cur = encoder.getValueAsDouble();
        double diff = position - cur;
        if (diff < 0 && bottomLimit.get()) {
            stopElevator();
        } else if (diff > 0 && topLimit.get()) {
            stopElevator();
        } else {
            primaryMotor.setPosition(position);
        }
    }

    public void setElevatorPower(double p) {
        double cur = encoder.getValueAsDouble();
        double diff = p * 0.1;
        double position = cur + diff;
        if (diff < 0 && bottomLimit.get()) {
            stopElevator();
        } else if (diff > 0 && topLimit.get()) {
            stopElevator();
        } else {
            primaryMotor.setPosition(position);
        }
    }

    @Override
    public void periodic() {

        currentPos = encoder.getValueAsDouble() / ElevatorConstants.countsPerInch;
    }
    public void stopElevator() {
        primaryMotor.set(0);
    }
 
    public DoubleConsumer getmotorconsumer(){
    return (v) -> setElevatorPower(v);
    }
}