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

private const val ENCODER_RESOLUTION: Double = 537.689839572
private const val WHEEL_CIRCUMFERENCE: Double = 9.6 * PI // In centimeters
private const val GEAR_Ratio: Double = 19.0 / 2.0 / 1

@Config
object WheelEncodersConfig {
    @JvmField
    var TICKS_PER_INCH: Int = 42
    @JvmField
    var TICK_PER_DEGREE: Double = 6.5
    @JvmField
    var WHEEL_CORRECTION_MULTIPLIER: Double = 1.25
}


class WheelEncoders : Plugin() {
    var wheelOne: DcMotor? = null
    var wheelTwo: DcMotor? = null
    var wheelThree: DcMotor? = null

    var wheelCurrentDistanceOne: Double? = null
    var wheelCurrentDistanceTwo: Double? = null
    var wheelCurrentDistanceThree: Double? = null

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

    private fun getWheelPosition(wheel: DcMotor?) {
        if (wheel == wheelOne) {
            wheelCurrentDistanceOne = abs(wheelOne!!.currentPosition.toDouble())
        } else if (wheel == wheelTwo) {
            wheelCurrentDistanceTwo = abs(wheelTwo!!.currentPosition.toDouble())
        } else if (wheel == wheelThree) {
            wheelCurrentDistanceThree = wheelThree!!.currentPosition.toDouble()
        }
    }

    private fun calculateTotalDistance(inches: Double) {

        countPerInch(inches)


        wheelFinalDistanceOne = wheelCurrentDistanceOne!! + tick!!
        wheelFinalDistanceTwo = wheelCurrentDistanceTwo!! + tick!!
        wheelFinalDistanceThree = 0.0

    }

    private fun calculateTotalDegrees(degrees: Double) {

        countPerDegree(degrees)

        wheelFinalDistanceOne = wheelCurrentDistanceOne!! + tick!!
        wheelFinalDistanceTwo = wheelCurrentDistanceTwo!! + tick!!
        wheelFinalDistanceThree = wheelCurrentDistanceThree!! + tick!!
    }

    fun wheelEncoderDirection(front: Double, inches: Double, power: Double) {
        //three fronts are PI (camera), 7 * PI / 4 (Slide 1), and Pi  / 4 (Slide 2)
        if (front == PI) {
            wheelOne = ctx.wheels.motor2
            wheelTwo = ctx.wheels.motor3
            wheelThree = ctx.wheels.motor1
            wheelOne!!.direction = DcMotorSimple.Direction.REVERSE
            wheelThree!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        } else if (front == 7 * PI / 4) {
            wheelOne = ctx.wheels.motor1
            wheelTwo = ctx.wheels.motor2
        } else if (front == PI / 4) {
            wheelOne = ctx.wheels.motor1
            wheelTwo = ctx.wheels.motor2
        }

        calculateTotalDistance(inches)

        //wheelOne!!.targetPosition = 1000

        //wheelOne!!.mode = DcMotor.RunMode.RUN_TO_POSITION

        //while (abs(wheelOne!!.currentPosition - 1000) > 10)

        //wheelOne!!.mode = DcMotor.RunMode.RUN_USING_ENCODER

        //while (wheelFinalDistanceOne!! >= wheelCurrentDistanceOne!!) {
        //    ctx.teleop.telemetry.addData("wheel current", wheelCurrentDistanceOne)
        //    ctx.teleop.telemetry.update()
        //    ctx.wheels.powerDirection(PI, 0.25)
        //    getWheelPosition(wheelOne)
        //}; ctx.wheels.stop()

        wheelOne!!.targetPosition = wheelFinalDistanceOne!!.toInt()
        wheelTwo!!.targetPosition = wheelFinalDistanceTwo!!.toInt()
        wheelThree!!.targetPosition = wheelFinalDistanceThree!!.toInt()

        //wheelOne!!.mode = DcMotor.RunMode.RUN_TO_POSITION

        //ctx.teleop.sleep(1000)

        //wheelOne!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        while (wheelOne!!.currentPosition <= wheelFinalDistanceOne!! || wheelTwo!!.currentPosition <= wheelFinalDistanceTwo!!) {

            if (wheelOne!!.currentPosition <= wheelFinalDistanceOne!!) {
                wheelOne!!.power = power
            }

            if (wheelTwo!!.currentPosition <= wheelFinalDistanceTwo!!) {
                wheelTwo!!.power = power
            }

            if (abs(wheelOne!!.currentPosition - 10) >= wheelTwo!!.currentPosition) {
                //wheel one is too far ahead
                wheelTwo!!.power = power * WheelEncodersConfig.WHEEL_CORRECTION_MULTIPLIER
            }

            if (abs(wheelOne!!.currentPosition + 10) <= wheelTwo!!.currentPosition) {
                //wheel one is too far behind
                wheelTwo!!.power = power * WheelEncodersConfig.WHEEL_CORRECTION_MULTIPLIER
            }

            if (abs(wheelTwo!!.currentPosition - 10) >= wheelOne!!.currentPosition) {
                //wheel two is too far ahead
                wheelTwo!!.power = power * WheelEncodersConfig.WHEEL_CORRECTION_MULTIPLIER
            }

            if (abs(wheelTwo!!.currentPosition + 10) <= wheelOne!!.currentPosition) {
                //wheel two is too far behind
                wheelTwo!!.power = power * WheelEncodersConfig.WHEEL_CORRECTION_MULTIPLIER
            }

            if (wheelThree!!.currentPosition != 0) {
                if (wheelThree!!.currentPosition > 0) {
                    //wheel three is going to to right
                    wheelThree!!.power = -0.1
                } else if (wheelThree!!.currentPosition < 0) {
                    //wheel three is going to the left
                    wheelThree!!.power = 0.1
                } else {
                    wheelThree!!.power = 0.0
                }
            }
            ctx.teleop.telemetry.addData("WheelOne Final Tick", wheelFinalDistanceOne)
            ctx.teleop.telemetry.addData("WheelOne Current Position", wheelOne!!.currentPosition)
            ctx.teleop.telemetry.addData("WheelTwo Final Tick", wheelFinalDistanceTwo)
            ctx.teleop.telemetry.addData("WheelTwo Current Position", wheelTwo!!.currentPosition)
            ctx.teleop.telemetry.addData("WheelThree Final Tick", wheelFinalDistanceThree)
            ctx.teleop.telemetry.addData("WheelThree Current Position", ctx.wheels.motor1!!.currentPosition)
            ctx.teleop.telemetry.update()

        }; ctx.wheels_ex.stopAndResetEncoders()
    }

    /**
     * Rotates the robot a certain degree and power using encoder values
     */
    fun wheelEncoderSpin (degrees: Double, power: Double) {
            wheelOne = ctx.wheels.motor1
            wheelTwo = ctx.wheels.motor2
            wheelThree = ctx.wheels.motor3

            countPerDegree(degrees)

            getWheelPosition(wheelOne)
            getWheelPosition(wheelTwo)
            getWheelPosition(wheelThree)

            wheelFinalDistanceOne = wheelCurrentDistanceOne!! + tick!!
            wheelFinalDistanceTwo = wheelCurrentDistanceTwo!! + tick!!
            wheelFinalDistanceThree = wheelCurrentDistanceThree!! + tick!!

            while (wheelOne!!.currentPosition <= wheelFinalDistanceOne!!) {
                ctx.wheels.power(power)
            }; ctx.wheels_ex.stopAndResetEncoders()
        }


}