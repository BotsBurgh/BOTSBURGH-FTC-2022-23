package org.firstinspires.ftc.teamcode.api.components

import org.firstinspires.ftc.teamcode.api.plugins.logger
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.runloop.Component

class LoggerTeleOp : Component() {
    override val order = Byte.MAX_VALUE

    override val pre = fun(ctx: Context) {
        ctx.logger.addData("Status", "Initialized")
        ctx.logger.update()
    }

    override val cycle = fun(ctx: Context) {
        ctx.logger.addData("Status", "Running")
        ctx.logger.update()
    }
}
