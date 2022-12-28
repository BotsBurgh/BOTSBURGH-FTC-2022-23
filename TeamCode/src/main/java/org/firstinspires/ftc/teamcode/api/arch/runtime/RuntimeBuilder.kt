package org.firstinspires.ftc.teamcode.api.arch.runtime

import org.firstinspires.ftc.teamcode.api.arch.*

class RuntimeBuilder {
    private val pre: MutableList<PhaseData> = ArrayList()
    private val cycle: MutableList<PhaseData> = ArrayList()
    private val post: MutableList<PhaseData> = ArrayList()

    private val plugins: MutableList<Plugin> = ArrayList()

    fun registerPre(func: CtxFunc, order: Byte = DEFAULT_ORDER, runMode: RunMode? = null): RuntimeBuilder {
        this.pre.add(PhaseData(func, order, runMode))
        return this
    }

    fun registerCycle(func: CtxFunc, order: Byte = DEFAULT_ORDER, runMode: RunMode? = null): RuntimeBuilder {
        this.cycle.add(PhaseData(func, order, runMode))
        return this
    }

    @Deprecated("The post phase has never worked in a standard TeleOp.", level = DeprecationLevel.ERROR)
    fun registerPost(func: CtxFunc, order: Byte = DEFAULT_ORDER, runMode: RunMode? = null): RuntimeBuilder {
        return this
    }

    fun registerComponent(component: Component): RuntimeBuilder {
        if (component.pre != null) {
            this.registerPre(component.pre!!, order = component.order, runMode = component.runMode)
        }

        if (component.cycle != null) {
            this.registerCycle(component.cycle!!, order = component.order, runMode = component.runMode)
        }

        return this
    }

    fun registerPlugin(plugin: Plugin): RuntimeBuilder {
        this.plugins.add(plugin)
        return this
    }

    fun build(cfg: Config): Runtime {
        val runtime = Runtime(this.plugins)

        fun filter(list: MutableList<PhaseData>) {
            // Sort list by order of components
            list.sortedBy { it.order }

            // Remove all items that's mode is not cfg.mode or null
            list.retainAll { it.runMode == cfg.runMode || it.runMode == null }
        }

        filter(this.pre)
        filter(this.cycle)

        // Register all functions, discarding unneeded data
        this.pre.forEach { runtime.pre.push(Link(it.func)) }
        this.cycle.forEach { runtime.cycle.push(Link(it.func)) }

        return runtime
    }
}

private data class PhaseData(val func: CtxFunc, val order: Byte, val runMode: RunMode?)
