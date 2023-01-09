package org.firstinspires.ftc.teamcode.api.plugins

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.Plugin
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

private const val ENCODER_RESOLUTION: Double = 537.689839572
private const val WHEEL_CIRCUMFERENCE: Double = 9.6 * PI // In millimeters

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
    override fun init() {
        this.motor1 = this.ctx.teleop.hardwareMap.get(DcMotor::class.java, MOTOR_1_NAME)
        this.motor2 = this.ctx.teleop.hardwareMap.get(DcMotor::class.java, MOTOR_2_NAME)
        this.motor3 = this.ctx.teleop.hardwareMap.get(DcMotor::class.java, MOTOR_3_NAME)

        this.motor1!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        this.motor2!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        this.motor3!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
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

    fun resetEncoders() {
        this.motor1!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        this.motor2!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        this.motor3!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER

        this.motor1!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        this.motor2!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        this.motor3!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
    }

    fun calculatePower(radians: Double, magnitude: Double): Triple<Double, Double, Double> = Triple(
        magnitude * sin(MOTOR_1_ANGLE - radians),
        magnitude * sin(MOTOR_2_ANGLE - radians),
        magnitude * sin(MOTOR_3_ANGLE - radians),
    )

    fun driveEncoderDirection(radians: Double, inches: Double) {
        // Inches -> cm -> mm -> rotations -> encoder ticks
        val distance = (inches * 2.54 * 10.0) / WHEEL_CIRCUMFERENCE * ENCODER_RESOLUTION
        val motorPower = this.calculatePower(radians, 1.0)

        val singleMotorPower: Double
        val singleMotor: DcMotor

        if (abs(motorPower.first) >= abs(motorPower.second) && abs(motorPower.first) >= abs(motorPower.third)) {
            singleMotorPower = abs(motorPower.first)
            singleMotor = ctx.wheels.motor1!!
        } else if (abs(motorPower.second) >= abs(motorPower.third)) {
            singleMotorPower = abs(motorPower.second)
            singleMotor = ctx.wheels.motor2!!
        } else {
            singleMotorPower = abs(motorPower.third)
            singleMotor = ctx.wheels.motor3!!
        }

        val singleMotorDistance = distance * singleMotorPower

        // TODO: Temporary
        val telemetry = ctx.teleop.telemetry

        this.resetEncoders()

        while (singleMotor!!.currentPosition < singleMotorDistance && ctx.teleop.opModeIsActive()) {
            this.powerDirection(radians, singleMotorPower)

            telemetry.addData("Radians", radians)
            telemetry.addData("Inches", inches)
            telemetry.addData("Total Distance", distance)
            telemetry.addData("Current Distance", singleMotor.currentPosition)
            telemetry.addData("Motor Power", motorPower)
            telemetry.addData("Single Motor Power", singleMotorPower)
            telemetry.addData("Single Motor Distance", singleMotorDistance)

            telemetry.update()
        }

        this.stop()
    }
}
