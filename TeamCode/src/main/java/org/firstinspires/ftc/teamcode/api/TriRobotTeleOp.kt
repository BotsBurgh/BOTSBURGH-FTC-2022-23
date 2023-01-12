package org.firstinspires.ftc.teamcode.api

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.components.LinearSlidesTeleOp
import org.firstinspires.ftc.teamcode.api.components.LoggerTeleOp
import org.firstinspires.ftc.teamcode.api.components.WheelsTeleOp
import org.firstinspires.ftc.teamcode.api.plugins.DistanceSensors
import org.firstinspires.ftc.teamcode.api.plugins.LinearSlides
import org.firstinspires.ftc.teamcode.api.plugins.Wheels
import org.firstinspires.ftc.teamcode.arch.runloop.RunloopRobot
import org.firstinspires.ftc.teamcode.arch.runloop.RunloopRuntimeBuilder

/**
 * This is the main robot configuration for the three-wheeled robot.
 *
 * All components are registered below in [configure].
 */
class TriRobotTeleOp(teleop: LinearOpMode): RunloopRobot(teleop) {
    override fun configure(builder: RunloopRuntimeBuilder) {
        builder

        // Teleop components
            .registerComponent(WheelsTeleOp())
            .registerComponent(LinearSlidesTeleOp())
            .registerComponent(LoggerTeleOp())

        // Plugins
            .registerPlugin(Wheels())
            .registerPlugin(LinearSlides())
            .registerPlugin(DistanceSensors())
    }
}
