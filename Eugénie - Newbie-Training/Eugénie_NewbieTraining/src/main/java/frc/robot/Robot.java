// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.epilogue.*;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

@Logged
/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

SparkMax leftMotor1;
SparkMax leftMotor2;
SparkMax rightMotor1;
SparkMax rightMotor2;

SparkMaxConfig leftConfig;
SparkMaxConfig leftLeadConfig;
SparkMaxConfig rightConfig;

XboxController controller;

DigitalInput distanceSwitch;
SparkMax shooter1;
SparkMax shooter2;
SparkMax indexer;

SparkMaxConfig tuning;

boolean previouslyDetected = false;


  public Robot() {
    leftMotor1 = new SparkMax(2, MotorType.kBrushless);
    leftMotor2 = new SparkMax(6, MotorType.kBrushless);
    rightMotor1 = new SparkMax(4, MotorType.kBrushless);
    rightMotor2 = new SparkMax(8, MotorType.kBrushless);

    shooter1 = new SparkMax(51, MotorType.kBrushless);
    shooter2 = new SparkMax(13, MotorType.kBrushless);
    indexer = new SparkMax(9, MotorType.kBrushless);

    leftConfig = new SparkMaxConfig();
    rightConfig = new SparkMaxConfig();

    leftLeadConfig = new SparkMaxConfig();

    leftMotor1.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    leftConfig.follow(leftMotor1);
    rightConfig.follow(rightMotor1);
    leftLeadConfig.inverted(true);

    leftMotor2.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightMotor2.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
    controller = new XboxController(0);

    distanceSwitch = new DigitalInput(1);

    distanceSwitch.get();

    tuning.closedLoop.p(0);
    tuning.closedLoop.velocityFF(0);
    shooter1.configure(tuning, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    shooter1.getClosedLoopController();
    
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
    // leftMotor1.set(controller.getLeftY());
    // rightMotor1.set(controller.getRightY());


    if (distanceSwitch.get() && controller.getAButton())  {
      shooter1.set(0.2);
      shooter2.set(0.2);
      indexer.set(0.2);
    }

    if (!distanceSwitch.get()){
      previouslyDetected = true;
    }

    if (previouslyDetected == true && !distanceSwitch.get()) {
      shooter1.set(0.2);
      shooter2.set(0.2);
      indexer.set(0.2);
    }

    if (previouslyDetected == true && distanceSwitch.get()) {
      shooter1.set(0);
      shooter2.set(0);
      indexer.set(0);
    }

    else if (!distanceSwitch.get() || controller.getAButtonReleased()) {
      shooter1.set(0);
      shooter2.set(0);
      indexer.set(0);
    }

    Epilogue.bind(this);
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
