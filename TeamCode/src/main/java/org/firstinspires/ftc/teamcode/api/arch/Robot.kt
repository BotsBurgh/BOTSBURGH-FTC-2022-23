package org.firstinspires.ftc.teamcode.api.arch

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.arch.runtime.RuntimeBuilder

const val DEFAULT_ORDER: Byte = 0

open class Robot(val teleop: LinearOpMode, val cfg: Config) {
    private val runtimeBuilder: RuntimeBuilder = RuntimeBuilder()
    private val ctx: Context = Context(teleop, cfg)

    init {
        this.configure(this.runtimeBuilder)
    }

    open fun configure(builder: RuntimeBuilder) {}

    fun run() {
        val runtime = runtimeBuilder.build(this.cfg)
        runtime.run(this.ctx)
    }
}
