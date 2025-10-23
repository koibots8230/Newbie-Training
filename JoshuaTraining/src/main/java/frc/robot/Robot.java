// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

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

  SparkMax indexerMotor;
  SparkMax shooterMotor1;
  SparkMax shooterMotor2;

  SparkMaxConfig indexerConfig;

  public Robot() {
    leftMotor1 = new SparkMax(2, MotorType.kBrushless);
    leftMotor2 = new SparkMax(6, MotorType.kBrushless);
    rightMotor1 = new SparkMax(4, MotorType.kBrushless);
    rightMotor2 = new SparkMax(8, MotorType.kBrushless);


    leftConfig = new SparkMaxConfig();

    leftConfig.follow(leftMotor1);


    leftMotor2.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    leftLeadConfig = new SparkMaxConfig();
    
    leftLeadConfig.inverted(true);

    leftMotor1.configure(leftLeadConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    rightConfig = new SparkMaxConfig();

    rightConfig.follow(rightMotor1);

    rightMotor2.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    controller = new XboxController(0);

    distanceSwitch = new DigitalInput(1);

    indexerMotor = new SparkMax(9, MotorType.kBrushless);
    shooterMotor1 = new SparkMax(11, MotorType.kBrushless);
    shooterMotor2 = new SparkMax(13, MotorType.kBrushless);

    indexerConfig = new SparkMaxConfig();

    indexerMotor.configure(indexerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {

  }
  boolean seen = false;

  @Override
  public void teleopPeriodic() {
    // leftMotor1.set(controller.getLeftY());
    // rightMotor1.set(controller.getRightY());

    /*if (controller.getBButton() && controller.getAButton()){
      indexerMotor.set(0);
      shooterMotor1.set(0);
      shooterMotor2.set(0);
    }
    else if (controller.getAButton() && !distanceSwitch.get()){
      indexerMotor.set(.2);
      shooterMotor1.set(-.2);
      shooterMotor2.set(-.2);
    }
    else if (controller.getBButton()){
      indexerMotor.set(-.2);
      shooterMotor1.set(.2);
      shooterMotor2.set(.2);
    } else{
      indexerMotor.set(0);
      shooterMotor1.set(0);
      shooterMotor2.set(0);
    }
*/

/*
    if (controller.getAButton()){
      if (!seen && distanceSwitch.get()){
        indexerMotor.set(.2);
        shooterMotor1.set(.2);
        shooterMotor2.set(.2);
      }
    
     else if (!distanceSwitch.get()){
        seen = true;
       indexerMotor.set(.2);
       shooterMotor1.set(.2);
       shooterMotor2.set(.2);
      }
      else if (seen && distanceSwitch.get()){
        indexerMotor.set(0);
       shooterMotor1.set(0);
       shooterMotor2.set(0);
      }
      else{
       indexerMotor.set(0);
       shooterMotor1.set(0);
       shooterMotor2.set(0);
      }
  } else{
      indexerMotor.set(0);
      shooterMotor1.set(0);
      shooterMotor2.set(0);
  }
*/

    if (distanceSwitch.get() && controller.getAButton() && seen){
      indexerMotor.set(.2);
      shooterMotor1.set(.2);
      shooterMotor2.set(.2);
    }
    else{
      indexerMotor.set(0);
      shooterMotor1.set(0);
      shooterMotor2.set(0);
    }
  }

  @Override
  public void disabledInit() {

  }

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
