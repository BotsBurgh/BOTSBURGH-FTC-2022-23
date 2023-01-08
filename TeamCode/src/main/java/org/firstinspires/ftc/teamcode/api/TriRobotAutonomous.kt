package org.firstinspires.ftc.teamcode.api

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.steps.LoggerAuto
import org.firstinspires.ftc.teamcode.arch.sequential.SequentialRobot
import org.firstinspires.ftc.teamcode.arch.sequential.SequentialRuntimeBuilder

class TriRobotAutonomous(teleop: LinearOpMode): SequentialRobot(teleop) {
    override fun configure(builder: SequentialRuntimeBuilder) {
        builder
            .registerStep(LoggerAuto())
    }
}
