package org.firstinspires.ftc.teamcode.api.steps

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.api.plugins.logger.logger
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.sequential.Step

class LoggerAuto : Step() {
    override val pre = fun(ctx: Context) {
        ctx.logger.info("Initializing autonomous")
        ctx.logger.dataCollector.collect()
    }

    override val main = fun(ctx: Context) {
        ctx.logger.info("Running autonomous")
    }
}
