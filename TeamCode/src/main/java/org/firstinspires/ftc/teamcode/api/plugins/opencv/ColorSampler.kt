package org.firstinspires.ftc.teamcode.api.plugins.opencv

import org.opencv.core.Mat
import org.firstinspires.ftc.teamcode.api.plugins.opencv.ConeScanPipeline.Color

fun defaultColorSample(): ColorSampler = PixelSampler()

abstract class ColorSampler {
    abstract fun sample(input: Mat): Color
}

class PixelSampler : ColorSampler() {
    override fun sample(input: Mat): Color {
        val pixel = input.get(input.rows() / 2, input.cols() / 2)

        return if (pixel[0] >= pixel[1] && pixel[0] >= pixel[2]) {
            Color.Red
        } else if (pixel[1] >= pixel[0] && pixel[1] >= pixel[2]) {
            Color.Green
        } else {
            Color.Blue
        }
    }
}
