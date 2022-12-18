package org.firstinspires.ftc.teamcode.api.plugins

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.Plugin

private var linear_slides_store: LinearSlides? = null

val Context.linear_slides
    get() = linear_slides_store!!

private const val LINEAR_SLIDE_1_NAME = "linearSlide1"
// private const val LINEAR_SLIDE_2_NAME = "linearSlide2"

private const val CLAW_1_NAME = "claw1"
// private const val CLAW_2_NAME = "claw2"

/**
 * Plugin for controlling the 2 linear slides on the robot.
 */
class LinearSlides: Plugin() {
    init {
        linear_slides_store = this
    }

    // Can be read by anything, but can only by set by itself (aka .init())
    var linearSlide1: DcMotor? = null
        private set
    // var linearSlide2: DcMotor? = null
    //     private set

    var claw1: Servo? = null
        private set
    // var claw2: Servo? = null
    //    private set

    fun init() {
        this.linearSlide1 = this.ctx.teleop.hardwareMap.get(DcMotor::class.java, LINEAR_SLIDE_1_NAME)
        // this.linearSlide2 = this.ctx.teleop.hardwareMap.get(DcMotor::class.java, LINEAR_SLIDE_2_NAME)

        this.claw1 = this.ctx.teleop.hardwareMap.get(Servo::class.java, CLAW_1_NAME)
        // this.claw2 = this.ctx.teleop.hardwareMap.get(Servo::class.java, CLAW_2_NAME)

        // Prevent slide from moving down due to gravity as best as possible.
        this.linearSlide1!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        // this.linearSlide2!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

        // Reverse slide direction
        this.linearSlide1!!.direction = DcMotorSimple.Direction.REVERSE
        // this.linearSlide2!!.direction = DcMotorSimple.Direction.REVERSE
    }

    fun powerSlide1(power: Double) {
        if (power > 0f && this.linearSlide1!!.currentPosition < 5800) {
            // Positive
            this.linearSlide1!!.power = power
        } else if (power < 0f && this.linearSlide1!!.currentPosition > 20) {
            // Negative
            this.linearSlide1!!.power = power
        } else {
            // power must be equal to 0, so stop slide
            this.stopSlide1()
        }
    }

    fun stopSlide1() {
        this.linearSlide1!!.power = 0.0
    }

    fun positionClaw1(position: Double) {
        this.claw1!!.position = position
    }

    /*
    fun powerSlide2(power: Double) {
        this.linearSlide2!!.power = power
    }

    fun stopSlide2() {
        this.powerSlide2(0.0)
    }

    fun positionClaw2(position: Double) {
        this.claw2!!.position = position
    }
     */
}
