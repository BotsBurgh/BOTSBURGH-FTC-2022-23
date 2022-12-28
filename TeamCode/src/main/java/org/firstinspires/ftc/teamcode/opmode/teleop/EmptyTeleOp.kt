package org.firstinspires.ftc.teamcode.opmode.teleop

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.arch.Config
import org.firstinspires.ftc.teamcode.arch.Robot
import org.firstinspires.ftc.teamcode.arch.RunMode
import org.firstinspires.ftc.teamcode.arch.runtime.RuntimeBuilder

/**
 * This is an empty teleop for debugging purposes.
 * If a programmer has a quick component to test, use this instead of [TeleOpMain].
 *
 * By default, this is disabled. To fix this, remove the `@Disabled` annotation below.
 * To register components, add them to the [EmptyRobot] class.
 *
 * > **Note:** Please make sure not to commit your test changes in this file.
 */
@TeleOp(name = "Empty")
@Disabled
class EmptyTeleOp: LinearOpMode() {
    override fun runOpMode() {
        EmptyRobot(this, Config(RunMode.TeleOp)).run()
    }
}

/**
 * This is an empty [Robot] class. This manages all components being registered.
 * The actual [Robot] class used in production is [TriRobot][org.firstinspires.ftc.teamcode.api.TriRobot].
 */
private class EmptyRobot(teleop: LinearOpMode, cfg: Config): Robot(teleop, cfg) {
    override fun configure(builder: RuntimeBuilder) {
        // Register any components here!
        // builder.registerComponent(MyComponent())
    }
}
