package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.TriRobot
import org.firstinspires.ftc.teamcode.api.arch.Config

@Autonomous(name = "Simple Autonomous")
class SimpleAuto: LinearOpMode() {
    override fun runOpMode() {
        TriRobot(this, Config(autonomous = true)).run()
    }
}
