package org.firstinspires.ftc.teamcode.api.components

import org.firstinspires.ftc.teamcode.api.arch.Component
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.RunMode
import org.firstinspires.ftc.teamcode.api.plugins.linear_slides

const val SLIDE_UP_POWER = 0.8
const val CLAW_CLOSE_POSITION = 0.4
const val CLAW_OPEN_POSITION = 1.0

/**
 * Component for moving the linear slide in a teleop.
 */
class LinearSlidesTeleOp: Component() {
    override val runMode = RunMode.TeleOp

    override val pre = fun(ctx: Context) {
        ctx.linear_slides.init()
    }

    override val cycle = fun(ctx: Context) {
        // The second linear slide is broken, so this prevents it from being used
        if (ctx.teleop.gamepad1.x) {
            ctx.linear_slides.powerSlide1(SLIDE_UP_POWER)
        } else if (ctx.teleop.gamepad1.y) {
            ctx.linear_slides.powerSlide1(-SLIDE_UP_POWER)
        } else {
            ctx.linear_slides.stopSlide1()
        }

        if (ctx.teleop.gamepad1.a) {
            ctx.linear_slides.positionClaw1(CLAW_CLOSE_POSITION)
        } else if (ctx.teleop.gamepad1.b) {
            ctx.linear_slides.positionClaw1(CLAW_OPEN_POSITION)
        }

        /*
        if (ctx.teleop.gamepad1.dpad_left) {
            if (ctx.teleop.gamepad1.x) {
                ctx.linear_slides.powerSlide1(SLIDE_UP_POWER)
            } else if (ctx.teleop.gamepad1.y) {
                ctx.linear_slides.powerSlide1(-SLIDE_UP_POWER)
            } else {
                ctx.linear_slides.stopSlide1()
            }

            if (ctx.teleop.gamepad1.a) {
                ctx.linear_slides.positionClaw1(CLAW_CLOSE_POSITION)
            } else if (ctx.teleop.gamepad1.b) {
                ctx.linear_slides.positionClaw1(CLAW_OPEN_POSITION)
            }
        } else if (ctx.teleop.gamepad1.dpad_right) {
            if (ctx.teleop.gamepad1.x) {
                ctx.linear_slides.powerSlide2(SLIDE_UP_POWER)
            } else if (ctx.teleop.gamepad1.y) {
                ctx.linear_slides.powerSlide2(-SLIDE_UP_POWER)
            } else {
                ctx.linear_slides.stopSlide2()
            }

            if (ctx.teleop.gamepad1.a) {
                ctx.linear_slides.positionClaw2(CLAW_CLOSE_POSITION)
            } else if (ctx.teleop.gamepad1.b) {
                ctx.linear_slides.positionClaw2(CLAW_OPEN_POSITION)
            }
        }
         */
    }
}
