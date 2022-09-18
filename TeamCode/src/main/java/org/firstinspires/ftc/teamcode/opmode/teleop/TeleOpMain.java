package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.api.TriRobot;

import kotlin.Triple;

@TeleOp(name = "Main")
public class TeleOpMain extends LinearOpMode {
    private TriRobot robot = new TriRobot(this);
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

        Triple<Double, Double, Double> motorPower = this.robot.motorPowerXY(joyX, joyY);

        telemetry.addData("Motor 1", motorPower.component1());
        telemetry.addData("Motor 2", motorPower.component2());
        telemetry.addData("Motor 3", motorPower.component3());

        motor1.setPower(motorPower.component1());
        motor2.setPower(motorPower.component2());
        motor3.setPower(motorPower.component3());
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
