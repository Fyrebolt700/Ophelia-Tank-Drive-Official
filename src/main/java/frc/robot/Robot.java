
//import packages
package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;


public class Robot extends TimedRobot {

  //initialize motors with correct device IDs and motor type
  private final CANSparkMax m_leftMotor1 = new CANSparkMax(1, MotorType.kBrushed);
  private final CANSparkMax m_leftMotor2 = new CANSparkMax(2, MotorType.kBrushed);

  private final CANSparkMax m_rightMotor1 = new CANSparkMax(3, MotorType.kBrushed);
  private final CANSparkMax m_rightMotor2 = new CANSparkMax(4, MotorType.kBrushed);

  //initializing tank drive with both motors
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor1, m_rightMotor1);

  //initalizing controller
  private final Joystick m_stick = new Joystick(0);

  //adds both motors to registry under parent object(m_robotDrive)
  //For better organization later on 
  public Robot() {
    SendableRegistry.addChild(m_robotDrive, m_leftMotor1);
    SendableRegistry.addChild(m_robotDrive, m_rightMotor1);

    SendableRegistry.addChild(m_robotDrive, m_leftMotor2);
    SendableRegistry.addChild(m_robotDrive, m_rightMotor2);
  }

  @Override
  public void robotInit() {
    // We need to invert both sides
    m_rightMotor1.setInverted(true);
    m_leftMotor1.setInverted(true);
    m_rightMotor2.follow(m_rightMotor1);
    m_leftMotor2.follow(m_leftMotor1);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // Y axis drives forward
    // X turns left and right
    
    //m_robotDrive.arcadeDrive(m_stick.getX() * 0.6, m_stick.getY() * 0.6);

    //m_robotDrive.arcadeDrive(-m_stick.getX(), m_stick.getY());

    //if the "RB" button is held down, max speed is 60%
    if (m_stick.getRawButton(6)){
      m_robotDrive.arcadeDrive(m_stick.getX()*0.7, -m_stick.getY()*0.7);
    } else {
      m_robotDrive.arcadeDrive(m_stick.getX(), -m_stick.getY());
    }
  }
}
