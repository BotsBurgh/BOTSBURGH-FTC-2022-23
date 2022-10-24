package org.firstinspires.ftc.teamcode.opmode.teleop

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.api.TriRobot
import org.firstinspires.ftc.teamcode.api.arch.Config

/**
 * This is the main teleop run during competition.
 *
 * For most of the part, this file will not be edited.
 * To find components being registered, take a look at the [TriRobot] class.
 * For an empty class used just for testing singular components, look at [EmptyTeleOp].
 */
@TeleOp(name = "TeleOp Main")
class TeleOpMain: LinearOpMode() {
    override fun runOpMode() {
        TriRobot(this, Config()).run()
    }
}
