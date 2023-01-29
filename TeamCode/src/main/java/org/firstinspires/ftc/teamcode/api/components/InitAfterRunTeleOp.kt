package org.firstinspires.ftc.teamcode.api.components

import org.firstinspires.ftc.teamcode.api.plugins.wheels
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.runloop.Component

class InitAfterRunTeleOp: Component() {
    private var hasRun: Boolean = false

    override val order = Byte.MIN_VALUE

    override val cycle = fun(ctx: Context) {
        if (!hasRun) {
            ctx.wheels.stopAndResetEncoders()
            ctx.teleop.sleep(500)
            hasRun = true
        }
    }
}
