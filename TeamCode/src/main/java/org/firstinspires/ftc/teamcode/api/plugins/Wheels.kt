package org.firstinspires.ftc.teamcode.api.plugins

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.Plugin
import kotlin.math.*

private val wheels_store = Wheels()

val Context.wheels
    get() = wheels_store

private const val MOTOR_1_NAME = "motor1"
private const val MOTOR_2_NAME = "motor2"
private const val MOTOR_3_NAME = "motor3"

private const val MOTOR_1_ANGLE: Double = 0.0
private const val MOTOR_2_ANGLE: Double = PI * (2.0 / 3.0)
private const val MOTOR_3_ANGLE: Double = 2.0 * MOTOR_2_ANGLE

class Wheels: Plugin() {
    private var motor1: DcMotor? = null
    private var motor2: DcMotor? = null
    private var motor3: DcMotor? = null

    /**
     * Initializes the motors from the given hardwareMap.
     */
    fun init() {
        this.motor1 = this.ctx.teleop.hardwareMap.get(DcMotor::class.java, MOTOR_1_NAME)
        this.motor2 = this.ctx.teleop.hardwareMap.get(DcMotor::class.java, MOTOR_2_NAME)
        this.motor3 = this.ctx.teleop.hardwareMap.get(DcMotor::class.java, MOTOR_3_NAME)
    }

    /**
     * Sets the power of the motors based on an angle and strength.
     *
     * @param radians The angle (in radians) the robot should move towards.
     * @param magnitude The strength (how fast) the robot should move.
     */
    fun powerDirection(radians: Double, magnitude: Double) {
        val power = calculatePower(radians, magnitude)

        // TODO: see if `?` can be changed with `!!`
        this.motor1?.power = power.first
        this.motor2?.power = power.second
        this.motor3?.power = power.third
    }

    /**
     * Rotates the robot with the given power.
     *
     * @param power The power to apply to each motor to affect how fast it spins.
     */
    fun powerRotation(power: Double) {
        this.motor1?.power = power
        this.motor2?.power = power
        this.motor3?.power = power
    }

    /**
     * Shorthand to setting power to 0, or stopping the wheels from moving.
     */
    fun stop() {
        this.motor1?.power = 0.0
        this.motor2?.power = 0.0
        this.motor3?.power = 0.0
    }

    private fun calculatePower(radians: Double, magnitude: Double): Triple<Double, Double, Double> = Triple(
        magnitude * sin(MOTOR_1_ANGLE - radians),
        magnitude * sin(MOTOR_2_ANGLE - radians),
        magnitude * sin(MOTOR_3_ANGLE - radians),
    )
}
