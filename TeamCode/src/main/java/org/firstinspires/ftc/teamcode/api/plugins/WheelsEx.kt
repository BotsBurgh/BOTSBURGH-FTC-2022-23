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

private const val MOTOR_1_ANGLE: Double = 0.0
private const val MOTOR_2_ANGLE: Double = PI * (2.0 / 3.0)
private const val MOTOR_3_ANGLE: Double = 2.0 * MOTOR_2_ANGLE

private const val ENCODER_RESOLUTION: Double = 537.689839572
private const val WHEEL_CIRCUMFERENCE: Double = 9.6 * PI // In centimeters

class WheelsEx: Plugin() {
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

        val motorPower = ctx.wheels.calculatePower(radians, 0.5)

        val wheelVectors = constructWheelVectors(radians, inches)
        val targetPosition = toCartesian(radians, inches)

        val distanceNeeded = Triple(
            -inchesToEncoderTicks(wheelVectors.first.dot(targetPosition)),
            -inchesToEncoderTicks(wheelVectors.second.dot(targetPosition)),
            -inchesToEncoderTicks(wheelVectors.third.dot(targetPosition)),
        )

        // TODO: Temporary
        val t = ctx.teleop.telemetry

        t.addData("Status", "Waiting")

        t.addData("1 Current", ctx.wheels.motor1!!.currentPosition)
        t.addData("2 Current", ctx.wheels.motor2!!.currentPosition)
        t.addData("3 Current", ctx.wheels.motor3!!.currentPosition)

        t.addData("1 Target", distanceNeeded.first)
        t.addData("2 Target", distanceNeeded.second)
        t.addData("3 Target", distanceNeeded.third)

        t.update()

        ctx.teleop.sleep(1500)

        while (abs(distanceNeeded.second - ctx.wheels.motor2!!.currentPosition) > 15 && ctx.teleop.opModeIsActive()) {
            ctx.wheels.power(motorPower)

            t.addData("Status", "Running please")

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

        return Triple(directionSingle(MOTOR_1_ANGLE), directionSingle(MOTOR_2_ANGLE), directionSingle(
            MOTOR_3_ANGLE))
    }
}

private data class MutableTriple<A, B, C>(
    var first: A,
    var second: B,
    var third: C,
) {
    override fun toString(): String = "($first, $second, $third)"
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

private fun inchesToEncoderTicks(inches: Double): Double = inches * 2.54 / WHEEL_CIRCUMFERENCE * ENCODER_RESOLUTION