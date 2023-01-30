package org.firstinspires.ftc.teamcode.api.components

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.api.plugins.linear_slides
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.runloop.Component

@Config
private object LinearSlideTeleOpConfig {
    @JvmField
    var SLIDE_MOVEMENT_MULTIPLIER: Double = 5.0
}

/**
 * Component for moving the linear slide in a teleop.
 */
class LinearSlidesTeleOp : Component() {
    override val pre = fun(ctx: Context) {
        ctx.linear_slides.init()

        // Reset encoders
        ctx.linear_slides.linearSlide1!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        ctx.linear_slides.linearSlide1!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
    }

    override val cycle = fun(ctx: Context) {
        val joyY = -ctx.teleop.gamepad2.left_stick_y.toDouble()

        if (ctx.teleop.gamepad2.left_stick_y != 0f) {
            ctx.linear_slides.powerSlide1(joyY * LinearSlideTeleOpConfig.SLIDE_MOVEMENT_MULTIPLIER)
        } else {
            ctx.linear_slides.stopSlide1()
        }

        if (ctx.teleop.gamepad2.a) {
            ctx.linear_slides.closeClaw1()
        } else if (ctx.teleop.gamepad2.b) {
            ctx.linear_slides.openClaw1()
        }
    }
}
