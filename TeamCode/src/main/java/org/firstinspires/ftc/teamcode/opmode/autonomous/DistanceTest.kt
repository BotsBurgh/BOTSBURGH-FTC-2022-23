package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.api.arch.Config
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.RunMode
import org.firstinspires.ftc.teamcode.api.plugins.Wheels
import org.firstinspires.ftc.teamcode.api.plugins.DistanceSensors
import org.firstinspires.ftc.teamcode.api.plugins.distance_sensors

import kotlin.math.PI

abstract class DistanceTest : LinearOpMode() {
    abstract val direction: Double

    private val config = Config(runMode = RunMode.Autonomous)

    private var ctx: Context? = null

    private var wheelsStore: Wheels? = null
    private val wheels: Wheels
        get() = wheelsStore!!


    private var distance_sensor_store: DistanceSensors? = null
    private val distance_sensor: DistanceSensors
        get() = distance_sensor_store!!


    override fun runOpMode() {
        this.ctx = Context(this, this.config)

        this.wheelsStore = Wheels()
        this.wheels.initPlugin(this.ctx!!)
        this.wheels.init()

        this.distance_sensor_store = DistanceSensors()
        this.distance_sensor.initPlugin(this.ctx!!)
        this.distance_sensor.init()

        while (opModeInInit()) {
            telemetry.addData("Status", "Initialized")
            telemetry.addData("Left", this.distance_sensor.getLeft())
            telemetry.addData("Back", this.distance_sensor.getBack())
            telemetry.addData("Right", this.distance_sensor.getRight())
            telemetry.update()
        }

        waitForStart()

        val runtime = ElapsedTime()

        runtime.reset()

        while (this.distance_sensor.getBack() < 9) {
            wheels.powerDirection(2 * PI / 3, 0.5)
        }; wheels.stop()

        while (this.distance_sensor.getRight() > 12)
            wheels.powerDirection(PI / 6, 0.5)

    }
}

@Autonomous(name = "DistanceTest")
class DistaneTestOne : DistanceTest() {
    override val direction = PI / 2.0
}


