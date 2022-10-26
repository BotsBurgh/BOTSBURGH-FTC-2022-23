package org.firstinspires.ftc.teamcode.api.components

import org.firstinspires.ftc.teamcode.api.arch.Component
import org.firstinspires.ftc.teamcode.api.arch.Context

class Logger: Component() {
    // Run this component at the very end of each stage.
    override val order = Byte.MAX_VALUE

    override val pre = fun(ctx: Context) {
        ctx.log("Status", "Initialized")
        ctx.pushLog()
    }

    override val cycle = fun(ctx: Context) {
        ctx.log("Status", "Running")
        ctx.pushLog()
    }

    override val post = fun(ctx: Context) {
        ctx.log("Status", "Finished")
        ctx.pushLog()
    }
}

// Plugins
fun Context.log(title: String, data: String) {
    this.teleop.telemetry.addData(title, data)
}

fun Context.pushLog() { this.teleop.telemetry.update() }
