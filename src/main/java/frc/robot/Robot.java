// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
import edu.wpi.first.wpilibj.SerialPort;

public class Robot extends TimedRobot {
  // private final DifferentialDrive m_robotDrive =
  //     new DifferentialDrive(new PWMSparkMax(0), new PWMSparkMax(1));
  // private final Joystick m_stick = new Joystick(0);
  private final Timer m_timer = new Timer();
  private static final SerialPort.Port SERIAL_PORT_PORT = SerialPort.Port.kOnboard; // port on the roborio
  private SerialPort serialPort = new SerialPort(9600, SERIAL_PORT_PORT); //1 is a placeholder, uses the onboard i2c/serial port
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {}

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    // if (m_timer.get() < 2.0) {
    //   m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
    // } else {
    //   m_robotDrive.stopMotor(); // stop robot
   // }
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {
    m_timer.start();
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    //m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
   //serialPort.writeString("Sending");
   // System.out.println("TESTINGCOLORSENSOR");
   if (m_timer.get() > 5) {
    serialPort.write(new byte[] {0x12}, 1);
    System.out.println("Wrote to aurduino");
    m_timer.reset();
    
  }
  if(serialPort.getBytesReceived()>0){
    //System.out.println(serialPort.getBytesReceived());
    //System.out.println(serialPort.readString());
    System.out.println("received");
    byte[] data = new byte[3];
    data=serialPort.read(3);
    System.out.println(data[0]);
    System.out.println(data[1]);
    System.out.println(data[2]);
  }
    // System.out.println(serialPort.read(3));
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
