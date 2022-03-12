// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// if these imports aren't working, right click 'build.gradle',
// 'Manage Vendor Libraries' > 'Install new libraries (online)'
// then copy paste the following link:
// https://maven.ctr-electronics.com/release/com/ctre/phoenix/Phoenix-frc2022-latest.json
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

/**
* The VM is configured to automatically run this class, and to call the functions corresponding to
* each mode, as described in the TimedRobot documentation. If you change the name of this class or
* the package after creating this project, you must also update the build.gradle file in the
* project.
*/
public class Robot extends TimedRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private final SendableChooser<String> m_chooser = new SendableChooser<>();
	
	// XboxController xbox = new XboxController(0);
	Joystick logitech = new Joystick(0);
	
	VictorSPX m_frontLeft = new VictorSPX(3); // leftForward
	VictorSPX m_rearLeft = new VictorSPX(2); // leftBackward
	MotorControllerGroup m_left = 	new MotorControllerGroup(
										new VictorMotorAdapter(m_frontLeft),
										new VictorMotorAdapter(m_rearLeft)
										);
	
	VictorSPX m_frontRight = new VictorSPX(0); // rightForward
	VictorSPX m_rearRight = new VictorSPX(1); // rightBackward
	MotorControllerGroup m_right = 	new MotorControllerGroup(
										new VictorMotorAdapter(m_frontRight),
										new VictorMotorAdapter(m_rearRight)
										);

	DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
	
	final double speedMultiplier = 0.5; // speed multiplier for the motors
	
	/**
	* This function is run when the robot is first started up and should be used for any
	* initialization code.
	*/
	@Override
	public void robotInit() {
		m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
		m_chooser.addOption("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);

		m_left.setInverted(true); // inverts left motors
		m_drive.setMaxOutput(speedMultiplier);
	}
	
	/**
	* This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
	* that you want ran during disabled, autonomous, teleoperated and test.
	*
	* <p>This runs after the mode specific periodic functions, but before LiveWindow and
	* SmartDashboard integrated updating.
	*/
	@Override
	public void robotPeriodic() {}
	
	/**
	* This autonomous (along with the chooser code above) shows how to select between different
	* autonomous modes using the dashboard. The sendable chooser code works with the Java
	* SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
	* uncomment the getString line to get the auto name from the text box below the Gyro
	*
	* <p>You can add additional auto modes by adding additional comparisons to the switch structure
	* below with additional strings. If using the SendableChooser make sure to add them to the
	* chooser code above as well.
	*/
	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}
	
	/** This function is called periodically during autonomous. */
	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case kCustomAuto:
			// Put custom auto code here
			break;
			case kDefaultAuto:
			default:
			// Put default auto code here
			break;
		}
	}
	
	/** This function is called once when teleop is enabled. */
	@Override
	public void teleopInit() {}
	
	/** This function is called periodically during operator control. */
	@Override
	public void teleopPeriodic() {
		double yAxis = logitech.getRawAxis(1); // + FORWARDS, - BACKWARDS
		double xAxis = logitech.getRawAxis(0); // + RIGHT, - LEFT
		
		m_drive.arcadeDrive(yAxis, -xAxis);
	}
	
	/** This function is called once when the robot is disabled. */
	@Override
	public void disabledInit() {}
	
	/** This function is called periodically when disabled. */
	@Override
	public void disabledPeriodic() {}
	
	/** This function is called once when test mode is enabled. */
	@Override
	public void testInit() {}
	
	/** This function is called periodically during test mode. */
	@Override
	public void testPeriodic() {}
	
	/** This function is called once when the robot is first started up. */
	@Override
	public void simulationInit() {}
	
	/** This function is called periodically whilst in simulation. */
	@Override
	public void simulationPeriodic() {}
}