package org.firstinspires.ftc.teamcode.api.components

import org.firstinspires.ftc.teamcode.api.arch.Component
import org.firstinspires.ftc.teamcode.api.arch.Context

class Logger: Component() {
    override val pre = fun(context: Context) {
        context.teleop.telemetry.addData("Status", "Initialized")
        context.teleop.telemetry.update()
    }

    override val cycle = fun(context: Context) {
        context.teleop.telemetry.addData("Status", "Running")
        context.teleop.telemetry.update()
    }

    override val post = fun(context: Context) {
        context.teleop.telemetry.addData("Status", "Finished")
        context.teleop.telemetry.update()
    }
}
