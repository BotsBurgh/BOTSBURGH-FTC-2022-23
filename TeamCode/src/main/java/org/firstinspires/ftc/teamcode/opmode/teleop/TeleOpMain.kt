package org.firstinspires.ftc.teamcode.opmode.teleop

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.api.TriRobot
import org.firstinspires.ftc.teamcode.api.arch.Config

@TeleOp(name = "TeleOp Main")
class TeleOpMain: LinearOpMode() {
    override fun runOpMode() {
        TriRobot(this, Config()).run()
    }
}
