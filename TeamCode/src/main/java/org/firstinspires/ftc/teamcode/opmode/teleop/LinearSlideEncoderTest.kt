package org.firstinspires.ftc.teamcode.opmode.teleop

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor

@TeleOp(name = "Linear Slide Encoder Test")
@Disabled
class LinearSlideEncoderTest : LinearOpMode() {
    override fun runOpMode() {
        val linearSlide1 = hardwareMap.get(DcMotor::class.java, "linearSlide1")
        linearSlide1.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        waitForStart()

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running")
            telemetry.addData("Motor 1", linearSlide1.currentPosition)

            if (gamepad1.a) {
                // DOwn
                linearSlide1.power = 0.3
            } else if (gamepad1.b) {
                // Up
                linearSlide1.power = -0.4
            } else {
                linearSlide1.power = 0.0
            }

            telemetry.update()
        }
    }
}
