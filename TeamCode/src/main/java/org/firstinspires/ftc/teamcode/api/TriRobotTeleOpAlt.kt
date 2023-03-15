package org.firstinspires.ftc.teamcode.api

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.components.*
import org.firstinspires.ftc.teamcode.api.plugins.LinearSlides
import org.firstinspires.ftc.teamcode.api.plugins.Wheels
import org.firstinspires.ftc.teamcode.api.plugins.logger.Logger
import org.firstinspires.ftc.teamcode.api.plugins.logger.logger
import org.firstinspires.ftc.teamcode.arch.runloop.RunloopRobot
import org.firstinspires.ftc.teamcode.arch.runloop.RunloopRuntimeBuilder
import java.io.File
import java.io.FileWriter

/**
 * This is the main robot configuration for the three-wheeled robot.
 *
 * All components are registered below in [configure].
 */
class TriRobotTeleOpAlt(teleop: LinearOpMode) : RunloopRobot(teleop) {
    override fun configure(builder: RunloopRuntimeBuilder) {
        builder

            // Teleop components
            .registerComponent(InitAfterRunTeleOp())
            .registerComponent(WheelsTeleOp())
            .registerComponent(LinearSlidesTeleOpOneControl())
            .registerComponent(LoggerTeleOp())

            // Plugins
            .registerPlugin(Logger())
            .registerPlugin(Wheels())
            .registerPlugin(LinearSlides())

    }

    override fun run() {
        try {
            super.run()
        } finally {
            ctx.logger.close()
        }
    }
}
