package org.firstinspires.ftc.teamcode.api.arch

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

open class Robot(val teleop: LinearOpMode, val cfg: Config = Config()) {
    private var runtime: RuntimeBuilder = RuntimeBuilder()
    private val context: Context = Context(teleop, cfg)

    init {
        this.configure()
    }

    open fun configure() {}

    fun register(component: Component): Robot {
        this.runtime.register(component)
        return this
    }

    fun registerPre(func: ComponentFunction, order: Byte = DEFAULT_ORDER, opmode: OpMode = DEFAULT_OPMODE): Robot {
        this.runtime.registerPre(func, order, opmode)
        return this
    }

    fun registerCycle(func: ComponentFunction, order: Byte = DEFAULT_ORDER, opmode: OpMode = DEFAULT_OPMODE): Robot {
        this.runtime.registerCycle(func, order, opmode)
        return this
    }

    fun registerPost(func: ComponentFunction, order: Byte = DEFAULT_ORDER, opmode: OpMode = DEFAULT_OPMODE): Robot {
        this.runtime.registerPost(func, order, opmode)
        return this
    }

    fun run() {
        val runtime = this.runtime.build(this.cfg)
        runtime.pre.invoke(this.context)

        this.context.teleop.waitForStart()

        while (this.context.teleop.opModeIsActive()) {
            runtime.cycle.invoke(this.context)
        }

        runtime.post.invoke(this.context)
    }
}
