
package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

import monologue.Logged;
import monologue.Monologue;
import monologue.Annotations.Log;

public class Robot extends TimedRobot implements Logged {

XboxController controller; 
CANSparkMax motor;

CANSparkMax motorLeftFront;
CANSparkMax motorLeftBack;
CANSparkMax motorRightFront;
CANSparkMax motorRightBack;

CANSparkMax intake;
CANSparkMax indexss;

double maximum;
double difference;
double total;

RelativeEncoder encoder;
@Log double velocity; 


DigitalInput indexSensor;

SparkPIDController pidController;


  @Override
  public void robotInit() {
    controller = new XboxController(0);

    motorLeftFront = new CANSparkMax(6, MotorType.kBrushless);
    motorLeftBack = new CANSparkMax(4, MotorType.kBrushless);
    motorRightFront = new CANSparkMax(7, MotorType.kBrushless);
    motorRightBack = new CANSparkMax(2, MotorType.kBrushless);
    
    intake = new CANSparkMax(1, MotorType.kBrushless);
    indexss = new CANSparkMax(3, MotorType.kBrushless);

    //motorLeftBack.follow(motorLeftFront);
    motorRightBack.follow(motorRightFront);

    Monologue.setupMonologue(this, "Robot", false, false);

    pidController = motorLeftBack.getPIDController();
    pidController.setFF(0);
    pidController.setP(0);



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
        motorLeftFront.set(maximum);
        motorRightFront.set(difference);
      }
      else {
        motorLeftFront.set(total);
        motorRightFront.set(maximum);
      }
    }
    else {
      if (controller.getLeftX() >=0 ) {
        motorLeftFront.set(total);
        motorRightFront.set(-maximum);
      }
      else {
        motorLeftFront.set(-maximum);
        motorRightFront.set(difference);
      }
    }

    if (controller.getBButton() && indexSensor.get()) { 
      intake.set(.5);
      indexss.set(.5);
    } else {
      intake.set(0);
      indexss.set(0);
      
    }
    if (controller.getXButton()) {
      pidController.setReference(400, ControlType.kVelocity);
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