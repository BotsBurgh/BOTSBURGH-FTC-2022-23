package org.firstinspires.ftc.teamcode.api.arch.runtime

import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.Plugin

class Runtime(private val plugins: MutableList<Plugin> = ArrayList()) {
    val pre = LinkedList()
    val cycle = LinkedList()
    val post = LinkedList()

    fun run(ctx: Context) {
        // Initialize plugins
        plugins.forEach { it.initPlugin(ctx) }

        this.pre.invokeRecursive(ctx)

        ctx.teleop.waitForStart()

        while (ctx.teleop.opModeIsActive()) {
            this.cycle.invokeRecursive(ctx)
        }

        this.post.invokeRecursive(ctx)
    }
}
