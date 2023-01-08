package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.TriRobotAutonomous

@Autonomous(name = "Autonomous Main")
class AutoMain: LinearOpMode() {
    override fun runOpMode() {
        TriRobotAutonomous(this).run()
    }
}
