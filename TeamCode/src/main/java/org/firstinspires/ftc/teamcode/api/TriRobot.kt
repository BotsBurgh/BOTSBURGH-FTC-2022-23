package org.firstinspires.ftc.teamcode.api

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.arch.Config
import org.firstinspires.ftc.teamcode.arch.Robot
import org.firstinspires.ftc.teamcode.arch.runtime.RuntimeBuilder
import org.firstinspires.ftc.teamcode.api.components.LinearSlidesTeleOp
import org.firstinspires.ftc.teamcode.api.components.LoggerTeleOp
import org.firstinspires.ftc.teamcode.api.components.WheelsTeleOp
import org.firstinspires.ftc.teamcode.api.plugins.*

/**
 * This is the main robot configuration for the three-wheeled robot.
 *
 * All components are registered below in [configure].
 */
class TriRobot(teleop: LinearOpMode, cfg: Config): Robot(teleop, cfg) {
    override fun configure(builder: RuntimeBuilder) {
        builder

        // Teleop components
            .registerComponent(WheelsTeleOp())
            .registerComponent(LinearSlidesTeleOp())
            .registerComponent(LoggerTeleOp())

        // Autonomous components

        // Plugins
            .registerPlugin(Wheels())
            .registerPlugin(LinearSlides())
            .registerPlugin(DistanceSensors())
    }
}
