package org.firstinspires.ftc.teamcode.arch.sequential

import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.IRuntime
import org.firstinspires.ftc.teamcode.arch.base.Plugin

class SequentialRuntime(private val plugins: List<Plugin>): IRuntime {
    val pre = StepList()
    val main = StepList()

    override fun run(ctx: Context) {
        this.plugins.forEach { it._init(ctx) }

        this.pre.invokeRecursive(ctx)

        ctx.teleop.waitForStart()

        this.main.invokeRecursive(ctx)
    }
}
