// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  XboxController controller;
  CANSparkMax motorleft1;
  CANSparkMax motorleft2;
  CANSparkMax motorright1;
  CANSparkMax motorright2;
  
  double maximum;
  double difference;
  double total;

  CANSparkMax Intake1;
  CANSparkMax Intake2;
  CANSparkMax Indexer;

  DigitalInput distanceswitch = new DigitalInput(0);



  @Override
  public void robotInit() {
    controller = new XboxController(0);
    motorleft1 = new CANSparkMax(999, MotorType.kBrushless);
    motorleft2 = new CANSparkMax(999, MotorType.kBrushless);
    motorright1 = new CANSparkMax(2, MotorType.kBrushless);
    motorright2 = new CANSparkMax(7, MotorType.kBrushless);
    motorleft2.follow(motorleft1, true);
    motorright2.follow(motorright1, true);
    motorleft1.setInverted(true);
    motorright1.setInverted(true);
    
    Intake1 = new CANSparkMax(4, MotorType.kBrushless);
    Intake2 = new CANSparkMax(15, MotorType.kBrushless);
    Intake2.follow(Intake1, true);

    Indexer = new CANSparkMax(6, MotorType.kBrushless);

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
    motorleft1.set(controller.getLeftY());
    motorright1.set(controller.getRightY());
    maximum = Math.max(Math.abs(controller.getLeftY()), Math.abs(controller.getRightY()));
    total = controller.getLeftY() + controller.getRightY();
    difference = controller.getLeftY() - controller.getRightY();
    controller.getRightY();
    if (controller.getLeftY() >= 0) {
      if (controller.getRightY() >= 0){
        motorleft1.set(maximum);
        motorright1.set(difference);
      }
    }
        else{
        motorleft1.set(total);
        motorright1.set(maximum);
        if (controller.getRightY() >= 0){
          motorleft1.set(total);
          motorright1.set(-maximum);
      }   else{
        motorleft1.set(-maximum);
        motorright1.set(difference);
      }
    }
    
    distanceswitch.get();
    if (controller.getAButton()) {
      Intake1.set(.5);
      Indexer.set(.5);
    } else if (!distanceswitch.get()){
      Intake1.set(0);   
      Indexer.set(0);
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
