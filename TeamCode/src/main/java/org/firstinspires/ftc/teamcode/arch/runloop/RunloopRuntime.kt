package org.firstinspires.ftc.teamcode.arch.runloop

import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.IRuntime
import org.firstinspires.ftc.teamcode.arch.base.Plugin

/**
 * The runtime used for running components.
 */
class RunloopRuntime(private val plugins: List<Plugin>) : IRuntime {
    val pre = ComponentList()
    val cycle = ComponentList()

    override fun run(ctx: Context) {
        this.plugins.forEach { it._init(ctx) }

        this.pre.invokeRecursive(ctx)

        ctx.teleop.waitForStart()

        while (ctx.teleop.opModeIsActive()) {
            this.cycle.invokeRecursive(ctx)
        }
    }
}
