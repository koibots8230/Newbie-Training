// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.lang.model.util.ElementScanner14;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import monologue.Logged;
import monologue.Monologue;
import monologue.Annotations.Log;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot implements Logged{ 
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  
  XboxController controller;
  CANSparkMax leadLeft;
  CANSparkMax leadRight;
  CANSparkMax backLeft;
  CANSparkMax backRight;
  double maximum;
  double difference;
  double total;
  CANSparkMax intake1;
  CANSparkMax intake2;
  CANSparkMax indexer;
  DigitalInput DistanceSwitch;
  int stage;
  CANSparkMax Shooter1;
  CANSparkMax Shooter2;
  @Log double velocity;
  RelativeEncoder encoder;
  SparkPIDController pidController;

   @Override
  public void robotInit() {
    controller = new XboxController(0);
    leadLeft = new CANSparkMax(999, MotorType.kBrushless);
    leadRight = new CANSparkMax(998, MotorType.kBrushless);
    backLeft = new CANSparkMax(997, MotorType.kBrushless);
    backRight = new CANSparkMax(996, MotorType.kBrushless);
    leadLeft.setInverted(true);
    leadRight.setInverted(true);
    //backLeft.follow(leadLeft);
    backRight.follow(leadRight);
    intake1 = new CANSparkMax(14, MotorType.kBrushless);
    intake2 = new CANSparkMax(15, MotorType.kBrushless);
    indexer = new CANSparkMax(6, MotorType.kBrushless);
    intake2.follow(intake1);
    DistanceSwitch = new DigitalInput( 1);
    indexer.setInverted(true);
    System.out.println("RobotInit successfull");
    stage = 1;
    Shooter1 = new CANSparkMax(7, MotorType.kBrushless);
    Shooter2 = new CANSparkMax(995, MotorType.kBrushless);
  
    Monologue.setupMonologue(this,"Robot", false, false);
    encoder = leadLeft.getEncoder();

    pidController.setP(0);
    pidController.setD(0);
  }

  @Override
  public void robotPeriodic() {
    Monologue.updateAll();
    velocity = encoder.getVelocity();
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    maximum = Math.max(Math.abs(controller.getRightY()), Math.abs(controller.getLeftX()));
    difference = controller.getRightY() - controller.getLeftX();
    total = controller.getRightY() + controller.getLeftX();
    if (controller.getRightY() >= 0) {
      if (controller.getLeftX() >= 0) {
        leadLeft.set(maximum);
        leadRight.set(difference);
      } else {
        leadLeft.set(total);
        leadRight.set(maximum);
      }
    } else {
      if (controller.getRightX() >= 0) {
        leadLeft.set(total);
        leadRight.set(-maximum);
      } else {
        leadLeft.set(-maximum);
        leadRight.set(difference);
      }

      
    }
    //System.out.println("before If");
    //if (controller.getAButton()){
      //System.out.println("A button trigger");
    //}
    //if (DistanceSwitch.get()) {
      //System.out.println("Distance Switch trigger");
    //}
    if (controller.getAButton() && DistanceSwitch.get()) {
      System.out.println("Success!!!");
      intake1.set(-0.1);
      indexer.set(0.1);
    } else {
      System.out.println("off");
      intake1.set(0);
      indexer.set(0);
    }
    //System.out.println("After If");
    if (controller.getBButton()) {
      if (stage == 1) {
        if (DistanceSwitch.get()) {
          Shooter1.set(-0.1);
          Shooter2.set(-0.1);
          indexer.set(-0.1);
        } else {
          stage = 2;
        }
      } else if (stage == 2) {
        if (!DistanceSwitch.get()) {
          Shooter1.set(-0.1);
          Shooter2.set(-0.1);
          indexer.set(-0.1);
        } else {
          stage = 3;
        } 
      } else if (stage == 3) {
        if (DistanceSwitch.get()) {
          Shooter1.set(0.1);
          Shooter2.set(0.1);
          indexer.set(0.1);
        } else {
          Shooter1.set(0);
          Shooter2.set(0);
          indexer.set(0);
        }
      }
    } else {
      Shooter1.set(0);
      Shooter2.set(0);
      indexer.set(0);
    }
    if (controller.getXButton()) {
      pidController.setReference(2000, ControlType.kVelocity);
    } else {
      pidController.setReference(0, ControlType.kVelocity);
    }
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
