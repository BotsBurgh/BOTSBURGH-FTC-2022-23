package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.api.arch.Config
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.RunMode
import org.firstinspires.ftc.teamcode.api.plugins.Wheels
import kotlin.math.PI

abstract class SimpleAuto: LinearOpMode() {
    abstract val direction: Double

    private val config = Config(runMode = RunMode.Autonomous)

    private var ctx: Context? = null

    private var wheelsStore: Wheels? = null
    private val wheels: Wheels
        get() = wheelsStore!!

    /*
    private var linear_slides_store: LinearSlides? = null
    private val linear_slides: LinearSlides
        get() = linear_slides_store!!
     */

    override fun runOpMode() {
        this.ctx = Context(this, this.config)

        this.wheelsStore = Wheels()
        this.wheels.initPlugin(this.ctx!!)
        this.wheels.init()

        /*
        this.linear_slides_store = LinearSlides()
        this.linear_slides.initPlugin(this.ctx!!)
        this.linear_slides.init()
         */

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        waitForStart()

        val runtime = ElapsedTime()

        runtime.reset()

        while (runtime.seconds() < 1.0 && opModeIsActive()) {
            this.wheels.powerDirection(this.direction, 0.5)
        }

        this.wheels.stop()
    }
}

@Autonomous(name = "Simple Auto Left")
class SimpleAutoLeft: SimpleAuto() {
    override val direction = PI / 2.0
}

@Autonomous(name = "Simple Auto Right")
class SimpleAutoRight: SimpleAuto() {
    override val direction = 3.0 * (PI / 2.0)
}
