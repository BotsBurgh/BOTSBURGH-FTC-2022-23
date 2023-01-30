package org.firstinspires.ftc.teamcode.api

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.plugins.DistanceSensors
import org.firstinspires.ftc.teamcode.api.plugins.Wheels
import org.firstinspires.ftc.teamcode.api.steps.LoggerAuto
import org.firstinspires.ftc.teamcode.api.steps.ScanAndParkSensors
import org.firstinspires.ftc.teamcode.arch.sequential.SequentialRobot
import org.firstinspires.ftc.teamcode.arch.sequential.SequentialRuntimeBuilder

/**
 * This is the main robot class for the three-wheeled robot autonomous.
 *
 * All components are registered below in [configure].
 */
class TriRobotAutonomous(teleop: LinearOpMode) : SequentialRobot(teleop) {
    override fun configure(builder: SequentialRuntimeBuilder) {
        builder

            // Plugins
            .registerPlugin(Wheels())
            .registerPlugin(DistanceSensors())

            // Steps
            .registerStep(LoggerAuto())
            .registerStep(ScanAndParkSensors())
    }
}
