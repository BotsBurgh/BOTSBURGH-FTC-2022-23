package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.api.arch.Config
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.RunMode
import org.firstinspires.ftc.teamcode.api.components.CLAW_CLOSE_POSITION
import org.firstinspires.ftc.teamcode.api.components.CLAW_OPEN_POSITION
import org.firstinspires.ftc.teamcode.api.plugins.LinearSlides
import org.firstinspires.ftc.teamcode.api.plugins.Wheels
import kotlin.math.PI

abstract class SmallPoll: LinearOpMode() {
    abstract val direction: Double

    private val config = Config(runMode = RunMode.Autonomous)

    private var ctx: Context? = null

    private var wheelsStore: Wheels? = null
    private val wheels: Wheels
        get() = wheelsStore!!


    private var linear_slides_store: LinearSlides? = null
    private val linear_slides: LinearSlides
        get() = linear_slides_store!!


    override fun runOpMode() {
        this.ctx = Context(this, this.config)

        this.wheelsStore = Wheels()
        this.wheels.initPlugin(this.ctx!!)
        this.wheels.init()


        this.linear_slides_store = LinearSlides()
        this.linear_slides.initPlugin(this.ctx!!)
        this.linear_slides.init()


        telemetry.addData("Status", "Initialized")
        telemetry.update()

        waitForStart()

        val runtime = ElapsedTime()

        runtime.reset()

        while (runtime.seconds() < 0.8 && opModeIsActive()) {
            this.wheels.powerDirection(this.direction, 0.35)
            this.linear_slides.powerSlide1(-1.0)
        }

        this.wheels.stop()
        while (runtime.seconds() < 0.95 && opModeIsActive()){
            this.wheels.stop()
        }
        while (runtime.seconds() < 1.1 && opModeIsActive()) {
            this.linear_slides.powerSlide1((-1.0))
        };

        this.linear_slides.stopSlide1()

        while (runtime.seconds() < 2.0 && opModeIsActive()){
            linear_slides.positionClaw1(CLAW_OPEN_POSITION)
            linear_slides.positionClaw1(CLAW_OPEN_POSITION)
        }

        while (runtime.seconds() < 2.5 && opModeIsActive()) {
            this.wheels.powerDirection(this.direction, -0.4)
        }

        while (runtime.seconds() < 2.8 && opModeIsActive()) {
            this.wheels.powerDirection((3* PI)/2, 0.6)
        }
    }
}

@Autonomous(name = "Small Poll")
class SmallPollRed: SmallPoll() {
    override val direction = 0.0

}