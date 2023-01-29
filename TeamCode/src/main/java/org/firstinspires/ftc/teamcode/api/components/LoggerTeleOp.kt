package org.firstinspires.ftc.teamcode.api.components

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.api.plugins.logger.logger
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.runloop.Component

class LoggerTeleOp : Component() {
    private val runtime = ElapsedTime()

    override val order = Byte.MAX_VALUE

    override val pre = fun(ctx: Context) {
        ctx.logger.info("Initialized teleop")
        ctx.logger.dataCollector.collect()
    }

    override val cycle = fun(ctx: Context) {
        // Every 0.1 seconds
        if (runtime.milliseconds() >= 50) {
            ctx.logger.dataCollector.collect()
            runtime.reset()
        }
    }
}
