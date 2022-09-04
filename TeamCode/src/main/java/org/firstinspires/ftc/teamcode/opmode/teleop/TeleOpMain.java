package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Main")
public class TeleOpMain extends LinearOpMode {
  private DcMotor motor1, motor2, motor3;
  
  private double motor1Angle = 0.0;
  private double motor2Angle = Math.PI * (2.0 / 3.0);
  private double motor3Angle = 2.0 * Math.PI * (2.0 / 3.0);
  
  @Override
  public void runOpMode() {
    motor1 = hardwareMap.get(DcMotor.class, "motor1");
    motor2 = hardwareMap.get(DcMotor.class, "motor2");
    motor3 = hardwareMap.get(DcMotor.class, "motor3");
    
    motor1.setDirection(DcMotorSimple.Direction.REVERSE);

    telemetry.addData("Status", "Initialized");
    telemetry.update();
  
    waitForStart();
    
    while (opModeIsActive()) {
      telemetry.addData("Status", "Running");

      executeJoystick();
      executeRotation();
      
      telemetry.update();
    }
  }
  
  private void executeJoystick() {
    double joyX = gamepad1.left_stick_x;
    double joyY = -gamepad1.left_stick_y;

    telemetry.addData("Joy X", joyX);
    telemetry.addData("Joy Y", joyY);

    double joyRad = Math.atan2(joyY, joyX) - (Math.PI / 2.0);
    double joyMag = Math.sqrt(joyY * joyY + joyX * joyX);
      
    telemetry.addData("Joy Rad", joyRad);
    telemetry.addData("Joy Mag", joyMag);
      
    // Mag normalization?
      
    double motor1Speed = joyMag * Math.sin(motor1Angle - joyRad);
    double motor2Speed = joyMag * Math.sin(motor2Angle - joyRad);
    double motor3Speed = joyMag * Math.sin(motor3Angle - joyRad);

    telemetry.addData("Motor 1", motor1Speed);
    telemetry.addData("Motor 2", motor2Speed);
    telemetry.addData("Motor 3", motor3Speed);
      
    motor1.setPower(motor1Speed);
    motor2.setPower(motor2Speed);
    motor3.setPower(motor3Speed);
  }
  
  private void executeRotation() {
    if (gamepad1.left_bumper) {
      motor1.setPower(1);
      motor2.setPower(1);
      motor3.setPower(1);
    } else if (gamepad1.right_bumper) {
      motor1.setPower(-1);
      motor2.setPower(-1);
      motor3.setPower(-1);
    }
  }
}
