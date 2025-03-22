package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Power;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class ShooterSubsystem extends SubsystemBase {
/*
    AlgaeArm Window motor w/SparkMax Arm CAN: roborio 25
    AlgaeArm potentiometer   Arm Pot ANALOG IN   0
    AlgaeArm Neverest w/SparkMax   Algae Intake    CAN: roborio    26
    AlgaeArm Neverest w/SparkMax   Algae Intake Follower   CAN: roborio    26
*/
    private SparkMax armMotor;
    private AnalogPotentiometer armPot;
    private final TalonFX intakeMotor;
    private final DigitalInput intakeLimit;


    public ShooterSubsystem() {
        
        armMotor = new SparkMax(Constants.AlgaeArmConstants.algaeArmID, MotorType.kBrushed);
        intakeMotor = new TalonFX(Constants.ElevatorConstants.intakeID, Constants.ElevatorConstants.intakeCAN);
        armPot = new AnalogPotentiometer(Constants.AlgaeArmConstants.algaeArmPotID);
            intakeMotor.setNeutralMode(NeutralModeValue.Brake);
            TalonFXConfiguration motorConfig = new TalonFXConfiguration();
            motorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
            intakeMotor.getConfigurator().apply(motorConfig);
        intakeLimit = new DigitalInput(Constants.ElevatorConstants.intakelimitID);
    }

    public void setArmPosition(double position) {
        // TODO: add logic to use positional PID with getArmPosition as sensor
    }

    public void getArmPosition() {
        // TODO: add logic to translate armPot to a position
    }

    public void setIntake() {
        armMotor.set(0.05);
    }

    public void setOuttake() {
        armMotor.set(-0.2);
    }

    public void stopIntake() {
        armMotor.set(0);
    }

    public void setShoot() {
    //    armMotor().setPosition(); 
    }

    public void setPower(double p) {
        intakeMotor.set(p);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("armPot", armPot.get());
    }
}