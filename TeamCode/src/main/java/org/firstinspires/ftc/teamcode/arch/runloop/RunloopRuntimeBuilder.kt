package org.firstinspires.ftc.teamcode.arch.runloop

import org.firstinspires.ftc.teamcode.arch.base.CtxFunc
import org.firstinspires.ftc.teamcode.arch.base.Plugin
import org.firstinspires.ftc.teamcode.arch.base.IRuntime
import org.firstinspires.ftc.teamcode.arch.base.RuntimeBuilder

class RunloopRuntimeBuilder: RuntimeBuilder {
    private val plugins: MutableList<Plugin> = ArrayList()

    private val pre: MutableList<PhaseData> = ArrayList()
    private val cycle: MutableList<PhaseData> = ArrayList()

    override fun registerPlugin(plugin: Plugin): RunloopRuntimeBuilder {
        this.plugins.add(plugin)
        return this
    }

    fun registerPre(func: CtxFunc, order: Byte = 0): RunloopRuntimeBuilder {
        this.pre.add(PhaseData(func, order))
        return this
    }

    fun registerCycle(func: CtxFunc, order: Byte = 0): RunloopRuntimeBuilder {
        this.cycle.add(PhaseData(func, order))
        return this
    }

    fun registerComponent(component: Component): RunloopRuntimeBuilder {
        if (component.pre != null) {
            this.registerPre(component.pre!!, order = component.order)
        }

        if (component.cycle != null) {
            this.registerCycle(component.cycle!!, order = component.order)
        }

        return this
    }

    override fun build(): IRuntime {
        val runtime = RunloopRuntime(this.plugins)

        this.pre.sortBy { it.order }
        this.cycle.sortBy { it.order }

        this.pre.forEach { runtime.pre.push(it.func) }
        this.cycle.forEach { runtime.cycle.push(it.func) }

        return runtime
    }
}

private data class PhaseData(val func: CtxFunc, val order: Byte)
