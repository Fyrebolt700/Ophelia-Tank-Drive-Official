
//import needed packages
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.Encoder;

/**
 * This is a basic TankDrive program with both autonomous and Teleop using arcade drive (its made to drive using 1 joystick on a controller).
 */
public class Robot extends TimedRobot {
  private final PWMSparkMax m_leftMotor = new PWMSparkMax(0);
  private final PWMSparkMax m_rightMotor = new PWMSparkMax(1);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  private final Joystick m_stick = new Joystick(0);
  private final Encoder m_leftEncoder = new Encoder(0,1);
  private final Encoder m_rightEncoder = new Encoder(2,3);

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightMotor.setInverted(true);
  }

    // Teleop: human operated
  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis on a joystick drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(-m_stick.getY(), -m_stick.getX());
    
    // When top button is pressed, turn right and drive forward
    // arcade drive: (speed, rotation)
    if(m_stick.getTopPressed()){
      m_robotDrive.arcadeDrive(0, 0.5); // turn right
      m_robotDrive.arcadeDrive(0.2, 0); //move forward at 0.2 speed
      Timer.delay(2); // run for 2 seconds
      m_robotDrive.stopMotor(); //stop motor
    }
  }

  // reset encoder count 
  @Override
  public void autonomousInit(){
    m_rightEncoder.reset();
  }

  //autonomous: driver has no control; fully automatic
  // Drive forward at speed 0.2 for 2 seconds
  @Override
  public void autonomousPeriodic(){
    m_robotDrive.arcadeDrive(0.2, 0);
    Timer.delay(2);
    m_robotDrive.stopMotor();
  }
}
