package org.firstinspires.ftc.teamcode.api

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.plugins.Wheels
import org.firstinspires.ftc.teamcode.api.plugins.wheels
import org.firstinspires.ftc.teamcode.api.steps.LoggerAuto
import org.firstinspires.ftc.teamcode.arch.sequential.SequentialRobot
import org.firstinspires.ftc.teamcode.arch.sequential.SequentialRuntimeBuilder

class TriRobotAutonomous(teleop: LinearOpMode): SequentialRobot(teleop) {
    override fun configure(builder: SequentialRuntimeBuilder) {
        builder
            .registerPlugin(Wheels())
            .registerStep(LoggerAuto())
            .registerMain {
                it.wheels.driveEncoderDirection(0.0, 24.0)
            }
    }
}
