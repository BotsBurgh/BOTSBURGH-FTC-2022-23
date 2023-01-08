package org.firstinspires.ftc.teamcode.api.plugins.opencv

import org.opencv.core.Mat
import org.openftc.easyopencv.OpenCvPipeline

/**
 * An OpenCV pipeline that does not process or modify the input.
 */
class DefaultPipeline: OpenCvPipeline() {
    override fun processFrame(input: Mat?): Mat {
        return input!!
    }
}
