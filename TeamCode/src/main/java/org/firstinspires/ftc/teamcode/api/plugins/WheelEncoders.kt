package org.firstinspires.ftc.teamcode.api.plugins

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.Plugin

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.round

private var wheelsEncoderStore: WheelEncoders? = null

val Context.wheel_encoders
    get() = wheelsEncoderStore!!

@Config
object WheelEncodersConfig {
    @JvmField
    var TICKS_PER_INCH: Int = 39

    @JvmField
    var TICK_PER_DEGREE: Double = 6.5
}


class WheelEncoders : Plugin() {
    var wheelOne: DcMotor? = null
    var wheelTwo: DcMotor? = null
    var wheelThree: DcMotor? = null

    var wheelFinalDistanceOne: Double? = null
    var wheelFinalDistanceTwo: Double? = null
    var wheelFinalDistanceThree: Double? = null

    var tick: Double? = null

    init {
        wheelsEncoderStore = this
    }


    private fun countPerInch(inches: Double) {
        tick = WheelEncodersConfig.TICKS_PER_INCH * inches
        round(tick!!)
    }

    private fun countPerDegree(degrees: Double) {
        tick = WheelEncodersConfig.TICK_PER_DEGREE * degrees
        round(tick!!)
    }

    /**
     * Rotates the robot a certain degree and power using encoder values
     */
    fun moveDirection(front: Double, inches: Double, power: Double) {
        //three fronts are PI (camera), 7 * PI / 4 (Slide 1), and Pi / 4 (Slide 2)
        when (front) {
            PI -> {
                wheelOne = ctx.wheels.motor2
                wheelTwo = ctx.wheels.motor3
                wheelThree = ctx.wheels.motor1
                wheelOne!!.direction = DcMotorSimple.Direction.REVERSE
                wheelOne!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
                wheelTwo!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
                wheelThree!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
            }
            7 * PI / 4 -> {
                wheelOne = ctx.wheels.motor3
                wheelTwo = ctx.wheels.motor1
                wheelThree = ctx.wheels.motor2
                wheelOne!!.direction = DcMotorSimple.Direction.REVERSE
                wheelOne!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
                wheelTwo!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
                wheelThree!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
            }
            PI / 4 -> {
                wheelOne = ctx.wheels.motor1
                wheelTwo = ctx.wheels.motor2
            }
        }

        ctx.wheels.stopAndResetEncoders()

        if (power > 0) {
            countPerInch(inches)
            wheelFinalDistanceOne = wheelOne!!.currentPosition + tick!!
            wheelFinalDistanceTwo = wheelTwo!!.currentPosition + tick!!
            wheelFinalDistanceThree = 0.0
        } else if (power < 0) {
            countPerInch(inches)
            wheelFinalDistanceOne = wheelOne!!.currentPosition - tick!!
            wheelFinalDistanceTwo = wheelTwo!!.currentPosition - tick!!
            wheelFinalDistanceThree = 0.0
        }

        wheelOne!!.targetPosition = wheelFinalDistanceOne!!.toInt()
        wheelTwo!!.targetPosition = wheelFinalDistanceTwo!!.toInt()
        wheelThree!!.targetPosition = wheelFinalDistanceThree!!.toInt()

        if (power > 0) {
            while (wheelOne!!.currentPosition <= wheelFinalDistanceOne!! || wheelTwo!!.currentPosition <= wheelFinalDistanceTwo!! && ctx.teleop.opModeIsActive()) {
                ctx.teleop.telemetry.addData("Wheel One Current", wheelOne!!.currentPosition)
                ctx.teleop.telemetry.addData("Wheel One Final", wheelFinalDistanceOne)
                ctx.teleop.telemetry.addData("Wheel Two Current", wheelTwo!!.currentPosition)
                ctx.teleop.telemetry.addData("Wheel Two Final", wheelFinalDistanceTwo)
                ctx.teleop.telemetry.addData("Wheel Three Current", wheelThree!!.currentPosition)
                ctx.teleop.telemetry.addData("Wheel Three Final", wheelFinalDistanceThree)
                ctx.teleop.telemetry.update()

                if (wheelOne!!.currentPosition <= wheelFinalDistanceOne!!) {
                    wheelOne!!.power = power
                }

                if (wheelTwo!!.currentPosition <= wheelFinalDistanceTwo!!) {
                    wheelTwo!!.power = power
                }

            }

            ctx.wheels.stopAndResetEncoders()

        } else if (power < 0) {
            while (wheelOne!!.currentPosition >= wheelFinalDistanceOne!! || wheelTwo!!.currentPosition >= wheelFinalDistanceTwo!! && ctx.teleop.opModeIsActive()) {
                ctx.teleop.telemetry.addData("Wheel One Current", wheelOne!!.currentPosition)
                ctx.teleop.telemetry.addData("Wheel One Final", wheelFinalDistanceOne)
                ctx.teleop.telemetry.addData("Wheel Two Current", wheelTwo!!.currentPosition)
                ctx.teleop.telemetry.addData("Wheel Two Final", wheelFinalDistanceTwo)
                ctx.teleop.telemetry.addData("Wheel Three Current", wheelThree!!.currentPosition)
                ctx.teleop.telemetry.addData("Wheel Three Final", wheelFinalDistanceThree)
                ctx.teleop.telemetry.update()

                if (wheelOne!!.currentPosition >= wheelFinalDistanceOne!!) {
                    wheelOne!!.power = power
                }

                if (wheelTwo!!.currentPosition >= wheelFinalDistanceTwo!!) {
                    wheelTwo!!.power = power
                }

            }

            ctx.wheels.stopAndResetEncoders()
        }

    }

