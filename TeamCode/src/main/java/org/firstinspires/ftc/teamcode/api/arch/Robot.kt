package org.firstinspires.ftc.teamcode.api.arch

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.arch.runtime.RuntimeBuilder

const val DEFAULT_ORDER: Byte = 0

open class Robot(val teleop: LinearOpMode, val cfg: Config) {
    private val runtimeBuilder: RuntimeBuilder = RuntimeBuilder()
    private val ctx: Context = Context(teleop, cfg)

    init {
        this.configure()
    }

    open fun configure() {}

    fun run() {
        val runtime = runtimeBuilder.build(this.cfg)
        runtime.run(this.ctx)
    }

    fun registerComponent(component: Component): Robot {
        this.runtimeBuilder.registerComponent(component)
        return this
    }

    fun registerPre(func: CtxFunc, order: Byte = DEFAULT_ORDER, runMode: RunMode? = null): Robot {
        this.runtimeBuilder.registerPre(func, order, runMode)
        return this
    }

    fun registerCycle(func: CtxFunc, order: Byte = DEFAULT_ORDER, runMode: RunMode? = null): Robot {
        this.runtimeBuilder.registerCycle(func, order, runMode)
        return this
    }

    fun registerPost(func: CtxFunc, order: Byte = DEFAULT_ORDER, runMode: RunMode? = null): Robot {
        this.runtimeBuilder.registerPost(func, order, runMode)
        return this
    }

    fun registerPlugin(plugin: Plugin): Robot {
        this.runtimeBuilder.registerPlugin(plugin)
        return this
    }
}
