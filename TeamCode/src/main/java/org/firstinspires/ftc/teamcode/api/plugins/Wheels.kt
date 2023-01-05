package org.firstinspires.ftc.teamcode.api.plugins

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.arch.Context
import org.firstinspires.ftc.teamcode.arch.Plugin
import kotlin.math.*

private var wheels_store: Wheels? = null

val Context.wheels
    get() = wheels_store!!

private const val MOTOR_1_NAME = "motor1"
private const val MOTOR_2_NAME = "motor2"
private const val MOTOR_3_NAME = "motor3"

private const val MOTOR_1_ANGLE: Double = 0.0
private const val MOTOR_2_ANGLE: Double = PI * (2.0 / 3.0)
private const val MOTOR_3_ANGLE: Double = 2.0 * MOTOR_2_ANGLE

/**
 * A plugin for controlling the three wheels of the robot.
 */
class Wheels: Plugin() {
    init {
        wheels_store = this
    }

    var motor1: DcMotor? = null
        private set
    var motor2: DcMotor? = null
        private set
    var motor3: DcMotor? = null
        private set

    /**
     * Initializes the motors from the given hardwareMap.
     */
    fun init() {
        this.motor1 = this.ctx.teleop.hardwareMap.get(DcMotor::class.java, MOTOR_1_NAME)
        this.motor2 = this.ctx.teleop.hardwareMap.get(DcMotor::class.java, MOTOR_2_NAME)
        this.motor3 = this.ctx.teleop.hardwareMap.get(DcMotor::class.java, MOTOR_3_NAME)
    }

    /**
     * Sets the power of all motors.
     *
     * @param power1 The power to apply [motor1].
     * @param power2 The power to apply [motor2].
     * @param power3 The power to apply [motor3].
     */
    fun power(power1: Double, power2: Double, power3: Double) {
        this.motor1?.power = power1
        this.motor2?.power = power2
        this.motor3?.power = power3
    }

    /**
     * Sets the power of all motors.
     *
     * @param power A [Triple] of [Double]s representing the power of each motor.
     */
    fun power(power: Triple<Double, Double, Double>) {
        this.power(power.first, power.second, power.third)
    }

    /**
     * Sets the power of all motors.
     *
     * @param power A [Double] to apply to each motor.
     */
    fun power(power: Double) {
        this.power(power, power, power)
    }

    /**
     * Sets the power of the motors based on an angle and strength.
     *
     * @param radians The angle (in radians) the robot should move towards.
     * @param magnitude The strength (how fast) the robot should move.
     */
    fun powerDirection(radians: Double, magnitude: Double) {
        val power = calculatePower(radians, magnitude)
        this.power(power)
    }

    /**
     * Rotates the robot with the given power.
     *
     * @param power The power to apply to each motor to affect how fast it spins.
     */
    fun powerRotation(power: Double) {
        this.power(power)
    }

    /**
     * Shorthand to setting power to 0, or stopping the wheels from moving.
     */
    fun stop() {
        this.power(0.0)
    }

    fun calculatePower(radians: Double, magnitude: Double): Triple<Double, Double, Double> = Triple(
        magnitude * sin(MOTOR_1_ANGLE - radians),
        magnitude * sin(MOTOR_2_ANGLE - radians),
        magnitude * sin(MOTOR_3_ANGLE - radians),
    )
}
