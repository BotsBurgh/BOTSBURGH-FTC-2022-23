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
        // Alias gamepad1
        val gamepad = ctx.teleop.gamepad1

        // Get rotation power as right stick left and right movement
        val rotationPower = 0.4 * -gamepad.right_stick_x.toDouble()

        // Use joystick input
        val joyX = gamepad.left_stick_x.toDouble()
        val joyY = -gamepad.left_stick_y.toDouble()

        // Convert xy coords to polar coords, then calculate power
        val directionPower = ctx.wheels.calculatePower(
            atan2(joyY, joyX) - (PI / 2.0),
            sqrt(joyY * joyY + joyX * joyX)
        )

        // Combine the rotation and direction powers together
        val totalPower = Triple(
            directionPower.first + rotationPower,
            directionPower.second + rotationPower,
            directionPower.third + rotationPower,
        )

        ctx.wheels.power(totalPower)
    }
}
