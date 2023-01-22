package org.firstinspires.ftc.teamcode.api.components

import org.firstinspires.ftc.teamcode.api.plugins.wheels
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.runloop.Component
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.sqrt

private object WheelsConfig {
    @JvmField var ROTATION_MULTIPLIER: Double = 0.6
    @JvmField var PRECISE_ROTATION_AMOUNT: Double = 0.3
}

/**
 * A component for controlling the wheels in a teleop context.
 */
class WheelsTeleOp : Component() {
    override val pre = fun(ctx: Context) {
        ctx.wheels.init()
    }

    override val cycle = fun(ctx: Context) {
        // Alias gamepad1
        val gamepad = ctx.teleop.gamepad1

        // Get rotation power as right stick left and right movement
        var rotationPower = WheelsConfig.ROTATION_MULTIPLIER * -gamepad.right_stick_x.toDouble()

        if (gamepad.left_bumper) {
            rotationPower += WheelsConfig.PRECISE_ROTATION_AMOUNT
        } else if (gamepad.right_bumper) {
            rotationPower -= WheelsConfig.PRECISE_ROTATION_AMOUNT
        }

        // Use joystick input
        val joyX = gamepad.left_stick_x.toDouble()
        val joyY = -gamepad.left_stick_y.toDouble()

        // Angle, adjusted so linear slide is front
        val joyRadians = atan2(joyY, joyX) - (PI / 3.0) - (PI / 2.0)
        // Strength
        val joyMagnitude = sqrt(joyY * joyY + joyX * joyX)

        // Calculate power of each wheel from polar coordinates
        val directionPower = ctx.wheels.calculatePower(joyRadians, joyMagnitude)

        // Combine the rotation and direction powers together
        val totalPower = Triple(
            directionPower.first + rotationPower,
            directionPower.second + rotationPower,
            directionPower.third + rotationPower,
        )

        ctx.wheels.power(totalPower)
    }
}
