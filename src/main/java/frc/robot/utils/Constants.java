package frc.robot.utils;

public class Constants {
    public static final String roborioCAN = "rio";
    public static final String canivoreCAN = "canivore";
    
    public static class ElevatorConstants {
        public static final int elevatorID = 18;
        public static final String elevatorCAN = Constants.roborioCAN;
        public static final int intakeID = 25;
        public static final String intakeCAN = Constants.roborioCAN;
        public static final int bottomlimitSwitchPort = 1;
        public static final int toplimitSwitchPort = 2;
        public static final int intakelimitID = 3;
        public static final int countsPerInch = 0;
        public static final double kp = 0; 
        public static final double ki = 0; 
        public static final double kd = 0;
        public static final int L4 = 174;
        public static final int L3 = 85;
        public static final int L2 = 40;
        public static final int L1 = 10;
        public static final int CORAL_STATION = 0;
    }

    public static class ClimberConstants {
        public static final int primaryMotorID = 16;
        public static final String primaryMotorCAN = Constants.roborioCAN;
        public static final int followerMotorID = 17;
        public static final String followerMotorCAN = Constants.roborioCAN;
        public static final int lowerLimitSwitchID = 0;
    }

    public static class AlgaeArmConstants {
        public static final int algaeArmID = 25;
        public static final String algaeArmCAN = Constants.roborioCAN;
        public static final int algaeIntakeID = 26;
        public static final String algaeIntakeCAN = Constants.roborioCAN;
        public static final int algaeArmPotID = 0;
        public static final double CORAL_STATION_POSITION = 0.802;
        public static final double L1_POSITION = 0.517;
        public static final double L2_POSITION = 0.517;
        public static final double L3_POSITION = 0.517;
        public static final double L4_POSITION = 0.517;
        public static final double STARTING_POSITION = 0.371;
    }
}
