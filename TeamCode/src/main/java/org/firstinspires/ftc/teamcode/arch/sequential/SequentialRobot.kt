package org.firstinspires.ftc.teamcode.arch.sequential

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.Robot

open class SequentialRobot(val teleop: LinearOpMode): Robot<SequentialRuntimeBuilder> {
    private val runtimeBuilder = SequentialRuntimeBuilder()
    private val ctx = Context(teleop)

    init {
        this.configure(this.runtimeBuilder)
    }

    override fun configure(builder: SequentialRuntimeBuilder) {}

    override fun run() {
        val runtime = this.runtimeBuilder.build()
        runtime.run(this.ctx)
    }
}
