package org.firstinspires.ftc.teamcode.api.plugins

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.Plugin

private val linear_slides_store = LinearSlides()

val Context.linear_slides
    get() = linear_slides_store

private const val LINEAR_SLIDE_1_NAME = "linearSlide1"
// private const val LINEAR_SLIDE_2_NAME = "linearSlide2"

private const val CLAW_1_NAME = "claw1"
private const val CLAW_2_NAME = "claw2"

/**
 * Plugin for controlling the 2 linear slides on the robot.
 */
class LinearSlides: Plugin() {
    private var linearSlide1: DcMotor? = null
    // private var linearSlide2: DcMotor? = null

    private var claw1: Servo? = null
    // private var claw2: Servo? = null

    fun init() {
        this.linearSlide1 = this.ctx.teleop.hardwareMap.get(DcMotor::class.java, LINEAR_SLIDE_1_NAME)

        this.claw1 = this.ctx.teleop.hardwareMap.get(Servo::class.java, CLAW_1_NAME)

        // Prevent slide from moving down due to gravity as best as possible.
        this.linearSlide1!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }

    fun powerSlide1(power: Double) {
        this.linearSlide1?.power = power
    }

    fun stopSlide1() {
        this.powerSlide1(0.0)
    }

    fun positionClaw1(position: Double) {
        this.claw1?.position = position
    }
}
