package org.firstinspires.ftc.teamcode.api.arch

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

open class Robot(teleop: LinearOpMode, cfg: Config = Config()) {
    private var runtime: Runtime = Runtime()
    private val context: Context

    init {
        this.context = Context(teleop, cfg)
        this.registerComponents()
    }

    open fun registerComponents() {}

    fun run() {
        this.runtime.pre?.invokeRecursive(this.context)

        this.context.teleop.waitForStart()

        while (this.context.teleop.opModeIsActive()) {
            this.runtime.cycle?.invokeRecursive(this.context)
        }

        this.runtime.post?.invokeRecursive(this.context)
    }

    fun register(component: Component) {
        if (component.pre != null) {
            if (this.runtime.pre == null) {
                // Create pre
                this.runtime.pre = Link(component.pre!!)
            } else {
                // Add to pre
                this.runtime.pre!!.pushLast(Link(component.pre!!))
            }
        }

        if (component.cycle != null) {
            if (this.runtime.cycle == null) {
                this.runtime.cycle = Link(component.cycle!!)
            } else {
                this.runtime.cycle!!.pushLast(Link(component.cycle!!))
            }
        }

        if (component.post != null) {
            if (this.runtime.post == null) {
                this.runtime.post = Link(component.post!!)
            } else {
                this.runtime.post!!.pushLast(Link(component.post!!))
            }
        }
    }
}

data class Config(val autonomous: Boolean = false)
