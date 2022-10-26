package org.firstinspires.ftc.teamcode.api.components

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.teamcode.api.arch.Component
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.OpMode
import kotlin.math.*

private const val motor1Angle: Double = 0.0
private const val motor2Angle: Double = PI * (2.0 / 3.0)
private const val motor3Angle: Double = 2.0 * motor2Angle

class Wheels: Component() {
    private var motor1: DcMotor? = null
    private var motor2: DcMotor? = null
    private var motor3: DcMotor? = null

    override val opmode = OpMode.TeleOp

    override val pre = fun(ctx: Context) {
        this.motor1 = ctx.teleop.hardwareMap.get(DcMotor::class.java, "motor1")
        this.motor2 = ctx.teleop.hardwareMap.get(DcMotor::class.java, "motor2")
        this.motor3 = ctx.teleop.hardwareMap.get(DcMotor::class.java, "motor3")
    }

    override val cycle = fun(ctx: Context) {
        val joyX = ctx.teleop.gamepad1.left_stick_x.toDouble()
        val joyY = ctx.teleop.gamepad1.left_stick_y.toDouble()

        // Flip x-axis
        var motorPower = this.motorPowerXY(-joyX, joyY)

        if (ctx.teleop.gamepad1.left_bumper) {
            motorPower = Triple(1.0, 1.0, 1.0)
        } else if (ctx.teleop.gamepad1.right_bumper) {
            motorPower = Triple(-1.0, -1.0, -1.0)
        }

        this.motor1?.power = motorPower.first
        this.motor2?.power = motorPower.second
        this.motor3?.power = motorPower.third
    }

    private fun motorPower(radians: Double, magnitude: Double): Triple<Double, Double, Double> = Triple(
        magnitude * sin(motor1Angle - radians),
        magnitude * sin(motor2Angle - radians),
        magnitude * sin(motor3Angle - radians),
    )

    private fun motorPowerXY(x: Double, y: Double): Triple<Double, Double, Double> = motorPower(
        atan2(y, x) - (PI / 2.0),
        sqrt(y * y + x * x),
    )
}
