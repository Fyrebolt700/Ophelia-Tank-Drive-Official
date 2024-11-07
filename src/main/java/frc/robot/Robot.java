
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
  //private final Joystick m_rightStick = new Joystick(1);


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
    //reduce speed on right side
    //m_rightMotor1.set(m_rightMotor1.get() * 0.95);
    m_rightMotor2.follow(m_rightMotor1);
    m_leftMotor2.follow(m_leftMotor1);

  }

  @Override

  //arcade drive for Teleop
  public void teleopPeriodic() {

    //double right_motorSpeed = m_leftMotor1

  //m_robotDrive.arcadeDrive(m_leftStick.getRawAxis(2) * 0.75, -m_leftStick.getRawAxis(1));

    // //if the "RB" button is held down, max speed is 70%
    if (m_stick.getRawButton(6)){
      //turning constant: 70%
      //slows forward/backward: 65%
      m_robotDrive.arcadeDrive(m_stick.getRawAxis(4)*0.70, -m_stick.getRawAxis(1)*0.65);
    } else if(m_stick.getRawButton(5)){
      m_robotDrive.arcadeDrive(m_stick.getRawAxis(4)*0, -m_stick.getRawAxis(1)*0);
      //m_robotDrive.stopMotor();
    } else {
      //turning constant: 70%
      //forward/backward regular speed: 75%
      m_robotDrive.arcadeDrive(m_stick.getRawAxis(4) * 0.70, -m_stick.getRawAxis(1)* 0.70);
    }




    //2-stick drivecontrol
    // if (m_leftStick.getRawButton(6)){
    //   m_robotDrive.tankDrive(m_leftStick.getX()*0.6, m_rightStick.getX()*0.6);

    // } else {
    //   m_robotDrive.tankDrive(m_leftStick.getX(), m_rightStick.getX());
    // }
    
    //stops motor when "LB" button is help down
    // if (m_leftStick.getRawButton(5)){
    //   m_robotDrive.arcadeDrive(m_leftStick.getRawAxis(2)*0, -m_leftStick.getRawAxis(1)*0);
    // } else {
    //   m_robotDrive.arcadeDrive(m_leftStick.getX(), -m_leftStick.getY());
    // }
  }
}

