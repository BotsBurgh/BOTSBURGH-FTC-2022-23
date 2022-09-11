package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.api.Robot;
import org.firstinspires.ftc.teamcode.api.TripleTuple;

@TeleOp(name = "Main")
public class TeleOpMain extends LinearOpMode {
  private Robot robot = new Robot(this);
  private DcMotor motor1, motor2, motor3;
  
  @Override
  public void runOpMode() {
    motor1 = hardwareMap.get(DcMotor.class, "motor1");
    motor2 = hardwareMap.get(DcMotor.class, "motor2");
    motor3 = hardwareMap.get(DcMotor.class, "motor3");

    // Motor 1 is reversed for some reason
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
    // Negate joyY so up goes forward
    double joyY = -gamepad1.left_stick_y;

    telemetry.addData("Joy X", joyX);
    telemetry.addData("Joy Y", joyY);

    TripleTuple motorPower = Robot.motorPowerXY(joyX, joyY);

    telemetry.addData("Motor 1", motorPower.one);
    telemetry.addData("Motor 2", motorPower.two);
    telemetry.addData("Motor 3", motorPower.three);
      
    motor1.setPower(motorPower.one);
    motor2.setPower(motorPower.two);
    motor3.setPower(motorPower.three);
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
