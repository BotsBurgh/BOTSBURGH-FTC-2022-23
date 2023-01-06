package org.firstinspires.ftc.teamcode.arch.runtime

import org.firstinspires.ftc.teamcode.arch.Context
import org.firstinspires.ftc.teamcode.arch.Plugin

class Runtime(private val plugins: MutableList<Plugin> = ArrayList()) {
    val pre = LinkedList()
    val cycle = LinkedList()

    fun run(ctx: Context) {
        // Initialize plugins
        plugins.forEach { it._init(ctx) }

        this.pre.invokeRecursive(ctx)

        ctx.teleop.waitForStart()

        while (ctx.teleop.opModeIsActive()) {
            this.cycle.invokeRecursive(ctx)
        }
    }
}
