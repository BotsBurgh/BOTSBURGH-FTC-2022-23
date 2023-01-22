package org.firstinspires.ftc.teamcode.api.plugins

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.Plugin


import kotlin.math.PI

private var wheelsEncoderStore: WheelEncoders? = null

val Context.wheel_encoders
    get() = wheelsEncoderStore!!

private const val ENCODER_RESOLUTION: Double = 537.689839572
private const val WHEEL_CIRCUMFERENCE: Double = 9.6 * PI // In centimeters
private const val GEAR_Ratio: Double = 19.0/2.0/1/0



class WheelEncoders : Plugin() {
    var wheelOne: DcMotor? = null
    var wheelTwo: DcMotor? = null

    var wheelCurrentDistanceOne: Double? = null
    var wheelCurrentDistanceTwo: Double? = null

    var wheelFinalDistanceOne: Double? = null
    var wheelFinalDistanceTwo: Double? = null

    var tick: Double? = null

    init {
        wheelsEncoderStore = this
    }

    private fun countPerInch(inches: Double) {
        //tick = inches * 2.54 / WHEEL_CIRCUMFERENCE * ENCODER_RESOLUTION
        tick = 42 * inches
    }

    private fun getWheelPosition(wheel: DcMotor?) {
        if (wheel == wheelOne) {
            wheelCurrentDistanceOne = wheelOne!!.currentPosition.toDouble() * -1
        } else if (wheel == wheelTwo) {
            wheelCurrentDistanceTwo = wheelTwo!!.currentPosition.toDouble()

        }
    }

    private fun calculateTotalDistance(inches: Double) {

        getWheelPosition(wheelOne)
        getWheelPosition(wheelTwo)

        countPerInch(inches)


        wheelFinalDistanceOne = wheelCurrentDistanceOne!! + tick!!
        wheelFinalDistanceTwo = wheelCurrentDistanceTwo!! + tick!!

    }

     fun wheelEncoderDirection(front: Double, inches: Double, power: Double) {
        //three fronts are PI (camera), 7 * PI / 4 (Slide 1), and Pi  / 4 (Slide 2)
        if (front == PI) {
            wheelOne = ctx.wheels.motor2
            wheelTwo = ctx.wheels.motor3
        } else if (front == 7 * PI / 4) {
            wheelOne = ctx.wheels.motor1
            wheelTwo = ctx.wheels.motor2
        } else if (front == PI / 4) {
            wheelOne = ctx.wheels.motor1
            wheelTwo = ctx.wheels.motor2
        }

         calculateTotalDistance(inches)
         getWheelPosition(wheelOne)


            while (wheelFinalDistanceOne!! >= wheelCurrentDistanceOne!!) {
                ctx.teleop.telemetry.addData("wheel current", wheelCurrentDistanceOne)
                ctx.teleop.telemetry.update()
                ctx.wheels.powerDirection(PI, 0.25)
                getWheelPosition(wheelOne)
            }; ctx.wheels.stop()






    }
}