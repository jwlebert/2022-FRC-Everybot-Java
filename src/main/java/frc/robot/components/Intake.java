package frc.robot.components;

import edu.wpi.first.wpilibj.Joystick;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Intake {
    double speedMultiplier;

    VictorSPX m_intake = new VictorSPX(4);

    public Intake(double speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

    public void run(boolean forward, boolean reverse) {
        if (forward) {
			m_intake.set(VictorSPXControlMode.PercentOutput, speedMultiplier);
		} else if (reverse) {
			m_intake.set(VictorSPXControlMode.PercentOutput, -speedMultiplier);
		} else {
			m_intake.set(VictorSPXControlMode.PercentOutput, 0);
		}
    }

}
