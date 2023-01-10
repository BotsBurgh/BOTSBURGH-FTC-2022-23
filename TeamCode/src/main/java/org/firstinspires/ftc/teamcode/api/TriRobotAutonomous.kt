package org.firstinspires.ftc.teamcode.api

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.plugins.Wheels
import org.firstinspires.ftc.teamcode.api.plugins.WheelsEx
import org.firstinspires.ftc.teamcode.api.plugins.opencv.OpenCV
import org.firstinspires.ftc.teamcode.api.plugins.wheels
import org.firstinspires.ftc.teamcode.api.plugins.wheels_ex
import org.firstinspires.ftc.teamcode.api.steps.LoggerAuto
import org.firstinspires.ftc.teamcode.api.steps.ScanAndPark
import org.firstinspires.ftc.teamcode.arch.sequential.SequentialRobot
import org.firstinspires.ftc.teamcode.arch.sequential.SequentialRuntimeBuilder
import kotlin.math.PI

class TriRobotAutonomous(teleop: LinearOpMode): SequentialRobot(teleop) {
    override fun configure(builder: SequentialRuntimeBuilder) {
        builder
            .registerPlugin(Wheels())
            .registerPlugin(WheelsEx())
            .registerPlugin(OpenCV())
            .registerStep(LoggerAuto())
            // .registerStep(ScanAndPark())
            .registerMain {
                it.wheels_ex.driveEncoderDirection(0.0, 24.0)
            }
    }
}
