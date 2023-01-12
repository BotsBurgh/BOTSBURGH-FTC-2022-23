package org.firstinspires.ftc.teamcode.api.components

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.api.plugins.linear_slides
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.runloop.Component

const val SLIDE_UP_POWER = 0.8
const val SLIDE_DOWN_POWER = 0.6
const val CLAW_CLOSE_POSITION = 0.4
const val CLAW_OPEN_POSITION = 1.0

/**
 * Component for moving the linear slide in a teleop.
 */
class LinearSlidesTeleOp : Component() {
    override val pre = fun(ctx: Context) {
        ctx.linear_slides.init()

        // Reset encoders
        ctx.linear_slides.linearSlide1!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        ctx.linear_slides.linearSlide1!!.mode = DcMotor.RunMode.RUN_USING_ENCODER

        ctx.teleop.telemetry.addData("LS", ctx.linear_slides.linearSlide1!!.currentPosition)
    }

    override val cycle = fun(ctx: Context) {

        val joyX = ctx.teleop.gamepad2.left_stick_x.toDouble()
        val joyY = -ctx.teleop.gamepad2.left_stick_y.toDouble()

        // The second linear slide is broken, so this prevents it from being used
        if (ctx.teleop.gamepad2.left_stick_y != 0f) {
            ctx.linear_slides.powerSlide1(joyY * 5)

        } else {
            ctx.linear_slides.stopSlide1()
        }

        if (ctx.teleop.gamepad2.a) {
            ctx.linear_slides.positionClaw1(CLAW_CLOSE_POSITION)
        } else if (ctx.teleop.gamepad2.b) {
            ctx.linear_slides.positionClaw1(CLAW_OPEN_POSITION)
        }

        ctx.teleop.telemetry.addData("LS", ctx.linear_slides.linearSlide1!!.currentPosition)
    }
}
