package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.arch.Config
import org.firstinspires.ftc.teamcode.arch.Context
import org.firstinspires.ftc.teamcode.arch.RunMode
import org.firstinspires.ftc.teamcode.api.plugins.Wheels
import org.firstinspires.ftc.teamcode.api.plugins.DistanceSensors

import kotlin.math.PI

abstract class DistanceTest : LinearOpMode() {
    abstract val sideBlue: Boolean

    private val config = Config(runMode = RunMode.Autonomous)

    private var ctx: Context? = null

    private var wheelsStore: Wheels? = null
    private val wheels: Wheels
        get() = wheelsStore!!


    private var distanceSensorStore: DistanceSensors? = null
    private val distanceSensors: DistanceSensors
        get() = distanceSensorStore!!

    override fun runOpMode() {
        this.ctx = Context(this, this.config)

        this.wheelsStore = Wheels()
        this.wheels.initPlugin(this.ctx!!)
        this.wheels.init()

        this.distanceSensorStore = DistanceSensors()
        this.distanceSensors.initPlugin(this.ctx!!)
        this.distanceSensors.init()

        while (opModeInInit()) {
            telemetry.addData("Status", "Initialized")
            telemetry.addData("Left", this.distanceSensors.getLeft())
            telemetry.addData("Back", this.distanceSensors.getBack())
            telemetry.addData("Right", this.distanceSensors.getRight())
            telemetry.update()
        }

        waitForStart()

        val runtime = ElapsedTime()

        runtime.reset()

        while (this.distanceSensors.getBack() < 9) {
            wheels.powerDirection(2 * PI / 3, 0.5)
        }

        wheels.stop()

        if (this.sideBlue) {
            while (this.distanceSensors.getLeft() > 12) {
                wheels.powerDirection((7 * PI) / 6, 0.5)
            }
        } else {
            while (this.distanceSensors.getRight() > 12) {
                wheels.powerDirection(PI / 6, 0.5)
            }
        }
    }
}

@Autonomous(name = "Distance Test Red")
class DistanceTestRed : DistanceTest() {
    override val sideBlue = false
}

@Autonomous(name = "Distance Test Blue")
class DistanceTestBlue : DistanceTest() {
    override val sideBlue = true
}
