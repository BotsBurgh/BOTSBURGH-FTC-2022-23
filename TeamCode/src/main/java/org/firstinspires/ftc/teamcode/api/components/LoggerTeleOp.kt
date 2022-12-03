package org.firstinspires.ftc.teamcode.api.components

import org.firstinspires.ftc.teamcode.api.arch.Component
import org.firstinspires.ftc.teamcode.api.arch.Context

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

    override val post = fun(ctx: Context) {
        ctx.teleop.telemetry.addData("Status", "Finished")
        ctx.teleop.telemetry.update()
    }
}
