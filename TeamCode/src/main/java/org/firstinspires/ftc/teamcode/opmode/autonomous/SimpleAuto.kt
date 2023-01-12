package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.api.plugins.Wheels
import org.firstinspires.ftc.teamcode.api.plugins.wheels
import org.firstinspires.ftc.teamcode.api.steps.LoggerAuto
import org.firstinspires.ftc.teamcode.arch.sequential.SequentialRobot
import org.firstinspires.ftc.teamcode.arch.sequential.SequentialRuntimeBuilder
import kotlin.math.PI

private class SimpleAutoRobot(teleop: LinearOpMode, private val direction: Double) :
    SequentialRobot(teleop) {
    override fun configure(builder: SequentialRuntimeBuilder) {
        builder
            .registerPlugin(Wheels())
            .registerStep(LoggerAuto())
            .registerMain {
                val runtime = ElapsedTime()

                runtime.reset()

                while (runtime.seconds() < 2.0 && it.teleop.opModeIsActive()) {
                    it.wheels.powerDirection(this.direction, 0.5)
                }

                it.wheels.stop()
            }
    }
}

@Autonomous(name = "Simple Auto Left", group = "Simple Auto")
@Disabled
class SimpleAutoLeft : LinearOpMode() {
    override fun runOpMode() {
        SimpleAutoRobot(this, PI / 2.0).run()
    }
}

@Autonomous(name = "Simple Auto Right", group = "Simple Auto")
@Disabled
class SimpleAutoRight : LinearOpMode() {
    override fun runOpMode() {
        SimpleAutoRobot(this, 3.0 * (PI / 2.0)).run()
    }
}
