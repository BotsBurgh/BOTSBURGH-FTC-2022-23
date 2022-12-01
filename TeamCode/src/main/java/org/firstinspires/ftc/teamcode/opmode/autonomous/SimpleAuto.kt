package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.TriRobot
import org.firstinspires.ftc.teamcode.api.arch.Config
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.RunMode
import org.firstinspires.ftc.teamcode.api.plugins.LinearSlides
import org.firstinspires.ftc.teamcode.api.plugins.Wheels

@Autonomous(name = "Simple Autonomous")
class SimpleAuto: LinearOpMode() {
    private val config = Config(runMode = RunMode.Autonomous)

    private var ctx: Context? = null

    private var wheels_store: Wheels? = null
    private val wheels: Wheels
        get() = wheels_store!!

    private var linear_slides_store: LinearSlides? = null
    private val linear_slides: LinearSlides
        get() = linear_slides_store!!

    override fun runOpMode() {
        this.ctx = Context(this, this.config)

        this.wheels_store = Wheels()
        this.wheels.initPlugin(this.ctx!!)
        this.wheels.init()

        this.linear_slides_store = LinearSlides()
        this.linear_slides.initPlugin(this.ctx!!)
        this.linear_slides.init()

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        waitForStart()

        while (opModeIsActive()) {
            this.wheels.powerRotation(0.5)

            telemetry.addData("Status", "Running")
            telemetry.update()
        }
    }
}
