package org.firstinspires.ftc.teamcode.arch.sequential

import org.firstinspires.ftc.teamcode.arch.base.CtxFunc
import org.firstinspires.ftc.teamcode.arch.base.Plugin
import org.firstinspires.ftc.teamcode.arch.base.IRuntimeBuilder

class SequentialRuntimeBuilder: IRuntimeBuilder {
    private val plugins: MutableList<Plugin> = ArrayList()

    private val pre: MutableList<CtxFunc> = ArrayList()
    private val main: MutableList<CtxFunc> = ArrayList()

    override fun registerPlugin(plugin: Plugin): SequentialRuntimeBuilder {
        this.plugins.add(plugin)
        return this
    }

    fun registerPre(func: CtxFunc): SequentialRuntimeBuilder {
        this.pre.add(func)
        return this
    }

    fun registerMain(func: CtxFunc): SequentialRuntimeBuilder {
        this.main.add(func)
        return this
    }

    fun registerStep(step: Step): SequentialRuntimeBuilder {
        if (step.pre != null) {
            this.registerPre(step.pre!!)
        }

        if (step.main != null) {
            this.registerMain(step.main!!)
        }

        return this
    }

    override fun build(): SequentialRuntime {
        val runtime = SequentialRuntime(this.plugins)

        this.pre.forEach { runtime.pre.push(it) }
        this.main.forEach { runtime.main.push(it) }

        return runtime
    }
}
