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

var wheelOne: DcMotor? = null
var wheelTwo: DcMotor? = null

var wheelCurrentDistanceOne: Int? = null
var wheelCurrentDistanceTwo: Int? = null

var wheelFinalDistanceOne: Double? = null
var wheelFinalDistanceTwo: Double? = null

var tick: Double? = null

class WheelEncoders : Plugin() {
    init {
        wheelsEncoderStore = this
    }

    private fun inchesToTicks(inches: Double) {
        tick = inches * 2.54 / WHEEL_CIRCUMFERENCE * ENCODER_RESOLUTION
    }

    private fun getWheelPosition(wheel: DcMotor?) {
        if (wheel == wheelOne) {
            wheelCurrentDistanceOne = wheelOne!!.currentPosition
        } else if (wheel == wheelTwo) {
            wheelCurrentDistanceTwo = wheelTwo!!.currentPosition

        }
    }

    private fun calculateTotalDistance(distance: Double) {

        getWheelPosition(wheelOne)
        getWheelPosition(wheelTwo)

    }

    private fun wheelEncoderDirection(front: Double, inches: Double, power: Double) {
        //three fronts are PI (camera), 7 * PI / 4 (Slide 1), and Pi  / 4 (Slide 2)
        if (front == PI) {
            wheelOne = ctx.wheels.motor1
            wheelTwo = ctx.wheels.motor2
        } else if (front == 7 * PI / 4) {
            wheelOne = ctx.wheels.motor1
            wheelTwo = ctx.wheels.motor2
        } else if (front == PI / 4) {
            wheelOne = ctx.wheels.motor1
            wheelTwo = ctx.wheels.motor2
        }

        inchesToTicks(inches)


    }
}