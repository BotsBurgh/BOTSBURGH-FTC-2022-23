package org.firstinspires.ftc.teamcode.opmode.teleop

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.api.TriRobotTeleOp

/**
 * This is the main teleop run during competition.
 *
 * For most of the part, this file will not be edited.
 * To find components being registered, take a look at the [TriRobotTeleOp] class.
 * For an empty class used just for testing singular components, look at [EmptyTeleOp].
 */
@TeleOp(name = "TeleOp Main")
class TeleOpMain : LinearOpMode() {
    override fun runOpMode() {
        TriRobotTeleOp(this).run()
    }
}
