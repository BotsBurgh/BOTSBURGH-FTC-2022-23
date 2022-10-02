package org.firstinspires.ftc.teamcode.api

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.arch.Config
import org.firstinspires.ftc.teamcode.api.arch.Robot
import org.firstinspires.ftc.teamcode.api.components.Logger

class TriRobot(teleop: LinearOpMode, cfg: Config): Robot(teleop, cfg) {
    override fun registerComponents() {
        this.register(Logger())
    }
}
