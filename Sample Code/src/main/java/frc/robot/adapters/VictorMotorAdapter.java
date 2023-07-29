package frc.robot.adapters;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class VictorMotorAdapter implements MotorController {

    private VictorSPX victorSPX;

    private boolean isDisabled = false;

    public VictorMotorAdapter(VictorSPX victorSPX) {
        this.victorSPX = victorSPX;
    }

    @Override
    public void set(double speed) {
        if (!isDisabled)
            victorSPX.set(VictorSPXControlMode.PercentOutput, speed);
    }

    @Override
    public double get() {
        return victorSPX.getMotorOutputPercent();
    }

    @Override
    public void setInverted(boolean isInverted) {
        victorSPX.setInverted(isInverted);
    }

    @Override
    public boolean getInverted() {
        return victorSPX.getInverted();
    }

    @Override
    public void disable() {
        isDisabled = true;
    }

    @Override
    public void stopMotor() {
        this.set(0);
    }
    
}
