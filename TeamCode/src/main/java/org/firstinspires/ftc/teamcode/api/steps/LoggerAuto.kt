package org.firstinspires.ftc.teamcode.api.steps

import org.firstinspires.ftc.teamcode.api.plugins.logger
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.sequential.Step

class LoggerAuto : Step() {
    override val pre = fun(ctx: Context) {
        ctx.logger.info("Initializing")
    }

    override val main = fun(ctx: Context) {
        ctx.logger.info("Running")
    }
}
