package org.firstinspires.ftc.teamcode.api.components

import org.firstinspires.ftc.teamcode.api.arch.Component
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.OpMode
import org.firstinspires.ftc.teamcode.api.plugins.linear_slides

/**
 * Component for moving the linear slide in a teleop.
 */
class LinearSlides: Component() {
    override val opmode = OpMode.TeleOp

    override val pre = fun(ctx: Context) {
        ctx.linear_slides.init()
    }

    override val cycle = fun(ctx: Context) {
        if (ctx.teleop.gamepad1.dpad_left) {
            if (ctx.teleop.gamepad1.x) {
                ctx.linear_slides.powerSlide1(0.5)
            } else if (ctx.teleop.gamepad1.y) {
                ctx.linear_slides.powerSlide1(-0.5)
            } else {
                ctx.linear_slides.stopSlide1()
            }

            if (ctx.teleop.gamepad1.a) {
                ctx.linear_slides.positionClaw1(0.4)
            } else if (ctx.teleop.gamepad1.b) {
                ctx.linear_slides.positionClaw1(1.0)
            }
            //-----------------------------------------------

        } else if (ctx.teleop.gamepad1.dpad_right) {
            if (ctx.teleop.gamepad1.x) {
                ctx.linear_slides.powerSlide2(0.5)
            } else if (ctx.teleop.gamepad1.y) {
                ctx.linear_slides.powerSlide2(-0.5)
            } else {
                ctx.linear_slides.stopSlide2()
            }

            if (ctx.teleop.gamepad1.a) {
                ctx.linear_slides.positionClaw2(0.4)
            } else if (ctx.teleop.gamepad1.b) {
                ctx.linear_slides.positionClaw2(1.0)
            }
        }
    }
}
