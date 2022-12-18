package org.firstinspires.ftc.teamcode.opmode.autonomous

import org.opencv.core.Mat
import org.openftc.easyopencv.OpenCvPipeline

class ConeScanPipeline: OpenCvPipeline() {
    enum class Color {
        Red,
        Green,
        Blue,
    }

    var output = Color.Blue
        private set

    private var contrasted = Mat()

    override fun processFrame(input: Mat?): Mat {
        input!!.convertTo(contrasted, -1, 0.6)

        val p = contrasted.get(contrasted.rows() / 2, contrasted.cols() / 2)

        output = if (p[0] >= p[1] && p[0] >= p[2]) {
            Color.Red
        } else if (p[1] >= p[0] && p[1] >= p[2]) {
            Color.Green
        } else {
            Color.Blue
        }

        return contrasted
    }
}
