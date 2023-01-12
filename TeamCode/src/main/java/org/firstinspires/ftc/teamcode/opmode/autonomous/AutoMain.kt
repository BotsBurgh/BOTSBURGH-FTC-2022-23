package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.TriRobotAutonomous

@Autonomous(name = "Autonomous Main")
@Disabled
class AutoMain: LinearOpMode() {
    override fun runOpMode() {
        TriRobotAutonomous(this).run()
    }
}
