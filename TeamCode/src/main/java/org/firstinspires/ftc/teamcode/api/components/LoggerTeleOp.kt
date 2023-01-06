package org.firstinspires.ftc.teamcode.api.components

import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.runloop.Component

class LoggerTeleOp: Component() {
    override val order = Byte.MAX_VALUE

    override val pre = fun(ctx: Context) {
        ctx.teleop.telemetry.addData("Status", "Initialized")
        ctx.teleop.telemetry.update()
    }

    override val cycle = fun(ctx: Context) {
        ctx.teleop.telemetry.addData("Status", "Running")
        ctx.teleop.telemetry.update()
    }
}
