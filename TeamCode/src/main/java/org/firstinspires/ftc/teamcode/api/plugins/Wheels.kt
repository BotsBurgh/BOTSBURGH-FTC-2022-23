package org.firstinspires.ftc.teamcode.api.plugins

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.api.plugins.logger.logger
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.Plugin
import kotlin.math.PI
import kotlin.math.sin

private var wheels_store: Wheels? = null

/**
 * A reference to the latest initialized [Wheels] plugin.
 *
 * @throws NullPointerException If accessed before a [Wheels] plugin has been created.
 */
val Context.wheels
    get() = wheels_store!!

private const val MOTOR_1_NAME = "motor1"
private const val MOTOR_2_NAME = "motor2"
private const val MOTOR_3_NAME = "motor3"

const val MOTOR_1_ANGLE: Double = 0.0
const val MOTOR_2_ANGLE: Double = PI * (2.0 / 3.0)
const val MOTOR_3_ANGLE: Double = 2.0 * MOTOR_2_ANGLE

/**
 * A plugin for controlling the three wheels of the robot.
 */
class Wheels : Plugin() {
    init {
        wheels_store = this
    }

    /**
     * One of the robot's wheels.
     *
     * The property will be `null` until the the plugin has been initialized.
     */
    var motor1: DcMotor? = null
        private set

    /**
     * One of the robot's wheels.
     *
     * @see motor1
     */
    var motor2: DcMotor? = null
        private set

    /**
     * One of the robot's wheels.
     *
     * @see motor1
     */
    var motor3: DcMotor? = null
        private set

    /**
     * Initializes the wheel motors.
     *
     * After run, [motor1], [motor2], and [motor3] will be non-null.
     */
    override fun init() {
        this.motor1 = this.ctx.teleop.hardwareMap.get(DcMotor::class.java, MOTOR_1_NAME)
        this.motor2 = this.ctx.teleop.hardwareMap.get(DcMotor::class.java, MOTOR_2_NAME)
        this.motor3 = this.ctx.teleop.hardwareMap.get(DcMotor::class.java, MOTOR_3_NAME)

        this.motor1!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        this.motor2!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        this.motor3!!.mode = DcMotor.RunMode.RUN_USING_ENCODER

        ctx.logger.dataCollector.registerCallback("wheel1 pos") {
            this.motor1!!.currentPosition.toString()
        }

        ctx.logger.dataCollector.registerCallback("wheel2 pos") {
            this.motor2!!.currentPosition.toString()
        }

        ctx.logger.dataCollector.registerCallback("wheel3 pos") {
            this.motor3!!.currentPosition.toString()
        }
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
     * Rotates the robot with the given [power].
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

    /**
     * Calculates the power needed for each of the wheels with a given direction with [radians] and
     * strength with [magnitude]. The strength should be a value between 0 and 1 inclusively.
     */
    fun calculatePower(radians: Double, magnitude: Double): Triple<Double, Double, Double> {
        return Triple(
            magnitude * sin(MOTOR_1_ANGLE - radians),
            magnitude * sin(MOTOR_2_ANGLE - radians),
            magnitude * sin(MOTOR_3_ANGLE - radians),
        )
    }

    /**
     * Stops the wheels and resets their encoder values.
     */
    fun stopAndResetEncoders() {
        ctx.wheels.stop()

        ctx.wheels.motor1!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        ctx.wheels.motor2!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        ctx.wheels.motor3!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER

        ctx.wheels.motor1!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        ctx.wheels.motor2!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        ctx.wheels.motor3!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
    }
}
