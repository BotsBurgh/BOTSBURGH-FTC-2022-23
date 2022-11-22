package org.firstinspires.ftc.teamcode.api.components

import org.firstinspires.ftc.teamcode.api.arch.Component
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.RunMode
import org.firstinspires.ftc.teamcode.api.plugins.wheels
import kotlin.math.*

/**
 * A component for controlling the wheels in a teleop context.
 */
class WheelsTeleOp: Component() {
    override val runMode = RunMode.TeleOp

    override val pre = fun(ctx: Context) {
        ctx.wheels.init()
    }

    override val cycle = fun(ctx: Context) {
        // Spinning is prioritized over joystick
        if (ctx.teleop.gamepad1.left_bumper) {
            ctx.wheels.powerRotation(0.6)
        } else if (ctx.teleop.gamepad1.right_bumper) {
            ctx.wheels.powerRotation(-0.6)
        } else {
            // Use joystick input
            val joyX = -ctx.teleop.gamepad1.left_stick_x.toDouble() // Flip x-axis
            val joyY = ctx.teleop.gamepad1.left_stick_y.toDouble()

            // Convert xy coords to polar coords, then move robot
            ctx.wheels.powerDirection(
                atan2(joyY, joyX) - (PI / 2.0),
                sqrt(joyY * joyY + joyX * joyX)
            )
        }
    }
}