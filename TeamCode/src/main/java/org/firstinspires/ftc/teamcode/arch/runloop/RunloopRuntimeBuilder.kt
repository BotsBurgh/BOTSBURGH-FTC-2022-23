package org.firstinspires.ftc.teamcode.arch.runloop

import org.firstinspires.ftc.teamcode.arch.base.CtxFunc
import org.firstinspires.ftc.teamcode.arch.base.IRuntimeBuilder
import org.firstinspires.ftc.teamcode.arch.base.Plugin

class RunloopRuntimeBuilder : IRuntimeBuilder {
    private val plugins: MutableList<Plugin> = ArrayList()

    private val pre: MutableList<PhaseData> = ArrayList()
    private val cycle: MutableList<PhaseData> = ArrayList()

    /**
     * Registers a [Plugin].
     */
    override fun registerPlugin(plugin: Plugin): RunloopRuntimeBuilder {
        this.plugins.add(plugin)
        return this
    }

    /**
     * Registers a [CtxFunc] to be run in the initialization phase.
     */
    fun registerPre(func: CtxFunc, order: Byte = 0): RunloopRuntimeBuilder {
        this.pre.add(PhaseData(func, order))
        return this
    }

    /**
     * Registers a [CtxFunc] to be run during the main (repeating) phase.
     */
    fun registerCycle(func: CtxFunc, order: Byte = 0): RunloopRuntimeBuilder {
        this.cycle.add(PhaseData(func, order))
        return this
    }

    /**
     * Registers a [Component].
     */
    fun registerComponent(component: Component): RunloopRuntimeBuilder {
        if (component.pre != null) {
            this.registerPre(component.pre!!, order = component.order)
        }

        if (component.cycle != null) {
            this.registerCycle(component.cycle!!, order = component.order)
        }

        return this
    }

    /**
     * Builds a [RunloopRuntime] and returns it.
     */
    override fun build(): RunloopRuntime {
        val runtime = RunloopRuntime(this.plugins)

        this.pre.sortBy { it.order }
        this.cycle.sortBy { it.order }

        this.pre.forEach { runtime.pre.push(it.func) }
        this.cycle.forEach { runtime.cycle.push(it.func) }

        return runtime
    }
}

private data class PhaseData(val func: CtxFunc, val order: Byte)
