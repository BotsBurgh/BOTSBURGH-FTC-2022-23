package org.firstinspires.ftc.teamcode.opmode.teleop

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.teamcode.api.TriRobot

@TeleOp(name = "TeleOp Main")
class TeleOpMain: LinearOpMode() {
    private var robot: TriRobot = TriRobot(this)

    private var motor1: DcMotor? = null
    private var motor2: DcMotor? = null
    private var motor3: DcMotor? = null

    override fun runOpMode() {
        motor1 = hardwareMap.get(DcMotor::class.java, "motor1")
        motor2 = hardwareMap.get(DcMotor::class.java, "motor2")
        motor3 = hardwareMap.get(DcMotor::class.java, "motor3")

        motor1?.direction = DcMotorSimple.Direction.REVERSE

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        waitForStart()

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running")

            executeJoystick()
            executeRotation()

            telemetry.update()
        }
    }

    private fun executeJoystick() {
        val joyX = gamepad1.left_stick_x.toDouble()
        val joyY = -gamepad1.left_stick_y.toDouble()

        val motorPower = this.robot.motorPowerXY(joyX, joyY)

        motor1?.power = motorPower.first
        motor2?.power = motorPower.second
        motor3?.power = motorPower.third
    }

    private fun executeRotation() {
        if (gamepad1.left_bumper) {
            motor1?.power = 1.0
            motor2?.power = 1.0
            motor3?.power = 1.0
        } else if (gamepad1.right_bumper) {
            motor1?.power = -1.0
            motor2?.power = -1.0
            motor3?.power = -1.0
        }
    }
}
