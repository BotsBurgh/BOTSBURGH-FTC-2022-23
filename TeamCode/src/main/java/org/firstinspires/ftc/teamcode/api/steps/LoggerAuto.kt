package org.firstinspires.ftc.teamcode.api.steps

import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.sequential.Step

class LoggerAuto: Step() {
    override val pre = fun(ctx: Context) {
        ctx.teleop.telemetry.addData("Status", "Initialized")
        ctx.teleop.telemetry.update()
    }

    override val main = fun(ctx: Context) {
        ctx.teleop.telemetry.addData("Status", "Beginning to run")
        ctx.teleop.telemetry.update()
    }
}
