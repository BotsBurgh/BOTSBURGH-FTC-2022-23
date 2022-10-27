package org.firstinspires.ftc.teamcode.api.components

import org.firstinspires.ftc.teamcode.api.arch.Component
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.plugins.log
import org.firstinspires.ftc.teamcode.api.plugins.logger

/**
 * Utility component that updates the log and adds helper messages.
 *
 * This runs at the end of each stage, and runs `Logger.pushLog` so that queued messages are displayed.
 */
class Logger: Component() {
    // Run this component at the very end of each stage.
    override val order = Byte.MAX_VALUE

    override val pre = fun(ctx: Context) {
        ctx.logger.logTitle("Status", "Initialized")
        ctx.logger.pushLog()
    }

    override val cycle = fun(ctx: Context) {
        ctx.logger.logTitle("Status", "Running")
        ctx.logger.pushLog()
    }

    override val post = fun(ctx: Context) {
        ctx.logger.logTitle("Status", "Finished")
        ctx.logger.pushLog()
    }
}
