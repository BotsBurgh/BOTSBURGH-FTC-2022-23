package org.firstinspires.ftc.teamcode.api.arch

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

open class Robot(teleop: LinearOpMode, cfg: Config = Config()) {
    private var runtime: RuntimeBuilder = RuntimeBuilder()
    private val context: Context

    init {
        this.context = Context(teleop, cfg)
        this.registerComponents()
    }

    open fun registerComponents() {}

    fun register(component: Component): Robot {
        this.runtime.register(component)
        return this
    }

    fun run() {
        val runtime = this.runtime.build()
        runtime.pre?.invokeRecursive(this.context)

        this.context.teleop.waitForStart()

        while (this.context.teleop.opModeIsActive()) {
            runtime.cycle?.invokeRecursive(this.context)
        }

        runtime.post?.invokeRecursive(this.context)
    }
}

data class Config(val autonomous: Boolean = false)
