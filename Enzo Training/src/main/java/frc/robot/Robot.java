// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.fasterxml.jackson.databind.ser.impl.IndexedStringListSerializer;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.epilogue.*;
  

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */

 @Logged
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  XboxController controller;
  SparkMax leftMotor1;
  SparkMax leftMotor2;
  SparkMax rightMotor1;
  SparkMax rightMotor2;
  SparkMaxConfig leftConfig;
  SparkMaxConfig rightConfig;
  SparkMaxConfig leftLeadConfig;
  DigitalInput distanceSwitch;
  SparkMax shooterLead;
  SparkMaxConfig indexerConfig;
  SparkMax shooterFollow;
  SparkMaxConfig shooterFollowConfig;
  SparkMax indexer;
  double changeTheFuckingVariableOwO = 0.1;
  SparkClosedLoopController whatTheActualFuckDoesThisDoJake;
  
  public Robot() {
    shooterLead = new SparkMax(11, MotorType.kBrushless);
    shooterFollow = new SparkMax(13, MotorType.kBrushless);
    shooterFollowConfig = new SparkMaxConfig();
    shooterFollowConfig.follow(shooterLead);
    shooterFollow.configure(shooterFollowConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    indexer = new SparkMax(9, MotorType.kBrushless);
    indexerConfig = new SparkMaxConfig();
    controller = new XboxController(0);
    leftMotor1 = new SparkMax(2, MotorType.kBrushless);
    leftMotor2 = new SparkMax(6, MotorType.kBrushless);
    rightMotor1 = new SparkMax(4, MotorType.kBrushless);
    rightMotor2 = new SparkMax(8, MotorType.kBrushless);
    leftConfig = new SparkMaxConfig();
    leftConfig.follow(leftMotor1);
    leftMotor2.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightConfig = new SparkMaxConfig();
    rightConfig.follow(rightMotor1);
    rightMotor2.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    leftLeadConfig = new SparkMaxConfig();
    leftLeadConfig.inverted(true);
    leftMotor1.configure(leftLeadConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    distanceSwitch = new DigitalInput(1);
    whatTheActualFuckDoesThisDoJake = indexer.getClosedLoopController();
    indexerConfig.closedLoop.p(changeTheFuckingVariableOwO);
    indexerConfig.closedloop.velocityff(0);
    Epilogue.bind(this);
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
  boolean isInShooter = false;
  boolean isShot = false;
  boolean NewVariableThatJakeHasNoClueWhatTheFunctionOfIs = false; // !isOutOfIndexer
  double i;
  boolean hasBeenInIndexerSinceLastShot = false;
  
  @Override
  public void teleopPeriodic() {
    // leftMotor1.set(controller.getLeftY());
    // rightMotor1.set(controller.getRightY());
    /*
    if(distanceSwitch.get()&&controller.getAButton()){
      shooterLead.set(0.2);
      indexer.set(0.2);
    }
    else{
      shooterLead.set(0);
      indexer.set(0);
    }
    
    */
    whatTheActualFuckDoesThisDoJake.setReference(0.5, ControlType.kVelocity);
    // first i need to check if it is currently in the indexer
    // if it is in the indexer, dont do anything with  the shooter
    // if it is not currently in the indexer then i need to see if one has been in the indexer since the last shot.
    // if it has been in the indexer since the last shot, then i allow it to shoot on pressing a
    // if it has not been in the indexer since the last shot, then i do not allow it to shoot on pressing a
    // if it shoots, then i need to reset the variable that says if it has been in the indexer since the last shot.
    
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
