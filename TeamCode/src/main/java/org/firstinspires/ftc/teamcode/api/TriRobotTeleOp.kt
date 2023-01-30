package org.firstinspires.ftc.teamcode.api

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.components.InitAfterRunTeleOp
import org.firstinspires.ftc.teamcode.api.components.LinearSlidesTeleOp
import org.firstinspires.ftc.teamcode.api.components.LoggerTeleOp
import org.firstinspires.ftc.teamcode.api.components.WheelsTeleOp
import org.firstinspires.ftc.teamcode.api.plugins.DistanceSensors
import org.firstinspires.ftc.teamcode.api.plugins.LinearSlides
import org.firstinspires.ftc.teamcode.api.plugins.Wheels
import org.firstinspires.ftc.teamcode.api.plugins.logger.Logger
import org.firstinspires.ftc.teamcode.api.plugins.logger.logger
import org.firstinspires.ftc.teamcode.arch.runloop.RunloopRobot
import org.firstinspires.ftc.teamcode.arch.runloop.RunloopRuntimeBuilder

/**
 * This is the main robot class for the three-wheeled robot teleop.
 *
 * All components are registered below in [configure].
 */
class TriRobotTeleOp(teleop: LinearOpMode) : RunloopRobot(teleop) {
    override fun configure(builder: RunloopRuntimeBuilder) {
        builder

            // Teleop components
            .registerComponent(InitAfterRunTeleOp())
            .registerComponent(WheelsTeleOp())
            .registerComponent(LinearSlidesTeleOp())
            .registerComponent(LoggerTeleOp())

            // Plugins
            .registerPlugin(Logger())
            .registerPlugin(Wheels())
            .registerPlugin(LinearSlides())
            .registerPlugin(DistanceSensors())
    }

    override fun run() {
        try {
            super.run()
        } finally {
            ctx.logger.close()
        }
    }
}
