package org.firstinspires.ftc.teamcode.opmode.teleop

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.arch.runloop.RunloopRobot
import org.firstinspires.ftc.teamcode.arch.runloop.RunloopRuntimeBuilder

/**
 * An empty teleop for testing purposes. **Do not commit changes to this file.**
 */
@TeleOp(name = "Empty")
@Disabled
class EmptyTeleOp : LinearOpMode() {
    override fun runOpMode() {
        EmptyRobot(this).run()
    }
}

private class EmptyRobot(teleop: LinearOpMode) : RunloopRobot(teleop) {
    override fun configure(builder: RunloopRuntimeBuilder) {
        // Register any components here!
        // builder.registerComponent(MyComponent())
    }
}
