package org.firstinspires.ftc.teamcode.api.plugins

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.Plugin
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

private var wheelsExStore: WheelsEx? = null

val Context.wheels_ex
    get() = wheelsExStore!!

private const val ENCODER_RESOLUTION: Double = 537.689839572
private const val WHEEL_CIRCUMFERENCE: Double = 9.6 * PI // In centimeters

/**
 * An extension to the [Wheels] plugin that enables using the encoders to drive to a certain distance.
 */
class WheelsEx : Plugin() {
    init {
        wheelsExStore = this
    }

    /**
     * Stops the wheels and resets their encoder values.
     */
    private fun stopAndResetEncoders() {
        ctx.wheels.stop()

        ctx.wheels.motor1!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        ctx.wheels.motor2!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        ctx.wheels.motor3!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER

        ctx.wheels.motor1!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        ctx.wheels.motor2!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        ctx.wheels.motor3!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
    }

    fun driveEncoderDirection(radians: Double, inches: Double) {
        this.stopAndResetEncoders()

        val wheelVectors = constructWheelVectors(radians, inches)
        val targetPosition = toCartesian(radians, inches)

        val distanceNeeded = Triple(
            -inchesToEncoderTicks(wheelVectors.first.dot(targetPosition)),
            -inchesToEncoderTicks(wheelVectors.second.dot(targetPosition)),
            -inchesToEncoderTicks(wheelVectors.third.dot(targetPosition)),
        )

        val motorPower = ctx.wheels.calculatePower(radians, 0.5)
        val t = ctx.teleop.telemetry

        while (!atLeastTwo(
                abs(distanceNeeded.first - ctx.wheels.motor1!!.currentPosition) < 15,
                abs(distanceNeeded.second - ctx.wheels.motor2!!.currentPosition) < 15,
                abs(distanceNeeded.third - ctx.wheels.motor3!!.currentPosition) < 15,
            ) && ctx.teleop.opModeIsActive()
        ) {
            ctx.wheels.power(motorPower)

            t.addData("Status", "Driving $inches inches at a $radians angle")

            t.addData("1 Current", ctx.wheels.motor1!!.currentPosition)
            t.addData("2 Current", ctx.wheels.motor2!!.currentPosition)
            t.addData("3 Current", ctx.wheels.motor3!!.currentPosition)

            t.addData("1 Target", distanceNeeded.first)
            t.addData("2 Target", distanceNeeded.second)
            t.addData("3 Target", distanceNeeded.third)

            t.update()
        }

        ctx.wheels.stop()
    }

    private fun constructWheelVectors(radians: Double, inches: Double): Triple<Vec2, Vec2, Vec2> {
        val targetPosition = toCartesian(radians, inches)

        fun directionSingle(angle: Double): Vec2 {
            val motorCoord = toCartesian(angle, 1.0)
            val slope = motorCoord.y / motorCoord.x

            var motorDirection = angle - (PI / 2.0)

            if (!isLeftOf(targetPosition, slope)) {
                motorDirection -= PI
            }

            return toCartesian(motorDirection, 1.0)
        }

        return Triple(
            directionSingle(MOTOR_1_ANGLE), directionSingle(MOTOR_2_ANGLE), directionSingle(
                MOTOR_3_ANGLE
            )
        )
    }
}

private data class Vec2(var x: Double, var y: Double) {
    fun dot(rhs: Vec2): Double = this.x * rhs.x + this.y * rhs.y
}

private fun isLeftOf(coordinate: Vec2, slope: Double, beta: Double = 0.0): Boolean {
    // Is positive?
    return if (slope >= 0) {
        coordinate.y >= slope * coordinate.x + beta
    } else {
        coordinate.y <= slope * coordinate.x + beta
    }
}

private fun toCartesian(radians: Double, magnitude: Double): Vec2 = Vec2(
    magnitude * cos(radians),
    magnitude * sin(radians),
)

private fun inchesToEncoderTicks(inches: Double): Double =
    inches * 2.54 / WHEEL_CIRCUMFERENCE * ENCODER_RESOLUTION

// a has to be true, along with b or c, or both b and c must be true.
private fun atLeastTwo(a: Boolean, b: Boolean, c: Boolean): Boolean = if (a) {
    b || c
} else {
    b && c
}
