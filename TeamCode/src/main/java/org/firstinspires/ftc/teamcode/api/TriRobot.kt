package org.firstinspires.ftc.teamcode.api

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.arch.Config
import org.firstinspires.ftc.teamcode.api.arch.Robot
import org.firstinspires.ftc.teamcode.api.components.LinearSlides
import org.firstinspires.ftc.teamcode.api.components.Logger
import org.firstinspires.ftc.teamcode.api.components.PluginInit
import org.firstinspires.ftc.teamcode.api.components.Wheels

/**
 * This is the main robot configuration for the three-wheeled robot.
 *
 * All components are registered below in [configure].
 */
class TriRobot(teleop: LinearOpMode, cfg: Config): Robot(teleop, cfg) {
    override fun configure() {
            // Teleop components

            // Autonomous components

    }
}
