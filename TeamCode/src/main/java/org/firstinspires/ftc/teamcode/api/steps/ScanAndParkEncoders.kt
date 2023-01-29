package org.firstinspires.ftc.teamcode.api.steps

import org.firstinspires.ftc.teamcode.api.plugins.opencv.ConeScanPipeline
import org.firstinspires.ftc.teamcode.api.plugins.opencv.ConeScanPipeline.Color
import org.firstinspires.ftc.teamcode.api.plugins.opencv.opencv
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.sequential.Step
import kotlin.math.PI

private const val FORWARD: Double = 0.0
private const val BACKWARD: Double = PI
private const val LEFT: Double = PI / 2.0
private const val RIGHT: Double = 3.0 * LEFT

private const val GRID_SIDE: Double = 24.0

/**
 * Scans cone color and parks in correct spot.
 */
class ScanAndPark : Step() {
    override val main = fun(ctx: Context) {
        // OpenCV cone scanning //

        val pipeline = ConeScanPipeline()
        ctx.opencv.setPipeline(pipeline)

        ctx.opencv.begin()

        // Wait 0.5 seconds
        ctx.teleop.sleep(500)

        val color = pipeline.output

        ctx.opencv.end()

        // Driving //

        // ctx.wheels_ex.driveEncoderDirection(LEFT, GRID_SIDE)
        ctx.teleop.sleep(1000)
        // ctx.wheels_ex.driveEncoderDirection(FORWARD, 3.0 * GRID_SIDE)
        ctx.teleop.sleep(1000)

        /*
        when (color) {
            Color.Red -> {}
            Color.Green -> ctx.wheels_ex.driveEncoderDirection(RIGHT, GRID_SIDE)
            Color.Blue -> ctx.wheels_ex.driveEncoderDirection(RIGHT, 2.0 * GRID_SIDE)
        }
         */
    }
}
