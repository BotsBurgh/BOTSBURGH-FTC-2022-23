package org.firstinspires.ftc.teamcode.api.components

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.api.plugins.linear_slides
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.runloop.Component

const val CLAW_CLOSE_POSITION = 0.35
const val CLAW_OPEN_POSITION = 0.5

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
        ctx.teleop.telemetry.addData("LS", ctx.linear_slides.linearSlide1!!.currentPosition)
        ctx.teleop.telemetry.update()

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
    }
}
