package org.firstinspires.ftc.teamcode.api.components

import org.firstinspires.ftc.teamcode.api.plugins.logger.logger
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.runloop.Component

class LoggerTeleOp : Component() {
    override val order = Byte.MAX_VALUE

    override val pre = fun(ctx: Context) {
        ctx.logger.info("Initialized teleop")
        ctx.logger.dataCollector.collect()
    }

    override val cycle = fun(ctx: Context) {
        ctx.logger.dataCollector.collect()
    }
}
