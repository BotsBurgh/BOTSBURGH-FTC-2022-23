package org.firstinspires.ftc.teamcode.api.components

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.api.arch.Component
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.RunMode
import org.firstinspires.ftc.teamcode.api.plugins.linear_slides

const val SLIDE_UP_POWER = 0.8
const val SLIDE_DOWN_POWER = 0.6
const val CLAW_CLOSE_POSITION = 0.4
const val CLAW_OPEN_POSITION = 1.0

/**
 * Component for moving the linear slide in a teleop.
 */
class LinearSlidesTeleOp: Component() {
    override val runMode = RunMode.TeleOp

    override val pre = fun(ctx: Context) {
        ctx.linear_slides.init()

        // Reset encoders
        ctx.linear_slides.linearSlide1!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        ctx.linear_slides.linearSlide1!!.mode = DcMotor.RunMode.RUN_USING_ENCODER

        ctx.teleop.telemetry.addData("LS", ctx.linear_slides.linearSlide1!!.currentPosition)
    }

    override val cycle = fun(ctx: Context) {
        // The second linear slide is broken, so this prevents it from being used
        if (ctx.teleop.gamepad1.x) {
            ctx.linear_slides.powerSlide1(-SLIDE_DOWN_POWER)
        } else if (ctx.teleop.gamepad1.y) {
            ctx.linear_slides.powerSlide1(SLIDE_UP_POWER)
        } else {
            ctx.linear_slides.stopSlide1()
        }

        if (ctx.teleop.gamepad1.a) {
            ctx.linear_slides.positionClaw1(CLAW_CLOSE_POSITION)
        } else if (ctx.teleop.gamepad1.b) {
            ctx.linear_slides.positionClaw1(CLAW_OPEN_POSITION)
        }

        ctx.teleop.telemetry.addData("LS", ctx.linear_slides.linearSlide1!!.currentPosition)
    }
}
