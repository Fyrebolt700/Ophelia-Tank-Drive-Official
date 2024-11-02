package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
    private Joystick m_controller;
    private CANSparkMax m_leftMotor1;
    private CANSparkMax m_leftMotor2;
    private CANSparkMax m_rightMotor1;
    private CANSparkMax m_rightMotor2;
 
   @Override
  public void robotInit() {
    m_leftMotor1 = new CANSparkMax(1, MotorType.kBrushed);
    m_leftMotor2 = new CANSparkMax(2, MotorType.kBrushed);
    m_rightMotor1 = new CANSparkMax(3, MotorType.kBrushed);
    m_rightMotor2 = new CANSparkMax(4, MotorType.kBrushed);
    m_leftMotor1.follow(m_leftMotor2);
    m_rightMotor1.follow(m_rightMotor2);
    m_controller = new Joystick(0);


  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    double leftWheelsRaw = m_controller.getRawAxis(1) + m_controller.getRawAxis(2);
    double rightWheelsRaw = m_controller.getRawAxis(5) - m_controller.getRawAxis(2);
    double leftWheels = 0;
    double rightWheels = 0;

    if (rightWheelsRaw > 1) {
       rightWheels = 1;
    } else if (rightWheelsRaw < -1){ 
      rightWheels = -1;
    } else {
      rightWheels = rightWheelsRaw;
    }
    if (leftWheelsRaw > 1) {
       leftWheels = 1;
    } else if (leftWheelsRaw < -1){ 
      leftWheels = -1;
    } else {
      leftWheels = leftWheelsRaw;
    }
    double speed = 1;

    m_leftMotor1.set(leftWheels * speed);
    m_leftMotor2.set(-leftWheels * speed);
    m_rightMotor1.set(-rightWheels * speed);
    m_rightMotor2.set(rightWheels * speed); 
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
