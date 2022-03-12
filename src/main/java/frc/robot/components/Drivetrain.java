package frc.robot.components;

import frc.robot.adapters.VictorMotorAdapter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

/**
 * A class that represents the drivetrain of the robot.
 * <p> Responsible for driving the robot.
 */
public class Drivetrain {

    Joystick driverInput;

    VictorSPX m_frontLeft = new VictorSPX(3);
	VictorSPX m_rearLeft = new VictorSPX(2);
	MotorControllerGroup m_left = 	new MotorControllerGroup(
										new VictorMotorAdapter(m_frontLeft),
										new VictorMotorAdapter(m_rearLeft)
										);
	
	VictorSPX m_frontRight = new VictorSPX(0);
	VictorSPX m_rearRight = new VictorSPX(1);
	MotorControllerGroup m_right = 	new MotorControllerGroup(
										new VictorMotorAdapter(m_frontRight),
										new VictorMotorAdapter(m_rearRight)
										);

	DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

    /**
     * @param {Joystick} driverInput - Device recieving driver input.
     * @param {double} speedMultiplier - Used to set max output.
     */
    public Drivetrain(Joystick driverInput, double speedMultiplier) {
        this.driverInput = driverInput;

        m_left.setInverted(true);
		m_drive.setMaxOutput(speedMultiplier);
    }

    public void drive() {
        double yAxis = driverInput.getRawAxis(1); // + FORWARDS, - BACKWARDS
		double xAxis = driverInput.getRawAxis(0); // + RIGHT, - LEFT
		
		m_drive.arcadeDrive(yAxis, -xAxis);
    }
}
