package org.firstinspires.ftc.teamcode.arch.runloop

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.IRobot

/**
 * A robot that uses a runloop runtime with components.
 */
open class RunloopRobot(val teleop: LinearOpMode) : IRobot<RunloopRuntimeBuilder> {
    private val runtimeBuilder = RunloopRuntimeBuilder()
    private val ctx = Context(teleop)

    init {
        this.configure(this.runtimeBuilder)
    }

    override fun configure(builder: RunloopRuntimeBuilder) {}

    override fun run() {
        val runtime = this.runtimeBuilder.build()
        runtime.run(this.ctx)
    }
}
