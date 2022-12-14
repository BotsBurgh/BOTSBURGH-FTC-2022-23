package org.firstinspires.ftc.teamcode.api.steps

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.api.plugins.distance_sensors
import org.firstinspires.ftc.teamcode.api.plugins.opencv.ConeScanPipeline
import org.firstinspires.ftc.teamcode.api.plugins.opencv.opencv
import org.firstinspires.ftc.teamcode.api.plugins.wheels
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.sequential.Step
import kotlin.math.PI

class ScanAndParkSensors(private val pipeline: ConeScanPipeline = ConeScanPipeline()): Step() {
    override val main = fun(ctx: Context) {
        val runtime = ElapsedTime()

        ctx.opencv.begin()

        val coneColor = pipeline.output

        ctx.opencv.end()

        runtime.reset()

        when (coneColor) {
            ConeScanPipeline.Color.Green -> {
                while (ctx.distance_sensors.getBack() < 30 && ctx.teleop.opModeIsActive()) {
                    ctx.wheels.powerDirection(2 * PI / 3 + 0.1, 0.5)
                }
            }

            ConeScanPipeline.Color.Blue -> {
                while (ctx.distance_sensors.getLeft() > 13 && ctx.teleop.opModeIsActive()) {
                    ctx.wheels.powerDirection(7 * PI / 6, 0.5)
                }

                ctx.wheels.stop()

                while (ctx.distance_sensors.getBack() < 30 && ctx.teleop.opModeIsActive()) {
                    ctx.wheels.powerDirection(3 * PI / 4 , 0.5)
                }
            }

            ConeScanPipeline.Color.Red -> {
                while (runtime.seconds() < 1.25 && ctx.teleop.opModeIsActive()) {
                    ctx.wheels.powerDirection(PI / 6, 0.5)
                }

                ctx.wheels.stop()
                ctx.teleop.sleep(1300)

                while (runtime.seconds() < 4.0 && ctx.teleop.opModeIsActive()) {
                    ctx.wheels.powerDirection((2 * PI / 3) - 0.1, 0.5)
                }
            }
        }
    }
}