    /**
     * Rotates the robot a certain degree and power using encoder values
     */
    fun wheelEncoderSpin(degrees: Double, power: Double) {
        wheelOne = ctx.wheels.motor1
        wheelTwo = ctx.wheels.motor2
        wheelThree = ctx.wheels.motor3
        wheelOne!!.direction = DcMotorSimple.Direction.FORWARD
        wheelTwo!!.direction = DcMotorSimple.Direction.FORWARD
        wheelThree!!.direction = DcMotorSimple.Direction.FORWARD

        ctx.teleop.sleep(1000)

        countPerDegree(degrees)

        if (power > 0) {
            wheelFinalDistanceOne = wheelOne!!.currentPosition + tick!!
            wheelFinalDistanceTwo = wheelTwo!!.currentPosition + tick!!
            wheelFinalDistanceThree = wheelThree!!.currentPosition + tick!!

            while (wheelOne!!.currentPosition < wheelFinalDistanceOne!! && ctx.teleop.opModeIsActive()) {
                ctx.wheels.power(power)
            }

            ctx.wheels.stop(); ctx.teleop.sleep(1000)

            while (wheelOne!!.currentPosition > wheelFinalDistanceOne!! && ctx.teleop.opModeIsActive()) {
                ctx.wheels.power(-0.1)
            }

            ctx.wheels.stop()
            ctx.teleop.sleep(1000)

        } else if (power < 0) {
            wheelFinalDistanceOne = wheelOne!!.currentPosition - tick!!
            wheelFinalDistanceTwo = wheelTwo!!.currentPosition - tick!!
            wheelFinalDistanceThree = wheelThree!!.currentPosition - tick!!

            while (wheelOne!!.currentPosition >= wheelFinalDistanceOne!! && ctx.teleop.opModeIsActive()) {
                ctx.wheels.power(power)
            }

            ctx.wheels.stop()
            ctx.teleop.sleep(1000)

            while (wheelOne!!.currentPosition <= wheelFinalDistanceOne!! && ctx.teleop.opModeIsActive()) {
                ctx.wheels.power(0.1)
            }
            ctx.wheels.stop()
            ctx.teleop.sleep(1000)
        }
    }
}